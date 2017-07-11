/**
 * File: RedisReadWrite.java
 * General 
 * @author luosong
 * version 1.0 2016年7月21日: 下午2:59:37
 * Copyright (C) 2008-2015 oneapm.com all rights reserved
 */
package cn.newtouch.application.web;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.newtouch.framework.cache.JedisUtil;
import cn.newtouch.framework.utils.ObjectUtil;

@Controller
@RequestMapping("/RedisReadWrite")
public class RedisReadWrite {
	
	static long t = System.currentTimeMillis();//获得当前时间的毫秒数
    static Random rd = new Random(t);//作为种子数传入到Random的构造器中
	public static byte[] redisKey = (rd.nextInt()+"").getBytes();
	
	@RequestMapping(value = "/lpush.do", method = RequestMethod.POST)
	public void lpush(String data) {
		try {
			JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/rpop.do", method = RequestMethod.POST)
	public String rpop(){
		byte[] bytes = JedisUtil.rpop(redisKey);
		String msg = "";
		try {
			msg = (String) ObjectUtil.bytesToObject(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	

	public static void main(String[] args) {

	}

}
