package cn.newtouch.application.security.dao;

import cn.newtouch.application.security.entity.Org;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import org.springframework.stereotype.Component;

/**
 * 部门持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class OrgDao extends HibernateDao<Org, String> {

}
