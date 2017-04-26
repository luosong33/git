package org.jumao.bi.entites.usertrace;

import java.io.Serializable;

public class UserTraceInfo implements Serializable{
	
	private static final long serialVersionUID = 8822965560390351660L;
	private String bizCode;
	private String loginTime;
	private String userAgent;
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
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	

}
