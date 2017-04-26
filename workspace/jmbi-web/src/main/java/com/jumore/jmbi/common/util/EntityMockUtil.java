package com.jumore.jmbi.common.util;

public class EntityMockUtil {

	
	public static String getAllCharString(){
		return "`1234567890-=~!@#$%^&*()_+qwertyuiop[]asdfghjkl;'\\zxcvbnm,./<>?:\"|{}";
	}
	
	public static String getChinseseText(){
		return "���Ĳ����ַ�";
	}
	
	public static String getLongText(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<500;i++){
			sb.append("");
		}
		return sb.toString();
	}
}
