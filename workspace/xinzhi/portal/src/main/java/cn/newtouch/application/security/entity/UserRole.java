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
@Table(name = "SEC_USER_ROLE")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 7446802057673100315L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	//授权权限
	@Column(name = "give", length = 1)
	private Integer give;
	// 所属用户
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	// 角色列表
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
}
