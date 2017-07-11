package cn.newtouch.application.web;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import cn.newtouch.application.security.annotation.BindAuthority;
import cn.newtouch.application.security.annotation.BindIgnore;
import cn.newtouch.application.security.entity.*;
import cn.newtouch.application.security.service.*;
import cn.newtouch.framework.event.ReloadEvent;
import cn.newtouch.framework.utils.Digests;

@Component
public class UrlsRegister implements ReloadEvent {

	@Autowired
	private RequestMappingHandlerMapping mapping;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private ResourceService resourceService;
	
	private Project project;
	

	@PostConstruct
	public void init() {
		project = projectService.getProject("portal");
		if (project == null) {
			project = new Project();
			project.setId("portal");
			project.setName("系统管理");
			project.setBaseUrl("/portal");
			project = projectService.save(project);
		}
		User user = userService.getUserByUsername("admin");
		if (user == null) {
			user = new User();
			user.setUsername("admin");
			user.setPlainPassword("admin");
			Digests.entryptPassword(user);
			user.setAdmin(1);
			user.setFullname("系统管理员");
			user = userService.save(user);
		}
		change();
	}

	public void change() {
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
		Map<String, Authority> authorities = new HashMap<String, Authority>();
		List<Authority> list1 = authorityService.findAuthorityByProjectId(project.getId());
		for (Authority authority : list1) {
			authorities.put(authority.getName(), authority);
		}
		Map<String, Resource> resources = new HashMap<String, Resource>();
		Set<String> resourceKeys = new HashSet<String>();
		List<Resource> list2 = resourceService.findResourceByProjectId(project.getId());
		for (Resource resource : list2) {
			resources.put(resource.getName(), resource);
		}
		for (Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
			Method method = entry.getValue().getMethod();
			BindAuthority authority = method.getAnnotation(BindAuthority.class);
			String domain = entry.getValue().getBeanType().getSimpleName();
			int index = domain.lastIndexOf("Controller");
			if (index != -1) {
				domain = domain.substring(0, index);
			}
			domain = domain.toLowerCase();
			String resource = domain + '.' + method.getName();
			if (authority != null) {
				String key = domain + ':' + authority.value();
				Authority _authority = authorities.get(key);
				if (_authority == null) {
					if (_authority == null) {
						_authority = new Authority(); 
						_authority.setName(key);
						_authority.setProject(project);
						_authority = authorityService.save(_authority);
						authorities.put(key, _authority);
					}
				}
				if (entry.getKey().getPatternsCondition().getPatterns().size() > 1) {
					System.err.println("1.该资源匹配超链接太多：" + entry.getValue() + entry.getValue());
					break;
				}
				if (!resourceKeys.add(resource)) {
					System.err.println("2.该资源重复出现（不支持同名方法）：" + entry.getValue() + entry.getValue());
				}
				Resource _resource = resources.get(resource);
				String source = entry.getKey().getPatternsCondition().getPatterns().iterator().next();
				if (_resource == null) {
					_resource = new Resource();
					_resource.setName(resource);
					if (method.getAnnotation(ResponseBody.class) != null) {
						_resource.setType("json");
					}
					_resource.setSource(source);
					_resource.setAuthority(_authority);
					_resource = resourceService.save(_resource);
					resources.put(resource, _resource);
				} else {
					int change = 0;
					boolean isJson = method.getAnnotation(ResponseBody.class) != null;
					if (isJson == !"json".equals(_resource.getType())) {
						if (isJson) {
							_resource.setType("json");
						} else {
							_resource.setType("html");
						}
						change++;
					} 
					if (source != null && !source.equals(_resource.getSource())) {
						_resource.setSource(source);
						change++;
					}
					if (change > 0) {
						resourceService.update(_resource);
					}
				}
			} else if(method.getAnnotation(BindIgnore.class) == null) {
				System.err.println("3.该资源没有绑定权限：" + entry.getValue() + entry.getValue());
			}
		}
	}

}
