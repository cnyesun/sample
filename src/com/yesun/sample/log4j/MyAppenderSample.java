package com.yesun.sample.log4j;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class MyAppenderSample extends Thread {
	
	static Logger logger = Logger.getLogger(MyAppenderSample.class);
	
	static{
		PropertyConfigurator.configure("log4j.properties");
	}
	
	private String userid;
	private String appid;
	
	private String appenderName = "RING";
	private String logFileName;
	public MyAppenderSample(String userid, String appid){
		
		this.userid = userid;
		this.appid = appid;
		
		appenderName = this.appid;//一个用户一个appender
		Appender appender = logger.getAppender(this.appenderName);
		
		if(appender == null){
			//不存在，则创建
			try {
				PatternLayout layout = new PatternLayout();
				layout.setConversionPattern("%d{ABSOLUTE} %5p - %m%n");
				MyDailyRollingFileAppender fappender = new MyDailyRollingFileAppender(this.userid, this.appid ,layout, "Sample", "'-'yyyy-MM-dd'.log'");
				this.logFileName = "USER_" + this.userid +"_APP_" +this.appid +"_"+ fappender.getFile();
				fappender.setFile(logFileName);
				fappender.activateOptions();
				fappender.setName(this.appenderName);
				
				logger.setLevel(Level.DEBUG);
				logger.addAppender(fappender);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			MyDailyRollingFileAppender fappender = (MyDailyRollingFileAppender)appender;
			this.logFileName = "USER_" + this.userid +"_APP_" +this.appid +"_"+ fappender.getFile();
			fappender.setFile(logFileName);
			fappender.activateOptions();
			logger.addAppender(appender);
		}
	}

	
	
	/**
	 * Description: execute
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-1-12 上午10:12:08 Create.
	 * ChangeLog:
	 */
	public void run(){

//		for(int i=0;i<10;i++){
		
			logger.debug(Thread.currentThread().getName() + " User:" + userid + " APP:" + appid + " executing....");
			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
//		try {
//			Thread.sleep(60000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		logger.debug(Thread.currentThread().getName() + "finished!");
	}
	

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-1-12 上午10:07:20 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		
		MyAppenderSample sample ;
		for(int i=1; i<=10; i++){
			sample = new MyAppenderSample("yesun", "APP" + i);
			sample.start();
		}
//		for(int i=1; i<=3; i++){
//			sample = new Log4JSample("yesun", "APP" + i);
//			sample.start();
//		}
		

	}

}
