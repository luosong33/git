package cn.newtouch.application.security.shiro;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import cn.newtouch.application.security.entity.Resource;
import cn.newtouch.application.security.service.ResourceService;
import cn.newtouch.framework.event.ReloadEvent;

/**
 * 授权元数据根据两部分构成： 1、数据库中动态生成，由注入的resourceManager根据资源、权限构造资源-权限的键值对
 * 2、使用spring的注入filterChainDefinitions，在配置文件中定义默认的安全定义，如登录页面，首页等
 * 
 * @author yuqs
 * @since 0.1
 */
public class ShiroDefinitionSectionMetaSource implements FactoryBean<Ini.Section>, ReloadEvent {
	private static Log log = LogFactory.getLog(ShiroDefinitionSectionMetaSource.class);
	// 注入资源管理对象
	private ResourceService resourceService;
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	// 注入默认的授权定义
	private String filterChainDefinitions;
	// 格式化数据，符合shiro的格式，如：perms["admin"]
	public static final String PREMISSION_FORMAT = "perms[\"{0}\"]";

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	public void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		this.shiroFilterFactoryBean = shiroFilterFactoryBean;
	}

	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		// 由注入的资源管理对象获取所有资源数据，并且Resource的authorities的属性是EAGER的fetch类型
		List<Resource> resources = resourceService.getAll();
		if (resources != null)
			for (Resource resource : resources) {
				putDefinitionSection(section, resource.getSource(), resource.getAuthority().getName());
			}
		section.put("/**", "user");
		return section;
	}

	private void putDefinitionSection(Section section, String key, String value) {
		log.info("加载数据库权限：【key=" + key + "\tvalue=" + value + "】");
		section.put(key, MessageFormat.format(PREMISSION_FORMAT, value.replaceAll("\\{\\w+\\}", "*")));
	}
	
	@Override
	public void change() {
		AbstractShiroFilter shiroFilter = null;  
		  
        try {  
            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();  
        } catch (Exception e) {  
            log.error(e.getMessage());  
        }  

        // 获取过滤管理器  
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter  
                .getFilterChainResolver();  
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();  

        // 清空初始权限配置  
        manager.getFilterChains().clear();  
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();  
        
		try {
			Map<String, String> chains = getObject();
			shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);
			  
            for (Map.Entry<String, String> entry : chains.entrySet()) {  
                String url = entry.getKey();  
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
