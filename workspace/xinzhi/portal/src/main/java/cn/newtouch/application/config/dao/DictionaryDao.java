package cn.newtouch.application.config.dao;

import cn.newtouch.application.config.entity.Dictionary;
import cn.newtouch.framework.orm.hibernate.HibernateDao;

import org.springframework.stereotype.Component;

/**
 * 配置字典持久化类
 * 
 * @author yuqs
 * @since 0.1
 */
@Component
public class DictionaryDao extends HibernateDao<Dictionary, Long> {

}
