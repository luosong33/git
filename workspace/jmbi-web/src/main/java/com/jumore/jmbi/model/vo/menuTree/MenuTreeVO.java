package com.jumore.jmbi.model.vo.menuTree;


public class MenuTreeVO {

	private Integer id;

	private String name;

	private Integer parentId;

	private SubItems subItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public SubItems getSubItems() {
		return subItems;
	}

	public void setSubItems(SubItems subItems) {
		this.subItems = subItems;
	}

}