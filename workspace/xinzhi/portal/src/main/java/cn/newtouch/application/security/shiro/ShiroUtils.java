package cn.newtouch.application.security.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.newtouch.application.security.entity.User;

/**
 * shiro工具类
 * 
 * @author yuqs
 * @since 0.1
 */
public class ShiroUtils {
	/**
	 * 返回当前登录的认证实体ID
	 * 
	 * @return
	 */
	public static Long getUserId() {
		ShiroPrincipal principal = getPrincipal();
		if (principal != null)
			return principal.getUser().getId();
		return null;
	}

	public static User getUser() {
		ShiroPrincipal principal = getPrincipal();
		if (principal != null)
			return principal.getUser();
		return null;
	}

	/**
	 * 返回当前登录的认证实体部门ID
	 * 
	 * @return
	 */
	public static String getOrgId() {
		User user = getUser();
		if (user != null && user.getCurrPost().getOrg() != null)
			return user.getCurrPost().getOrg().getId();
		return null;
	}

	/**
	 * 获取当前登录的认证实体
	 * 
	 * @return
	 */
	public static ShiroPrincipal getPrincipal() {
		Subject subject = SecurityUtils.getSubject();
		return (ShiroPrincipal) subject.getPrincipal();
	}

	/**
	 * 获取所有组集合
	 * 
	 * @return
	 */
	public static List<String> getGroups() {
		List<String> groups = new ArrayList<String>();
		String orgId = getOrgId();
		ShiroPrincipal principal = getPrincipal();
		if (principal != null) {
			groups.addAll(principal.getRoles());
		}
		if (orgId != null) {
			groups.add(String.valueOf(orgId));
		}
		return groups;
	}

	/**
	 * 获取当前认证实体的中文名称
	 * 
	 * @return
	 */
	public static String getFullname() {
		ShiroPrincipal principal = getPrincipal();
		if (principal != null)
			return principal.toString();
		return "";
	}

	/**
	 * 获取当前认证实体的登录名称
	 * 
	 * @return
	 */
	public static String getUsername() {
		ShiroPrincipal principal = getPrincipal();
		if (principal != null)
			return principal.getUsername();
		throw new RuntimeException("user's name is null.");
	}

	/**
	 * 获取当前认证的实体部门名称
	 * 
	 * @return
	 */
	public static String getOrgName() {
		User user = getUser();
		if (user != null)
			return user.getCurrPost().getOrg().getName();
		return "";
	}
}
