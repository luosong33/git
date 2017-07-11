package cn.newtouch.application.security.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtouch.application.security.annotation.BindAuthority;
import cn.newtouch.application.security.entity.Menu;
import cn.newtouch.application.security.service.MenuService;
import cn.newtouch.framework.web.Status;

/**
 * 系统登录Controller
 * 
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping("/security/menu")
public class MenuController {
	private static Log log = LogFactory.getLog(MenuController.class);
	
	@Autowired
	private MenuService menuService;

	@BindAuthority("manager")
	@RequestMapping("manager")
	public String manager(Model model) {
		return "/security/menu/manager";
	}

	@ResponseBody
	@BindAuthority("manager")
	@RequestMapping("all")
	public List<Menu> all() {
		return menuService.getAll();
	}

	@ResponseBody
	@BindAuthority("create")
	@RequestMapping("create")
	public Status create(String name, HttpServletRequest request) {
		if (StringUtils.isEmpty(name)) {
			return Status.fail("菜单名称不能为空!");
		}
		Long pid = null;
		String pidStr = request.getParameter("pid");
		if (StringUtils.isNotEmpty(pidStr)) {
			pid = Long.parseLong(pidStr);
		}
		Menu menu = menuService.create(pid, name);
		return Status.ok(menu);
	}

	@ResponseBody
	@BindAuthority("update")
	@RequestMapping("update")
	public Status update(Long id, String name, String description) {
		if (id == null) {
			return Status.fail("ID不能为空!");
		}
		if (StringUtils.isEmpty(name)) {
			return Status.fail("菜单名称不能为空!");
		}
		menuService.update(id, name, description);
		return Status.ok();
	}

	@ResponseBody
	@BindAuthority("update")
	@RequestMapping("update_postion")
	public Status update_postion(Long id, Long pid, Integer index) {
		if (id == null) {
			return Status.fail("ID不能为空!");
		}
		if (index == null) {
			return Status.fail("菜单序号不能为空!");
		}
		menuService.updatePostion(id, pid, index);
		return Status.ok();
	}

	@ResponseBody
	@BindAuthority("delete")
	@RequestMapping("delete")
	public Status delete(Long id) {
		if (id == null) {
			return Status.fail("ID不能为空!");
		}
		menuService.delete(id);
		return Status.ok();
	}

}
