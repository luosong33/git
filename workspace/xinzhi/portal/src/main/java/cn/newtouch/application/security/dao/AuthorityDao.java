package cn.newtouch.application.security.dao;

import cn.newtouch.application.security.entity.Authority;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 权限持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class AuthorityDao extends HibernateDao<Authority, Long> {
	
	public List<String> findAllName() {
		String hql = "select a.name from Authority a";
		return createQuery(hql).list();
	}
	
	public List<String> findNameByPostId(Long userId) {
		String hql = "select a.name from Authority a join a.roles r join r.users u where u.id = ?";
		return distinct(createQuery(hql, userId)).list();
	}

}
