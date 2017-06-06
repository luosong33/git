package org.jumao.bi.entites.charts;

public class CommonTBean<T> {

	private String name;
	
	private T value;
	
	public CommonTBean(String name, T value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	

}
