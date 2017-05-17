package com.jumore.jmbi.dao.entity.permission;

import java.util.Date;

import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Sequence;
import com.jumore.dove.aop.Table;
import com.jumore.jmbi.dao.entity.base.BaseEntity;

@Entity
@Table(name = "perm_role")
public class Role extends BaseEntity {
    /**自增id*/
    @Column(name = "id")
    @Id
    @Sequence
    private Long    id;

    /**角色名称*/
    @Column(name = "name")
    private String  name;

    /**角色描述*/
    @Column(name = "description")
    private String  description;

    /**状态,0:启用,1:禁用*/
    @Column(name = "status")
    private Integer status;

    /**是否删除*/
    @Column(name = "delete_flag")
    private String  deleteFlag;

    /***/
    @Column(name = "language_code")
    private Long    languageCode;

    /***/
    @Column(name = "create_time")
    private Date    createTime;

    /***/
    @Column(name = "create_id")
    private Long    createId;

    /***/
    @Column(name = "update_time")
    private Date    updateTime;

    /***/
    @Column(name = "update_id")
    private Long    updateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Long getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(Long languageCode) {
        this.languageCode = languageCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }
}