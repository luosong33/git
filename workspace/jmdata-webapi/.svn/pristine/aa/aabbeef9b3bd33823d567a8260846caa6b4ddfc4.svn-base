package com.jumore.jmdata.common.auth;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jumore.dove.web.auth.DoveCasRealm;
import com.jumore.jmdata.common.util.properties.PropertiesBean;

public class CenterApiCasRealm extends DoveCasRealm {
	
	@Autowired
	private PropertiesBean propertiesBean;

	/**
	 * 初始化用户信息，放入session，CAS认证成功后执行
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("staticDomainUrl", propertiesBean.getStaticDomainUrl());// 静态文件全域名

		AuthenticationInfo info = super.doGetAuthenticationInfo(token);
		try {
			Map attrs = (Map) info.getPrincipals().asList().get(1);
			DataUserDTO dataUserDTO = new DataUserDTO();
			if (attrs.get("id") != null
					&& !((String) attrs.get("id")).equals("")) {// 用户ID
				dataUserDTO
						.setId(Long.parseLong(String.valueOf(attrs.get("id"))));
			}
			if (attrs.get("platform") != null) {// 平台编码
				dataUserDTO.setBizCode(String.valueOf(attrs.get("platform")));
			}
			if (attrs.get("mobile_no") != null) {// 手机号码
				dataUserDTO.setMobileNo(String.valueOf(attrs.get("mobile_no")));
			}
			if (attrs.get("status") != null) {// 使用状态 0待审核 1正常 4停用
				dataUserDTO.setStatus(String.valueOf(attrs.get("status")));
			}
			if (attrs.get("real_name") != null) {// 真实姓名
				dataUserDTO.setRealName(String.valueOf(attrs.get("real_name")));
			}
			if (attrs.get("user_type") != null) {// 用户类型，0企业用户，1平台用户（管理员）
													// 2平台用户（运营人员）,3物流供应商，4物流委托方
				dataUserDTO.setUserType(String.valueOf(attrs.get("user_type")));
			}
			if (attrs.get("jumore_user_id") != null) {// 认证中心USERID 备用
				dataUserDTO.setJumoreUserID(String.valueOf(attrs
						.get("jumore_user_id")));
			}
			if (attrs.get("mail") != null) {// 认证中心USERID 备用
				dataUserDTO.setMail(String.valueOf(attrs.get("mail")));
			}

			// 将用户对象放入session中
			SecurityUtils.getSubject().getSession()
					.setAttribute("user", dataUserDTO);
			// 公司信息可以根据需要放入session
			List principals = CollectionUtils.asList(new Object[] { dataUserDTO
					.getId() });
			SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(
					principals, this.getName());
			return new SimpleAuthenticationInfo(principalCollection,
					info.getCredentials());
		} catch (Throwable ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		DataUserDTO user = (DataUserDTO) SecurityUtils.getSubject()
				.getSession().getAttribute("user");
		return doAuthorization(user);
	}

	/**
	 * 加载权限:本系统就一类用户，无权限之分。
	 * 
	 * @param user
	 * @return
	 */
	private AuthorizationInfo doAuthorization(DataUserDTO user) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("default");// 添加默认权限
		return info;
	}

	@Override
	protected String getBizCode() {
		return "1016";
	}
}
