package cn.newtouch.framework.orm.hibernate;

public class LocalAutoGenerator extends AutoGenerator {

	private Long id;

	@Override
	protected void curr(Long id) {
		this.id = id;
	}

	@Override
	protected Long next() {
		return ++id;
	}

}
