package com.jumore.jmdata.controller.help;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.dove.util.ParamMap;
import com.jumore.jmdata.common.exceptions.BizException;
import com.jumore.jmdata.common.requests.CommResponse;
import com.jumore.jmdata.common.util.Consts;
import com.jumore.jmdata.common.util.DateUtils;
import com.jumore.jmdata.common.util.ParamUtil;
import com.jumore.jmdata.dao.entity.help.Help;
import com.jumore.jmdata.dao.entity.help.HelpCategories;
import com.jumore.jmdata.dao.entity.permission.Account;
import com.jumore.jmdata.model.param.help.HelpQueryParam;
import com.jumore.jmdata.model.vo.PageResult;
import com.jumore.jmdata.model.vo.help.HelpVO;
import com.jumore.jmdata.model.vo.menuTree.MenuTreeVO;
import com.jumore.jmdata.model.vo.menuTree.SubItems;
import com.jumore.jmdata.service.help.IHelpCategoriesService;
import com.jumore.jmdata.service.help.IHelpService;

/**
 * 
 * @author liquan
 *
 * @since: 2016年9月7日 下午2:16:23
 * @history:
 */
@Controller()
@RequestMapping("v1/help")
public class HelpController extends BaseController {

	@Autowired
	IHelpService helpService;

	@Autowired
	IHelpCategoriesService categoriesService;

	/**
	 * 查询善行列表
	 *
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody ModelAndView list(Help queryParam) {
		ModelAndView model = new ModelAndView();
		HelpCategories cate = new HelpCategories();
		cate.setIsDel(0);
		List<Object> list = categoriesService.listByExample(cate);
		model.addObject("list", list);
		return model;
	}

	/**
	 * 判断不为空
	 * 
	 * @param str
	 * @return
	 * @history:
	 */
	public boolean isNotNull(String str) {
		if (null != str && str.trim().length() > 1) {
			return true;
		}
		return false;
	}

	/**
	 * 查询善行分页列表
	 * 
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listData")
	public @ResponseBody PageResult<HelpVO> listData(HelpQueryParam queryParam,
			String startDate, String endDate) {
		// queryParam.addSort("createtime", SortType.DESC);
		// queryParam.setDelflag(0);// 只查询未删除的记录

		// if(isNotNull(startDate)){//查询开始时间
		// queryParam.setBdate(DateUtils.parseDate(startDate,
		// DateUtils.DAY_FORMAT));
		// }
		// if(isNotNull(endDate)){//查询结束时间
		// queryParam.setEdate(DateUtils.parseDate(endDate,
		// DateUtils.DAY_FORMAT));
		// }
		queryParam.setBdate(startDate);
		queryParam.setEdate(endDate);
		queryParam.setIsPublic(1);
		return helpService.queryHelpPage(queryParam);
	}

	/**
	 * 查询善行分页列表
	 * 
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listHotData")
	public @ResponseBody PageResult<HelpVO> listHotData(
			HelpQueryParam queryParam, String startDate, String endDate) {
		// queryParam.addSort("createtime", SortType.DESC);
		// queryParam.setDelflag(0);// 只查询未删除的记录

		// if(isNotNull(startDate)){//查询开始时间
		// queryParam.setBdate(DateUtils.parseDate(startDate,
		// DateUtils.DAY_FORMAT));
		// }
		// if(isNotNull(endDate)){//查询结束时间
		// queryParam.setEdate(DateUtils.parseDate(endDate,
		// DateUtils.DAY_FORMAT));
		// }
		queryParam.setBdate(startDate);
		queryParam.setEdate(endDate);
		queryParam.setIsPublic(1);
		return helpService.listHotData(queryParam);
	}

	/**
	 * 查询帮助中心的分类列表：包含分类下的内容
	 *
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listMenuData")
	public @ResponseBody CommResponse listMenuData(HelpCategories cate) {
		// cate.setBottomShow(1);// 在底部显示的：帮助分类
		// queryParam.addSort("createtime", SortType.DESC);
		cate.setIsDel(0);// 只查询未删除的记录
		ParamMap paramMapx = ParamUtil.getParamMap(cate);
		List<HelpCategories> list =categoriesService.listByParams(HelpCategories.class, ".HelpCategoriesMapper.selectBy", paramMapx);
//		List<HelpCategories> list = categoriesService.listByExample(cate);
		List<MenuTreeVO> resultList = new ArrayList<MenuTreeVO>();

		if (null != list && list.size() > 0) {
			for (HelpCategories helpCategories : list) {
				MenuTreeVO tree = new MenuTreeVO();
				tree.setId(helpCategories.getId());
				tree.setName(helpCategories.getCateName());
				tree.setParentId(0);
				HelpQueryParam queryParam = new HelpQueryParam();
				queryParam.setCateId(helpCategories.getId());
				queryParam.setIsPublic(1);
 
				ParamMap paramMap = ParamUtil.getParamMap(queryParam);
				paramMap.addOrder("sort", "asc");
				// 查询help:不包含内容和其他关联字段
				List<Help> helpList = helpService.listByParams(Help.class,
						".HelpMapper.selectNoContentDetail", paramMap);

				if (null != helpList && helpList.size() > 0) {
					SubItems sub = new SubItems();
					List<MenuTreeVO> subList = new ArrayList<MenuTreeVO>();
					for (Help help : helpList) {
						MenuTreeVO vo = new MenuTreeVO();
						vo.setId(help.getId());
						vo.setName(help.getTitle());
						vo.setParentId(helpCategories.getId());
						subList.add(vo);
					}
					sub.setList(subList);
					tree.setSubItems(sub);
				}
				resultList.add(tree);
			}
		}
		return CommResponse.success(resultList);
	}

	// /**
	// * 查询帮助中心的分类列表：包含分类下的内容
	// *
	// * @param request
	// * @param activityQueryParam
	// * @return 善行列表
	// */
	// @RequestMapping(value = "/listMenuData")
	// public @ResponseBody List<HelpCategoriesVO> listMenuData(HelpCategories
	// cate) {
	// cate.setBottomShow(1);// 在底部显示的：帮助分类
	// // queryParam.addSort("createtime", SortType.DESC);
	// cate.setIsDel(0);// 只查询未删除的记录
	// List<HelpCategories> list = categoriesService.listByExample(cate);
	// List<HelpCategoriesVO> resultList = new ArrayList<HelpCategoriesVO>();
	// if (null != list) {
	// for (HelpCategories helpCategories : list) {
	// HelpQueryParam queryParam = new HelpQueryParam();
	// queryParam.setCateId(helpCategories.getId());
	// queryParam.setIsPublic(1);
	// LinkedHashMap<String, SortType> sortmap = new LinkedHashMap<String,
	// BaseParam.SortType>();
	// sortmap.put("sort", SortType.DESC);
	// queryParam.setSortMap(sortmap);
	// ParamMap paramMap = ParamUtil.getParamMap(queryParam);
	// // 查询help:不包含内容和其他关联字段
	// List<Help> helpList = helpService.listByParams(Help.class,
	// ".HelpMapper.selectNoContentDetail", paramMap);
	// HelpCategoriesVO helpCate = new HelpCategoriesVO();
	// BeanUtils.copyProperties(helpCategories, helpCate);
	// helpCate.setHelpList(helpList);
	// resultList.add(helpCate);
	// }
	// }
	// return resultList;
	// }

	/**
	 * 行愿无尽：编辑界面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public @ResponseBody ModelAndView edit(Integer id) {
		ModelAndView model = new ModelAndView();
		if (null != id) {
			Help help = helpService.getHelpInfo(id);
			model.addObject("help", help);
		}
		// 设置分类列表：带到前台显示
		HelpCategories cate = new HelpCategories();
		cate.setIsDel(0);
		List<Object> list = categoriesService.listByExample(cate);
		model.addObject("cateList", list);
		return model;
	}

	@RequestMapping(value = "/info")
	public @ResponseBody HelpVO getHelpInfo(Integer id) {
		if (null != id) {
			Help info = helpService.getHelpInfo(id);
			if (null != info) {
				HelpVO vo = new HelpVO();
				BeanUtils.copyProperties(info, vo);
				vo.setCreateTimeStr(DateUtils.dateToDateString(vo
						.getCreateTime()));
				vo.setPublicDateStr(DateUtils.dateToDateString(vo
						.getPublicDate()));
				return vo;
			}
		}
		return null;
	}

	/**
	 * 功能说明：新增或修改
	 * 
	 * @param sopParam
	 *            id为空时表示新增
	 * @return
	 * @throws BizException
	 *             CommResponse
	 */
	@RequestMapping(value = "/addOrUpdate")
	public @ResponseBody CommResponse addOrUpdate(Help help,
			HttpServletResponse response) throws BizException {// @RequestParam(required
																// = false,
																// value =
																// "file")
																// MultipartFile
																// myfile,
		if (StringUtils.isNotEmpty(help.getContent())) {
			String regex = "<[^>]*>";
			String str = help.getContent().replaceAll(regex, "")
					.replaceAll("&nbsp;", " ");
			if (str.length() > 10000) {
				return CommResponse.failure("内容超出，请删减文字后保存。");
			}
			String summery = "";// 简介字段：截取内容的第一行
			if (str.length() > 50) {
				summery = str.substring(0, 50);
			} else {
				summery = str;
			}
			help.setSummary(summery);
		}

		// // 判断是不是已上传了新文件
		// if (myfile != null && myfile.getSize() > 0) {
		// if (myfile.getSize() > 3 * 1024 * 1024) {
		// throw new BizException(
		// BaseExceptionEnum.PIC_SIZE_TOO_BIG.getCode(),
		// "图片超过3M的限制大小");
		// }
		// String picture = "";
		// try {
		// // 以原大小存储图片
		// picture = MediaUtils.saveImage(myfile, 0, 0);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// // 将图片放入对象
		// help.setPicture(picture);
		// }

		if (help.getId() == null) {
			Account account = (Account) SecurityUtils.getSubject().getSession()
					.getAttribute(Consts.Session_User_Key);
			if (null != account && null != account.getId()) {// 保存时：记录上传人
				help.setUserId(Integer.valueOf(account.getId() + ""));
			}
			help.setCreateTime(new Date());
			helpService.addHelp(help);
		} else {
			helpService.updateHelp(help);
		}
		return CommResponse.success();
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public CommResponse delete(Integer id) throws Exception {
		helpService.deleteById(Help.class, id);
		return CommResponse.success();
	}

	/**
	 * 删除:批量
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public CommResponse delete(String ids) throws Exception {
		if (StringUtils.isNotEmpty(ids)) {
			helpService.deleteByIds(Help.class, Arrays.asList(ids.split(",")));
		}
		return CommResponse.success();
	}

	/**
	 * 设置：发布状态
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setStatus")
	@ResponseBody
	public CommResponse setStatus(Integer id, int status) throws Exception {
		return CommResponse.success(helpService.setStatus(id, status));
	}

}
