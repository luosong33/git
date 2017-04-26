/**
 * 
 */
package com.jumore.jmbi.model.param.permission;

import com.jumore.jmbi.model.param.PageQueryParam;

/** 
*
* @author: luochao
* @since: 2016年7月29日  下午5:17:31
* @history:
*/
public class RoleParam extends PageQueryParam {

    /**
     * 
     */
    private static final long serialVersionUID = -3309119072112569726L;
    /**主键*/
    private Long              id;

    /**角色名称*/
    private String            name;

    /**状态*/
    private Integer           status;
    /**描述*/
    private String            description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
