package com.yesun.sample.threadlocal;

public class ThreadLocalConfig {
	
	//实现线程内的全局变量，而非多线程全局变量，各线程间互不相干
	private static ThreadLocal<AppConfig> local = new ThreadLocal<AppConfig>();
	
	public static AppConfig getConfig() {
		return local.get();
	}
	
	public static void setConfig(AppConfig config) {
		local.set(config);
	}

}
