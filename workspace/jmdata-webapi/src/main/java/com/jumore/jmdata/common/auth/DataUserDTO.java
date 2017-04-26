package com.jumore.jmdata.common.auth;

import java.io.Serializable;

/**
 * @description:
 * @author: tanyikuang
 * @since: 2016/11/5
 * @history:
 */
public class DataUserDTO implements Serializable {

    private static final long serialVersionUID = -1407672160853580928L;
    private Long id;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String mobileNo;

    private String bizCode;

    private String status;

    private String userType;

    private String jumoreUserID;
    
    private String mail;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getJumoreUserID() {
        return jumoreUserID;
    }

    public void setJumoreUserID(String jumoreUserID) {
        this.jumoreUserID = jumoreUserID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
}
