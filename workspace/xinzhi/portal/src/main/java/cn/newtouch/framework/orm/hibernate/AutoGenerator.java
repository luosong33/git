package cn.newtouch.framework.orm.hibernate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import javax.persistence.Column;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import lombok.Getter;
import lombok.Setter;

public abstract class AutoGenerator {

	@Target(value = { java.lang.annotation.ElementType.FIELD })
	@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
	@Documented
	public @interface Tag {
	}
	
	@Setter
	private SessionFactory sessionFactory;
	private Field field;
	@Getter
	private String key;
	
	public boolean need(){
		return field != null;
	}
	
	protected abstract void curr(Long id);

	protected abstract Long next();

	private Long max(String clazz, String field) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "select max(" + field + ") from " + clazz;
			return (Long) session.createQuery(hql).uniqueResult();
		} finally {
			session.close();
		}
	}

	private void initID(String clazz, String field) {
		key = clazz + '.' + field;
		Long id = max(clazz, field);
		if (id == null) {
			id = 0L;
		}
		curr(id);
	}

	public void init(Class<?> entityClass) {
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(AutoGenerator.Tag.class)) {
				this.field = field;
				Column column = field.getAnnotation(Column.class);
				initID(entityClass.getSimpleName(), column == null ? field.getName() : column.name());
				break;
			}
		}
	}

	public void generatorID(Object entity) {
		if (entity == null) {
			return;
		}
		try {
			field.setAccessible(true);
			field.set(entity, next());
		} catch (Exception e) {
			throw new RuntimeException("id generator error", e);
		}
	}

}
