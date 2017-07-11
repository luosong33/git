/**
 * File: ShardedJedisPool.java
 * General 
 * @author luosong
 * version 1.0 2016年7月21日: 下午3:56:10
 * Copyright (C) 2008-2015 oneapm.com all rights reserved
 */
package cn.newtouch.framework.cache;

import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MyShardedJedisPool {
	
	private static ShardedJedisPool shardedJedisPool;
	
	// 静态代码初始化池配置
    static {
        //change "maxActive" -> "maxTotal" and "maxWait" -> "maxWaitMillis" in all examples
        JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(5);
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setMaxTotal(-1);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(5);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //创建四个redis服务实例，并封装在list中
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(new JedisShardInfo("127.0.0.1", 6379)); // windows本地redis
//        list.add(new JedisShardInfo("192.168.92.109", 6379)); // 远程linux虚拟机
        //创建具有分片功能的的Jedis连接池
        shardedJedisPool = new ShardedJedisPool(config, list);
    }

    public static ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public static ShardedJedis getResource() {
        return shardedJedisPool.getResource();
    }
	
}
