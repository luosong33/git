package cn.newtouch.application.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.newtouch.framework.orm.hibernate.AutoGenerator;
import lombok.Data;

/**
 * 资源实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_RESOURCE")
public class Resource implements Serializable {
	private static final long serialVersionUID = 5503383469393219319L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 资源名称
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	// 类型
	@Column(name = "type", length = 10)
	private String type = "html";
	// 资源值（此处主要作为url资源，及链接路径）
	@Column(name = "source", length = 200, nullable = false)
	private String source;
	// 资源所属权限列表（多对多关联）
	@ManyToOne
	@JoinColumn(name = "authority")
	private Authority authority;
	// 资源所属菜单
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", unique = true, nullable = true)
	private Menu menu;
}
