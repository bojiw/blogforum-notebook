package com.blogforum.notebook.service.rabbitmq.consumer;

import org.springframework.amqp.core.Message;

public interface Handler {

	/**
	 * 用户注册时笔记系统一些初始化准备
	 * @param message
	 * @author: wwd
	 * @time: 2017年11月21日
	 */
	public void doHandler(Message message);
	
}
