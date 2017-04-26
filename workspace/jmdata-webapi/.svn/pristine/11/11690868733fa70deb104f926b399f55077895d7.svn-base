package com.jumore.jmdata.controller.information;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmdata.common.exceptions.BizException;
import com.jumore.jmdata.common.requests.CommResponse;
import com.jumore.jmdata.common.util.DateUtils;
import com.jumore.jmdata.common.util.properties.PropertiesBean;
import com.jumore.jmdata.dao.entity.industry.Industry;
import com.jumore.jmdata.dao.entity.information.Information;
import com.jumore.jmdata.model.param.common.FileParam;
import com.jumore.jmdata.model.param.information.InformationQueryParam;
import com.jumore.jmdata.model.vo.PageResult;
import com.jumore.jmdata.model.vo.Information.InformationVO;
import com.jumore.jmdata.service.common.IFileService;
import com.jumore.jmdata.service.industry.IIndustryService;
import com.jumore.jmdata.service.information.IInformationService;

/**
 * 
 * @author liquan
 *
 * @since: 2016年9月7日 下午2:16:23
 * @history:
 */
@Controller()
@RequestMapping("/v1/information")
public class InformationController extends BaseController {

	@Autowired
	IInformationService informationService;

	@Autowired
	IIndustryService industryService;

	@Autowired
	private IFileService fileService;

	@Autowired
	private PropertiesBean propertiesBean;

	/**
	 * 查询善行列表
	 *
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody ModelAndView list(Information queryParam) {
		ModelAndView model = new ModelAndView();
		Industry cate = new Industry();
		cate.setRank(0);
		List<Object> list = industryService.listByExample(cate);
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

	@RequestMapping(value = "/info")
	public @ResponseBody InformationVO getInformationInfo(Integer id) {
		if (null != id) {
			Information info = informationService.getInformationInfo(id);
			if (null != info) {
				InformationVO vo = new InformationVO();
				BeanUtils.copyProperties(info, vo);
				vo.setCreateTimeStr(DateUtils.dateToDateString(vo
						.getCreateTime()));
				vo.setPublicDateStr(DateUtils.dateToDateString(vo
						.getPublicDate()));
				vo.setPicturePath(propertiesBean.getWebImageViewPath()
						+ vo.getPicturePath());
				return vo;
			}
		}
		return null;
	}

	/**
	 * 查询善行分页列表
	 * 
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listData")
	public @ResponseBody PageResult<InformationVO> listData(
			InformationQueryParam queryParam, String startDate, String endDate) {
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
		return informationService.queryShowInformationPage(queryParam);
	}

	// /**
	// * 给首页展示使用：1、先查询出最新的有图片的作为第一条；2、查询最新的三条作为后面三个(排除已经存在的第一条记录)；一共4个
	// *
	// * @param request
	// * @param activityQueryParam
	// * @return 善行列表
	// */
	// @RequestMapping(value = "/listInforIndexData")
	// public @ResponseBody PageResult<InformationVO> listInforIndexData(
	// InformationQueryParam queryParam, String startDate, String endDate) {
	//
	// List<InformationVO> resultList = new ArrayList<InformationVO>();
	// // 给首页展示使用：1、先查询出最新的有图片的作为第一条；2、查询最新的三条作为后面三个(排除已经存在的第一条记录)；一共4个
	// ParamMap paramMap = ParamUtil.getParamMap(queryParam);
	// paramMap.put("top", 1);
	// List<InformationVO> top1List = informationService.listByParams(
	// InformationVO.class, ".InformationMapper.selectForIndexTopPic",
	// paramMap);
	// // <!-- 后面选出的三条要排除掉第一条带图片记录，否则重复了 -->
	// if (null != top1List && null != top1List.get(0)) {
	// paramMap.put("oneId", top1List.get(0).getId());
	// resultList.add(top1List.get(0));
	// } else {
	// paramMap.put("oneId", -1);
	// }
	// paramMap.put("top", 3);
	// List<InformationVO> top3List = informationService.listByParams(
	// InformationVO.class,
	// ".InformationMapper.selectForIndexTopThree", paramMap);
	// resultList.addAll(top3List);
	// // 返回格式化的时间字段
	// if (null != resultList) {
	// for (InformationVO vo : resultList) {
	// vo.setCreateTimeStr(DateUtils.dateToDateString(vo
	// .getCreateTime()));
	// vo.setPublicDateStr(DateUtils.dateToDateString(vo
	// .getPublicDate()));
	// }
	// }
	// PageResult<InformationVO> response = new PageResult<InformationVO>(
	// resultList, resultList.size());
	// return response;
	// }

	/**
	 * 给首页展示使用：一共4个
	 * 
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listInforIndexData")
	public @ResponseBody PageResult<InformationVO> listInforIndexData(
			InformationQueryParam queryParam, String startDate, String endDate) {
		queryParam.setLimit(4);// 只要4条
		queryParam.setCategories(1);// 只查询：观点
		queryParam.setBdate(startDate);
		queryParam.setEdate(endDate);
		queryParam.setIsPublic(1);
		return informationService.queryShowInformationPage(queryParam);
	}

	/**
	 * 行业资讯：列表
	 * 
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listInforData")
	public @ResponseBody PageResult<InformationVO> listInforData(
			InformationQueryParam queryParam, String startDate, String endDate) {
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
		queryParam.setCategories(1);// 只查询：观点
		queryParam.setBdate(startDate);
		queryParam.setEdate(endDate);
		queryParam.setIsPublic(1);
		return informationService.queryShowInformationPage(queryParam);
	}

	/**
	 * 观点资讯：列表
	 * 
	 * @param request
	 * @param activityQueryParam
	 * @return 善行列表
	 */
	@RequestMapping(value = "/listOpinionData")
	public @ResponseBody PageResult<InformationVO> queryShowInformationPage(
			InformationQueryParam queryParam, String startDate, String endDate) {
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
		queryParam.setCategories(2);// 只查询：观点
		queryParam.setBdate(startDate);
		queryParam.setEdate(endDate);
		queryParam.setIsPublic(1);
		return informationService.queryInformationPage(queryParam);
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
	public @ResponseBody CommResponse addOrUpdate(Information infor,
			FileParam fileParam, HttpServletResponse response)
			throws BizException {// @RequestParam(required
									// = false,
									// value =
									// "file")
									// MultipartFile
									// myfile,
		if (StringUtils.isNotEmpty(infor.getContent())) {
			String regex = "<[^>]*>";
			String str = infor.getContent().replaceAll(regex, "")
					.replaceAll("&nbsp;", " ");
			if (str.length() > 10000) {
				return CommResponse.failure("内容超出，请删减文字后保存。");
			}
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
		// Information.setPicture(picture);
		// }

		// if (infor.getId() == null) {
		// Account account = (Account) SecurityUtils.getSubject().getSession()
		// .getAttribute(Consts.Session_User_Key);
		// if (null != account && null != account.getId()) {// 保存时：记录上传人
		// infor.setUserId(Integer.valueOf(account.getId() + ""));
		// }
		// informationService.addInformation(infor);
		// } else {
		// informationService.updateInformation(infor);
		// }
		informationService.updateOrAdd(infor, fileParam);

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
		informationService.deleteById(Information.class, id);
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
			informationService.deleteByIds(Information.class,
					Arrays.asList(ids.split(",")));
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
		return CommResponse.success(informationService.setStatus(id, status));
	}

}
