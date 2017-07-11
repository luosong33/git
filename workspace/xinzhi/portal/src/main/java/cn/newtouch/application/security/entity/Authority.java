package cn.newtouch.application.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.newtouch.framework.orm.hibernate.AutoGenerator;
import lombok.Data;

/**
 * 权限实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_AUTHORITY")
public class Authority implements Serializable {
	private static final long serialVersionUID = -8349705525996917628L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 权限名称
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	// 权限描述
	@Column(name = "description", length = 500)
	private String description;
	// 资源所属项目
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	// 权限管辖的资源列表（多对多关联）
	@OneToMany(mappedBy = "authority")
	private List<Resource> resources = new ArrayList<Resource>();
	// 权限所属的角色列表（多对多关联）
	@ManyToMany(mappedBy = "authorities", cascade=CascadeType.ALL)
	private List<Role> roles = new ArrayList<Role>();
}
