package cn.newtouch.application.security.dao;

import cn.newtouch.application.security.entity.Project;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import org.springframework.stereotype.Component;

/**
 * 项目持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class ProjectDao extends HibernateDao<Project, String> {

}
