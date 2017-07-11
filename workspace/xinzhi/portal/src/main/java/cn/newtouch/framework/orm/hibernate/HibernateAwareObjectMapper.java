package cn.newtouch.framework.orm.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate3.Hibernate3Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -3953563979038527291L;

	public HibernateAwareObjectMapper() {
		Hibernate3Module hm = new Hibernate3Module();
		registerModule(hm);
	}
}
