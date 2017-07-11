package cn.newtouch.application.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * 部门实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_ORG")
public class Org implements Serializable {
	private static final long serialVersionUID = 7297765946510001885L;
	public static final String ROOT_ORG_ID = "00000000";
	// 主键标识ID
	@Id
	protected String id;
	// 部门名称
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	// 部门全称
	@Column(name = "fullname", length = 200)
	private String fullname;
	// 部门描述
	@Column(name = "description", length = 500)
	private String description;
	// 是否激活状态
	@Column(name = "enabled", length = 1)
	private Integer enabled;
	// 上级部门
	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private Org parent;
	// 部门管辖的所有下级部门列表（一对多关联）
	@OneToMany(mappedBy = "parent")
	private List<Org> children = new ArrayList<Org>();
	// 部门管辖的所有用户列表（一对多关联）
	@OneToMany(mappedBy = "org")
	private List<Post> posts = new ArrayList<Post>();
}
