/**
 * File: TestRedisQuene.java
 * General 
 * @author luosong
 * version 1.0 2016年7月21日: 下午3:29:59
 * Copyright (C) 2008-2015 oneapm.com all rights reserved
 */
package cn.newtouch.test;

import java.util.Random;

import cn.newtouch.application.security.entity.RedisQueueMessage;
import cn.newtouch.framework.cache.JedisUtil;
import cn.newtouch.framework.utils.ObjectUtil;

public class TestRedisQuene {

	public static byte[] redisKey = "ffffffffff".getBytes();
//	static long t = System.currentTimeMillis();//获得当前时间的毫秒数
//    static Random rd = new Random(t);//作为种子数传入到Random的构造器中
//	public static byte[] redisKey = (rd.nextInt()+"").getBytes();
	
    static{
        try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private static void init() throws Exception {
    	JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes("字符串1"));
    	JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes("字符串2"));
    	JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes("字符串3"));
    	/*RedisQueueMessage msg1 = new RedisQueueMessage(1, "内容1");
        JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(msg1));
        RedisQueueMessage msg2 = new RedisQueueMessage(2, "内容2");
        JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(msg2));
        RedisQueueMessage msg3 = new RedisQueueMessage(3, "内容3");
        JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(msg3));*/
    }
    
    public static void main(String[] args) throws Exception {
        pop();
    }
 
    private static void pop() throws Exception {
        byte[] bytes = JedisUtil.rpop(redisKey);
        String msg = (String) ObjectUtil.bytesToObject(bytes);
//        RedisQueueMessage msg = (RedisQueueMessage) ObjectUtil.bytesToObject(bytes);
        if(msg != null){
            System.out.println(msg);
//            System.out.println(msg.getId()+"   "+msg.getContent());
        }
    }
 
}
