package com.yesun.sample.log4j;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.HierarchyEventListener;

public class Log4JSample extends Thread {

	Logger logger;// = Logger.getLogger(Log4JSample.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
//		LogManager.getLoggerRepository().addHierarchyEventListener(new HierarchyEventListener(){
//
//			@Override
//			public void addAppenderEvent(Category cat, Appender appender) {
//				System.out.println("addAppenderEvent ");
//			}
//
//			@Override
//			public void removeAppenderEvent(Category cat,
//					Appender appender) {
//				System.out.println("removeAppenderEvent ");
//				
//			}});
	}

	private String userid;
	private String appid;
	private String loggerName;
	private String appenderName;
	private String logFileName;

	public Log4JSample(String userid, String appid) {
		
		System.out.println(Thread.currentThread().getName()+ "Load Log4JSample....");

		this.userid = userid;
		this.appid = appid;
		// logName必须唯一，为userid + appid
		this.loggerName = userid + "_" + appid;
		// appendName同logName
		this.appenderName = userid + "_" + appid;

		// 动态生成appender,每一个用户应用一个Appender
		logger = Logger.getLogger(this.loggerName);// 唯一，每次都获取新的会有问题 TODO

		System.out.println(Thread.currentThread().getName() + "创建logger对象<<<" + userid + appid + logger.toString() + ">>>");
		
		Appender appender = logger.getAppender(this.appenderName);// 唯一
		if (appender == null) {
			// 不存在，则创建
			try {
				PatternLayout layout = new PatternLayout();
				layout.setConversionPattern("%d{ABSOLUTE} %5p - %m%n");
				DailyRollingFileAppender fappender = new DailyRollingFileAppender(
						layout, "Sample", "'-'yyyy-MM-dd'.log'");

				this.logFileName = "USER_" + this.userid + "_APP_" + this.appid
						+ "_" + fappender.getFile();
				fappender.setFile(this.logFileName);
				fappender.activateOptions();
				fappender.setName(this.appenderName);

				logger.setLevel(Level.DEBUG);
				logger.addAppender(fappender);
				// add console appender
				ConsoleAppender consoleAppendar = (ConsoleAppender) Logger
						.getRootLogger().getAppender("stdout");
				logger.addAppender(consoleAppendar);

				// int size = logger.appendLoopOnAppenders(new
				// LoggingEvent(){});

				System.out.println("创建appender对象<<<" + userid + appid + fappender.toString() +">>>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("读取appender对象<<<" + userid + appid + appender.toString() +">>>");
			logger.addAppender(appender);
		}
	}

	/**
	 * Description: execute Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-1-12 上午10:12:08 Create. ChangeLog:
	 */
	public void run() {
		logger.debug(Thread.currentThread().getName() + " User:" + userid + " APP:" + appid + " executing....");
		
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug(Thread.currentThread().getName() + "finished!");
	}

	/**
	 * Description:
	 * 
	 * @param args
	 *            Copyright (c) Digital Heaven. All Rights Reserved. 1.0 YESUN
	 *            2012-1-12 上午10:07:20 Create. ChangeLog:
	 */
	public static void main(String[] args) {

		Log4JSample sample;
		for (int i = 1; i <= 10; i++) {
			sample = new Log4JSample("yesun", "APP" + i);
			sample.start();

			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				Enumeration<?> em = LogManager.getLoggerRepository().getCurrentLoggers();
				while (em.hasMoreElements()) {
					Logger log = (Logger) em.nextElement();
					Enumeration<?> emu = log.getAllAppenders();
					while (emu.hasMoreElements()) {
						Object element = emu.nextElement();
						if (element instanceof DailyRollingFileAppender) {
							DailyRollingFileAppender fAppender = (DailyRollingFileAppender) element;
							fAppender.close();
							System.out.println("删除appender对象<<<" + fAppender.getName() + fAppender.toString()+">>>");
							log.removeAppender(fAppender);//释放appender，注意不能释放console appender
						}
					}
					System.out.println("删除logger对象<<<" + log.getName() + log.toString() + ">>>");
					//释放资源
					Hierarchy hie = (Hierarchy) log.getHierarchy();
					hie.clear();//释放缓存hashtable
					log = null;//释放logger
				}
				
				
				
				em = LogManager.getLoggerRepository().getCurrentLoggers();
				int i=0;
				while (em.hasMoreElements()) {
					Logger log = (Logger) em.nextElement();
					System.out.println("LOG:" + log.toString());
				}
			}}, 3000, 3000);

		
//		
//		
//		// 模拟定期清理logger和appender
//		Thread thread = new Thread(){
//			public void run(){
//				
//				try {
//					Thread.currentThread().sleep(4000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				Enumeration<?> em = LogManager.getLoggerRepository().getCurrentLoggers();
//				while (em.hasMoreElements()) {
//					Logger log = (Logger) em.nextElement();
//					Enumeration<?> emu = log.getAllAppenders();
//					while (emu.hasMoreElements()) {
//						Object element = emu.nextElement();
//						if (element instanceof DailyRollingFileAppender) {
//							DailyRollingFileAppender fAppender = (DailyRollingFileAppender) element;
//							fAppender.close();
//							System.out.println("删除appender对象<<<" + fAppender.getName() + fAppender.toString()+">>>");
//							log.removeAppender(fAppender);//释放appender，注意不能释放console appender
//						}
//					}
//					System.out.println("删除logger对象<<<" + log.getName() + log.toString() + ">>>");
//					log = null;//释放logger
//				}
//				
//				
//				em = LogManager.getLoggerRepository().getCurrentLoggers();
//				int i=0;
//				while (em.hasMoreElements()) {
//					Logger log = (Logger) em.nextElement();
//					System.out.println("LOG:" + log.toString());
//				}
//			}
//		};
//		thread.start();
		

		


		for (int i = 1; i <= 10; i++) {
			sample = new Log4JSample("yesun", "APP" + i);
			sample.start();

			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
