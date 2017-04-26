package com.jumore.jmdata.controller.auth;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jumore.dove.controller.base.BaseController;
import com.jumore.dove.util.RSAUtils;
import com.jumore.dove.web.auth.DoveCasToken;
import com.jumore.dove.web.model.Const;
import com.jumore.dove.web.model.ResponseVo;
import com.jumore.jmdata.common.auth.AuthUser;

/**
 * Created by root on 11/1/16.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<String> doLogin(HttpServletRequest request,
			@RequestBody AuthUser authUser) throws Exception {
		RSAPrivateKey privateKey = (RSAPrivateKey) request.getSession()
				.getAttribute(RSAUtils.RAS_Key_Private);
		String descrypedPwd = RSAUtils.decryptByPrivateKey(
				authUser.getPassword(), privateKey);
		Subject user = SecurityUtils.getSubject();
		DoveCasToken token = new DoveCasToken(authUser.getMobileNo(),
				descrypedPwd);
		try {
			user.login(token);
			SavedRequest lastPage = WebUtils.getSavedRequest(request);
			// 重定向到系统首页，或者之前访问的页面
			ResponseVo<String> rs = ResponseVo.<String> BUILDER();
			if (lastPage != null) {
				rs.setData(lastPage.getRequestUrl());
			}

			request.getSession().removeAttribute(RSAUtils.RAS_Key_Private);
			return rs;
		} catch (AuthenticationException ex) {
			logHelper.getBuilder().warn("", ex);
			return ResponseVo.<String> BUILDER()
					.setCode(Const.BUSINESS_CODE.FAILED).setDesc("用户名或密码错误");
		} catch (Exception e) {
			logHelper.getBuilder().warn("", e);
			return ResponseVo.<String> BUILDER()
					.setCode(Const.BUSINESS_CODE.FAILED).setDesc("登录失败");
		}
	}

	@RequestMapping(value = "v1/getPublicKey", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<String> getPublicKey(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = RSAUtils.getKeys();
		RSAPublicKey publicKey = (RSAPublicKey) map
				.get(RSAUtils.RAS_Key_Public);
		RSAPrivateKey privateKey = (RSAPrivateKey) map
				.get(RSAUtils.RAS_Key_Private);

		request.getSession().setAttribute(RSAUtils.RAS_Key_Private, privateKey);// 私钥保存在session中，用于解密

		// 公钥信息保存在页面，用于加密
		String publicKeyExponent = publicKey.getPublicExponent().toString(16);
		String publicKeyModulus = publicKey.getModulus().toString(16);
		map.put("publicKeyExponent", publicKeyExponent);
		map.put("publicKeyModulus", publicKeyModulus);
		ResponseVo<String> rs = ResponseVo.<String> BUILDER();
		rs.setData(JSON.toJSONString(map));
		return rs;
	}

}
