package com.jumore.jmbi.service.impl.permission;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jumore.jmbi.common.util.Consts;
import com.jumore.jmbi.dao.entity.permission.Account;
import com.jumore.jmbi.service.permission.IAuthorizationService;

public class LoRealm extends AuthorizingRealm {

	@Resource
	private IAuthorizationService authorizationService;

	// @Override
	// protected AuthorizationInfo doGetAuthorizationInfo(
	// PrincipalCollection principals) {
	// SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	// Account account = (Account) SecurityUtils.getSubject().getSession()
	// .getAttribute(Consts.Session_User_Key);
	// if ("admin".equals(account.getAccount())) {
	// info.addStringPermission("*");
	// }
	// info.addRole("default");
	// return info;
	// }

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new RuntimeException("用户名或密码不能为空");
		}
		Account vo = new Account();
		vo.setAccount(username);
		vo.setPassword(password);
		vo.setIsDel(0);//未删除
//		vo.setStatus(0);//启用：状态
		Account po = (Account) authorizationService.getByExample(vo);
		if (po == null) {
			throw new RuntimeException("用户名或密码不正确");
		}
		if (po.getStatus() == 1) {
			throw new RuntimeException("该用户已被禁用");
		}
		
		// 修改登陆时间
		// accountService.updateLastLoginTime(po.getId());
		// 组装头像路径
		// if (StringUtils.isNotEmpty(po.getHeadImg())) {
		// String imgViewAddress = propertiesBean.getImgViewAddress();
		// po.setHeadImg(imgViewAddress + po.getHeadImg());
		// }

		// //记录用户登录信息
		// UserLog entity = new UserLog();
		//
		// entity.setAccount(po.getAccount());
		// entity.setPassword(po.getPassword());
		// entity.setMobile(po.getMobile());
		// entity.setRemarks("");
		//
		// userLogService.saveLog(entity);

		// 用户信息放入session
		SecurityUtils.getSubject().getSession()
				.setAttribute(Consts.Session_User_Key, po);
		return new SimpleAuthenticationInfo(username, password, getName());

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission("*");
		info.addRole("default");
		return info;
	}
}