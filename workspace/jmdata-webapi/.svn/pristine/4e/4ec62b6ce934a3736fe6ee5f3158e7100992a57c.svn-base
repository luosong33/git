package com.jumore.jmdata.controller.notice;

import java.util.Arrays;
import java.util.Date;

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
import com.jumore.jmdata.common.exceptions.BizException;
import com.jumore.jmdata.common.requests.CommResponse;
import com.jumore.jmdata.common.util.Consts;
import com.jumore.jmdata.common.util.DateUtils;
import com.jumore.jmdata.dao.entity.notice.Notice;
import com.jumore.jmdata.dao.entity.permission.Account;
import com.jumore.jmdata.model.param.notice.NoticeQueryParam;
import com.jumore.jmdata.model.vo.PageResult;
import com.jumore.jmdata.model.vo.Information.InformationVO;
import com.jumore.jmdata.model.vo.notice.NoticeVO;
import com.jumore.jmdata.service.notice.INoticeService;

/**
 * 
 * @author liquan
 *
 * @since: 2016年9月7日 下午2:16:23
 * @history:
 */
@Controller()
@RequestMapping("v1/notice")
public class NoticeController extends BaseController {

	@Autowired
	INoticeService noticeService;

	/**
	 * 查询善行列表
	 *
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody ModelAndView list(Notice queryParam) {
		ModelAndView model = new ModelAndView();
		// NoticeCategories cate = new NoticeCategories();
		// cate.setIsDel(1);
		// List<Object> list = categoriesService.listByExample(cate);
		// model.addObject("list", list);
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
	public @ResponseBody PageResult<NoticeVO> listData(
			NoticeQueryParam queryParam, String startDate, String endDate) {
//		queryParam.addSort("createtime", SortType.DESC);
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
		queryParam.setIsPublic(1);//只查询已发布的通知
		return noticeService.queryNoticePage(queryParam);
	}

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
			Notice notice = noticeService.getNoticeInfo(id);
			model.addObject("notice", notice);
		}
		// // 设置分类列表：带到前台显示
		// NoticeCategories cate = new NoticeCategories();
		// cate.setIsDel(1);
		// List<Object> list = categoriesService.listByExample(cate);
		// model.addObject("cateList", list);
		return model;
	}
	
	@RequestMapping(value = "/info")
	public @ResponseBody NoticeVO getNoticeInfo(Integer id) {
		if (null != id) {
			Notice info= noticeService.getNoticeInfo(id);
			if(null!=info){
				NoticeVO vo = new NoticeVO();
				BeanUtils.copyProperties(info, vo);
				vo.setCreateTimeStr(DateUtils.dateToDateString(vo.getCreateTime()));
				vo.setPublicDateStr(DateUtils.dateToDateString(vo.getPublicDate()));
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
	public @ResponseBody CommResponse addOrUpdate(Notice notice,
			HttpServletResponse response) throws BizException {// @RequestParam(required
																// = false,
																// value =
																// "file")
																// MultipartFile
																// myfile,
		if (StringUtils.isNotEmpty(notice.getContent())) {
			String regex = "<[^>]*>";
			String str = notice.getContent().replaceAll(regex, "")
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
		// Notice.setPicture(picture);
		// }

		if (notice.getId() == null) {
			Account account = (Account) SecurityUtils.getSubject().getSession()
					.getAttribute(Consts.Session_User_Key);
			if (null != account && null != account.getId()) {// 保存时：记录上传人
				notice.setUserId(Integer.valueOf(account.getId() + ""));
			}
			notice.setCreateTime(new Date());
			noticeService.addNotice(notice);
		} else {
			noticeService.updateNotice(notice);
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
		noticeService.deleteById(Notice.class, id);
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
		if(StringUtils.isNotEmpty(ids)){
			noticeService.deleteByIds(Notice.class, Arrays.asList(ids.split(",")));
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
		return CommResponse.success(noticeService.setStatus(id, status));
	}

}
