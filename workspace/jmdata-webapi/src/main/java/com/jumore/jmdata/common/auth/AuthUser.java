package com.jumore.jmdata.common.auth;

public class AuthUser {

	private String mobileNo;
	
	private String password;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthUser [mobileNo=" + mobileNo + ", password=" + password + "]";
	}
	
	

}
