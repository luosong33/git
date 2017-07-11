package cn.newtouch.application.security.dao;

import cn.newtouch.application.security.entity.UserRole;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import org.springframework.stereotype.Component;

/**
 * 用户持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class UserRoleDao extends HibernateDao<UserRole, Long> {

}
