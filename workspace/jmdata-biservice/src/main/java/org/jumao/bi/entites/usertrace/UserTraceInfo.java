package org.jumao.bi.entites.usertrace;

import java.io.Serializable;

public class UserTraceInfo implements Serializable{
	
	private static final long serialVersionUID = 8822965560390351660L;
	private String bizCode;
	private String loginTime;
	private String deviceType;
	private String browseType;
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getBrowseType() {
        return browseType;
    }
    public void setBrowseType(String browseType) {
        this.browseType = browseType;
    }
	
	

}
