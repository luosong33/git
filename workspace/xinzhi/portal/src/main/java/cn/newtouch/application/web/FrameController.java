package cn.newtouch.application.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtouch.application.security.annotation.BindAuthority;
import cn.newtouch.application.security.annotation.BindIgnore;
import cn.newtouch.application.security.entity.Authority;
import cn.newtouch.application.security.entity.Menu;
import cn.newtouch.application.security.entity.Resource;
import cn.newtouch.application.security.service.MenuService;
import cn.newtouch.application.security.service.ResourceService;
import cn.newtouch.framework.web.Pager;

/**
 * 平台级别Controller
 * 
 * @author yuqs
 * @since 0.1
 */
@Controller
public class FrameController {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private ResourceService resourceService;
	
	@BindIgnore
	@RequestMapping("/frame")
	public String frame(Model model) {
		return "frame2";
	}

	@ResponseBody
	@BindAuthority("manager")
	@RequestMapping("menus")
	public List<Menu> menus() {
		return menuService.getAll();
	}

	@BindAuthority("dev")
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		return "welcome";
	}

	@ResponseBody
	@BindAuthority("dev")
	@RequestMapping("/links")
	public Pager<Resource> links(int page, int pagesize) {
		return resourceService.getPagerLinksWithoutMenu(page, pagesize);
	}
	
}
