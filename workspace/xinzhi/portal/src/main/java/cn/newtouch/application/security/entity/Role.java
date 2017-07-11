package cn.newtouch.application.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.newtouch.framework.orm.hibernate.AutoGenerator;
import lombok.Data;

/**
 * 角色实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 2041148498753846675L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 角色名称
	@Column(name = "name", unique = true, nullable = false, length = 200)
	private String name;
	// 角色描述
	@Column(name = "description", length = 500)
	private String description;
	// 角色所包含的用户列表（一对多关联）
	@OneToMany(mappedBy = "role")
	private List<UserRole> users = new ArrayList<UserRole>();
	// 角色拥有的权限列表（多对多关联）
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "SEC_ROLE_AUTHORITY", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "AUTHORITY_ID") })
	private List<Authority> authorities = new ArrayList<Authority>();
}
