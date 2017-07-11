/**
 * File: RedisQueueMessage.java
 * General 
 * @author luosong
 * version 1.0 2016年7月21日: 下午3:15:32
 * Copyright (C) 2008-2015 oneapm.com all rights reserved
 */
package cn.newtouch.application.security.entity;

import java.io.Serializable;

/**
 * 自定义redis消息实体类，可用于接收消息内容和消息下表的设置
 * @author Administrator
 *
 */
public class RedisQueueMessage implements Serializable {

	private static final long serialVersionUID = 7792729L;
	
	public RedisQueueMessage(int id , String content){
		this.id = id;
		this.content = content;
	}
	
    private int id;
    private String content;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
	
}
