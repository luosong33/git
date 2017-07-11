package cn.newtouch.framework.web;

import java.util.List;

public class Pager<T> {

	private Long total;
	private List<T> rows;

	public Pager(Long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}
	
	public List<T> getRows() {
		return rows;
	}

}
