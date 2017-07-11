package cn.newtouch.application.security.web;

import java.util.List;

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
import cn.newtouch.application.security.entity.Resource;
import cn.newtouch.framework.web.Status;

/**
 * 系统登录Controller
 * 
 * @author yuqs
 * @since 0.1
 */
@Controller("/security/resource")
public class ResourceController {
	private static Log log = LogFactory.getLog(ResourceController.class);

	@BindIgnore
	@RequestMapping(value = "without_menu", method = RequestMethod.POST)
	@ResponseBody
	public List<Resource> without_menu() {
		return null;
	}

}
