package cn.newtouch.application.config.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.newtouch.framework.orm.hibernate.AutoGenerator;
import lombok.Data;

/**
 * 配置字典实体类
 * 
 * @author lshl
 * @since 0.1
 */
@Data
@Entity
@Table(name = "CONF_DICTIONARY")
public class Dictionary implements Serializable {
	private static final long serialVersionUID = -7610108657592274423L;
	@Id
	@AutoGenerator.Tag
	private Long id;
	// 名称
	@Column(name = "name", unique = true, nullable = false, length = 200)
	private String name;
	// 中文名称
	@Column(name = "cn_name", nullable = false, length = 200)
	private String cnName;
	// 描述
	@Column(name = "description", length = 500)
	private String description;
	// 字典选项
	@OneToMany(mappedBy = "dictionary", cascade = CascadeType.ALL)
	private List<DictionaryItem> dictionaryItems = new ArrayList<DictionaryItem>();
}
