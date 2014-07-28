package com.yesun.sample.jms;

import java.util.Properties;

import javax.jms.Session;
import javax.naming.Context;

public class MessageProducter {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String queueConnectionFactoryName = "myJMSConnectionFactory";
		String queueName = "myJMSQueue";
		boolean transcated = false;
		int acknowledgementMode = Session.AUTO_ACKNOWLEDGE;
		String message = "message to send...";
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "");
		properties.put(Context.PROVIDER_URL, "t3://localhost:7001");

	}

}
