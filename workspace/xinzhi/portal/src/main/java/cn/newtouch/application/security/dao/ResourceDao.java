package cn.newtouch.application.security.dao;

import cn.newtouch.application.security.entity.Resource;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import org.springframework.stereotype.Component;

/**
 * 资源持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class ResourceDao extends HibernateDao<Resource, Long> {

}
