package cn.newtouch.application.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.newtouch.framework.orm.hibernate.AutoGenerator;
import lombok.Data;

/**
 * 菜单实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_MENU")
public class Menu implements Serializable {
	private static final long serialVersionUID = 3488405380107404492L;
	// 菜单资源的根菜单标识为0
	public static final Long ROOT_MENU = 0L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 菜单名称
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	// 菜单描述
	@Column(name = "description", length = 500)
	private String description;
	// 排序字段
	@Column(name = "indexs")
	private Integer index;
	// 上级菜单
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Menu parent;
	// 子菜单列表（多对多关联）
	@OneToMany(mappedBy="parent")
	private List<Menu> children = new ArrayList<Menu>();
	//资源
	@OneToOne(mappedBy="menu")
	private Resource resource;
	
	public Long getPid() {
		return this.parent == null ? null : this.parent.id;
	}
	
}
