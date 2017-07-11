package cn.newtouch.application.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * 项目实体类，继承抽象安全实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "SEC_PROJECT")
public class Project implements Serializable {
	private static final long serialVersionUID = 5503383469393219319L;
	// 主键标识ID
	@Id
	private String id;
	// 项目名称
	@Column(name = "name", unique = true, nullable = false, length = 200)
	private String name;
	// 基础链接
	@Column(name = "source", length = 200)
	private String baseUrl;
	// 资源列表（一对多关联）
	@OneToMany(mappedBy="project")
	private List<Authority> authorities = new ArrayList<Authority>();

}
