package cn.newtouch.application.security.dao;

import cn.newtouch.application.security.entity.Post;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import org.springframework.stereotype.Component;

/**
 * 角色持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class PostDao extends HibernateDao<Post, Long> {

}