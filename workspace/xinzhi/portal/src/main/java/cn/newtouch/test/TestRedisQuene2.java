/**
 * File: TestRedisQuene.java
 * General 
 * @author luosong
 * version 1.0 2016年7月21日: 下午3:29:59
 * Copyright (C) 2008-2015 oneapm.com all rights reserved
 */
package cn.newtouch.test;

import cn.newtouch.framework.cache.MyShardedJedisPool;
import cn.newtouch.framework.utils.ObjectUtil;
import redis.clients.jedis.ShardedJedis;

public class TestRedisQuene2 {

	static{
		try {
			init();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] redisKey = "jhgfds33".getBytes();
	static ShardedJedis jedis = MyShardedJedisPool.getShardedJedisPool().getResource();
	
	private static void init() throws Exception  {
		jedis.lpush(redisKey, ObjectUtil.objectToBytes("星期一"));
        jedis.lpush(redisKey, ObjectUtil.objectToBytes("444444444"));
	}

	public static void main(String[] args) throws Exception {
		byte[] bytes = jedis.rpop(redisKey);
		String msg = (String) ObjectUtil.bytesToObject(bytes);
		if(msg != null){
            System.out.println(msg);
        }
    }
	
}
