package cn.newtouch.application.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.newtouch.framework.orm.hibernate.AutoGenerator;
import lombok.Data;

/**
 * 用户实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 7446802057673100315L;
	// 系统管理员账号类型
	public static final Integer TYPE_ADMIN = 0;
	// 普通用户账号类型
	public static final Integer TYPE_GENERAL = 1;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 登录用户名称
	@Column(name = "username", length = 50, unique = true)
	private String username;
	// 密码
	@Column(name = "password", length = 50)
	private String password;
	// 明文密码
	@Transient
	private String plainPassword;
	// salt
	@Column(name = "salt")
	private String salt;
	// 用户姓名
	@Column(name = "fullname", length = 100)
	private String fullname;
	// 电子邮箱
	@Column(name = "email", length = 100, nullable = true)
	private String email;
	// 是否超级管理员
	@Column(name = "admin", length = 1)
	private Integer admin = 0;
	// 是否可用
	@Column(name = "enabled", length = 1)
	private Integer enabled = 1;
	// 岗位列表（多对多关联）
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<Post>();
	// 当前职位
	@Transient
	private Post currPost;
	// 角色列表
	@OneToMany(mappedBy = "user")
	private List<UserRole> roles = new ArrayList<UserRole>();
}
