package com.jumore.jmdata.controller.user;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.dove.cache.CacheService;
import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmdata.common.auth.DataUserDTO;
import com.jumore.jmdata.dao.entity.common.UserAttention;
import com.jumore.jmdata.model.vo.common.RespModel;
import com.jumore.jmdata.service.common.IUserAttentionService;
import com.jumore.ucenter.dubbo.service.en.api.dto.EnUserDTO;

/**
 * 辅助前端 提供认证服务
 * 
 * @author win
 *
 */
@Controller
public class UserCenterController extends BaseController {

	@Autowired
	protected CacheService cacheService;


	@Autowired
	private IUserAttentionService userAttentionService;

	@RequestMapping(value = "/v1/login")
	public String login(String from, HttpServletResponse response)
			throws IOException {
		String sjid = (String) SecurityUtils.getSubject().getSession().getId();
		StringBuffer sb = new StringBuffer(200);
		sb.append("redirect:");
		sb.append(from).append("?shiroJID=").append(sjid);

		// 获取本系统特有信息
		DataUserDTO userDTO = (DataUserDTO) SecurityUtils.getSubject()
				.getSession().getAttribute("user");
		Long userId = userDTO.getId();
		String realName = userDTO.getRealName();
		sb.append("&userId=");
		sb.append(userId);
		sb.append("&realName=");
		sb.append(URLEncoder.encode(realName));
		String url = sb.toString();
		logHelper.getBuilder().info("登陆成功,跳转到==》" + url);
		return url;

	}

	@RequestMapping(value = "/v1/getMsg", method = RequestMethod.GET)
	@ResponseBody
	public DataUserDTO getUserMessage(EnUserDTO enUserDTO) throws IOException {
		DataUserDTO userDTO = (DataUserDTO) SecurityUtils.getSubject()
				.getSession().getAttribute("user");
		if (userDTO != null && enUserDTO.getId() == null) {
			enUserDTO.setId(userDTO.getId());
		}
		
		return userDTO;

	}

	@RequestMapping(value = "/v1/webLogout")
	public String doLogout(HttpServletRequest request) throws Exception {
		SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);

		String redAddress = "";
		String queryString = savedRequest.getQueryString();
		if (StringUtils.isNotEmpty(queryString) && queryString.length() > 11) {
			redAddress = queryString.substring(11);
		}
		SecurityUtils.getSubject().logout();
		return "redirect:" + redAddress;
	}

	@RequestMapping(value = "/v1/reHome")
	public String reHome(String from) throws Exception {
		if (StringUtils.isNotEmpty(from)) {
			return "redirect:" + from;
		}
		return "redirect:/user/init";
	}

	/**
	 * 用户关注
	 * 
	 * @return 行业id 行业名称 关注名称 返回链接
	 *         http://localhost:8080/userAttention/dofollow?attention
	 *         .industryId=
	 *         120&attention.industryName=123&attention.cateName=123&
	 *         attention.uri=123
	 * 
	 */
	@RequestMapping(value = "/v1/userAttention/dofollow", method = RequestMethod.GET)
	@ResponseBody
	public RespModel follow(UserAttention attention) {
		RespModel model = new RespModel(RespModel.RespCode.SUCCESS.getCode(),
				"关注成功");
		DataUserDTO userDTO = (DataUserDTO) SecurityUtils.getSubject()
				.getSession().getAttribute("user");
		attention.setUserId(userDTO.getId());
		attention.setIsAttention((byte) 1);
		attention.setServiceType((byte) 1);
		attention.setAttentionDate(new Date());
		userAttentionService.saveOrUpdate(attention);
		return model;
	}

	/**
	 * 关注数据回显
	 * 
	 * @return 行业id 行业名称 关注名称 返回链接
	 *         http://localhost:8080/userAttention/dofollow?attention
	 *         .industryId=
	 *         120&attention.industryName=123&attention.cateName=123&
	 *         attention.uri=123
	 * 
	 */
	@RequestMapping(value = "/v1/userAttention/doQuery", method = RequestMethod.GET)
	@ResponseBody
	public UserAttention query(UserAttention attention, HttpServletResponse re) {
		re.addHeader("Access-Control-Allow-Origin", "*");
		re.addHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept");
		DataUserDTO userDTO = (DataUserDTO) SecurityUtils.getSubject()
				.getSession().getAttribute("user");
		Long id = userDTO.getId();
		attention.setUserId(id);
		UserAttention po = userAttentionService.queryByUri(attention);
		if (po == null) {
			po = new UserAttention();
			po.setIsAttention((byte) 0);
			return po;
		}
		return po;
	}
}
