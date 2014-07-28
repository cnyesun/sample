package com.yesun.sample.threadlocal;

import java.util.Random;

public class MyService {
	
	
	public void work() {
		
		//在同一线程的任何代码中，都可以通过ThreadLocal获取config对象，避免了通过参数传递的繁琐
		//也避免了普通全局静态变量在所有线程中可能需要做数据同步的问题
		
		AppConfig config = ThreadLocalConfig.getConfig();
		System.out.println(Thread.currentThread().getName() + "static thread local config" + config + config.getAppid());//虽然是静态变量，但是多线程打出来的结果也不一样
		
		//每个线程都修改config
		config.setAppid(Thread.currentThread().getName() + " MODIFY");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": "+config.getAppid());
		
	}

}
