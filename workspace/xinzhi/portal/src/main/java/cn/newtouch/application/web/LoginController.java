package cn.newtouch.application.web;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtouch.application.security.annotation.BindIgnore;
import cn.newtouch.framework.web.Status;

/**
 * 系统登录Controller
 * 
 * @author yuqs
 * @since 0.1
 */
@Controller
public class LoginController {
	private static Log log = LogFactory.getLog(LoginController.class);

	@BindIgnore
	@RequestMapping("/login_view")
	public String login_view() {
		return "login";
	}

	@BindIgnore
	@RequestMapping("/login")
	@ResponseBody
	public Status login(String username, String password, String remember) {
		if (StringUtils.isBlank(username)) {
			return Status.fail("用户名不能为空！");
		}
		if (StringUtils.isBlank(password)) {
			return Status.fail("密码不能为空！");
		}
		log.info("Login user=====" + username);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		log.info("remember=" + remember);
		try {
			if (remember != null && remember.equalsIgnoreCase("on")) {
				token.setRememberMe(true);
			}
			subject.login(token);
			return Status.ok();
		} catch (UnknownAccountException ue) {
			token.clear();
			return Status.fail("登录失败，您输入的用户名不存在");
		} catch (IncorrectCredentialsException ie) {
			token.clear();
			return Status.fail("登录失败，密码不匹配");
		} catch (RuntimeException re) {
			token.clear();
			return Status.fail("登录失败");
		}
	}

}
