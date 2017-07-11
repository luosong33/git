package cn.newtouch.application.config.entity;

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
 * 配置字典选项实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "CONF_DICTITEM")
public class DictionaryItem implements Serializable {
	private static final long serialVersionUID = -1382491728106297904L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 字典选项名称
	@Column(name = "name", unique = true, nullable = false, length = 200)
	private String name;
	// 编码（手动设置）
	@Column(name = "code", length = 50)
	private String code;
	// 描述
	@Column(name = "description", length = 500)
	private String description;
	// 字段选项排序字段
	@Column(name = "orderby")
	private Integer orderby;
	// 配置字典实体（关联）
	@ManyToOne
	@JoinColumn(name = "dictionary")
	private Dictionary dictionary;
}
