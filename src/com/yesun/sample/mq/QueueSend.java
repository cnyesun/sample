package com.yesun.sample.mq;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * sample QueueSend.java
 * Description: 测试队列发送消息
 * 
 * ActiveMQ是JMS规范的实现
 * 
 * 
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN Nov 30, 2012 2:10:17 PM Create.
 * ChangeLog:
 */
public class QueueSend {
	
	private static final int SEND_NUMBER = 10;

	public static void sendMessage(Session session, MessageProducer producer) throws Exception {
	  for (int i = 1; i <=SEND_NUMBER; i++) {
		TextMessage message = session.createTextMessage("ActiveMq 发送的消息" + i);
		//发送消息到目的地方
		System.out.println(new Date() + "发送消息:" + i+"成功");
		producer.send(message);
	  }
	}

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN Nov 30, 2012 2:09:53 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁(发送到队列，此队列需要在管理平台创建).
		Destination destination;
		// MessageProducer：消息发送者
		MessageProducer producer;
		// TextMessage message;
		
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");

		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			// myqueue需要在admin界面创建
			destination = session.createQueue("myqueue");
			// 得到消息生成者
			producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 构造消息，此处写死，项目就是参数，或者方法获取
			sendMessage(session, producer);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}

	}

}
