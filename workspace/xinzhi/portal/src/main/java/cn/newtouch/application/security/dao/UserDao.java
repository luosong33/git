package cn.newtouch.application.security.dao;

import cn.newtouch.application.security.entity.User;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import org.springframework.stereotype.Component;

/**
 * 用户持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class UserDao extends HibernateDao<User, Long> {

}
