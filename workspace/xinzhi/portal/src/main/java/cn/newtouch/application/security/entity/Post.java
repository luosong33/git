package cn.newtouch.application.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.newtouch.framework.orm.hibernate.AutoGenerator;
import lombok.Data;

/**
 * 岗位实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_POST")
public class Post implements Serializable {
	private static final long serialVersionUID = 7446802057673100315L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 名称
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	// 是否可用
	@Column(name = "enabled", length = 1)
	private Integer enabled;
	// 所属用户
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	// 所属部门
	@ManyToOne
	@JoinColumn(name = "org_id", nullable = true)
	private Org org;
	// 角色列表（多对多关联）
	@ManyToOne
	@JoinColumn(name = "data_id", nullable = true)
	private PostData data;

	public Post() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
	
	public PostData getData() {
		return data;
	}
	
	public void setData(PostData data) {
		this.data = data;
	}

}
