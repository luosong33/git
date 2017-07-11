package cn.newtouch.application.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "SEC_POST_DATA")
public class PostData implements Serializable {
	private static final long serialVersionUID = 7446802057673100315L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 名称
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	// 网销渠道
	@Column(name = "chnl_net", length = 1)
	private Integer chnlNet;
	// 电销渠道
	@Column(name = "chnl_phone", length = 1)
	private Integer chnlPhone;
	// 角色列表（多对多关联）
	@OneToMany(mappedBy = "data")
	private List<Post> posts = new ArrayList<Post>();
}
