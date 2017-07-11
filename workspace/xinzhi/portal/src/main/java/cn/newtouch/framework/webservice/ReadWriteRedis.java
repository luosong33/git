/**
 * File: ReadWriteRedis.java
 * General 
 * @author luosong
 * version 1.0 2016年7月21日: 上午10:13:59
 * Copyright (C) 2008-2015 oneapm.com all rights reserved
 */
package cn.newtouch.framework.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(serviceName = "http://tempuri.org/ReadWriteRedis")
public class ReadWriteRedis {
	
	public String getString(){
		return "redis";
	}

	public static void main(String[] args) {
		
		Endpoint.publish("http://localhost:9000/Service/ReadWriteRedis", new ReadWriteRedis());
		System.out.println("success");
	}

}
