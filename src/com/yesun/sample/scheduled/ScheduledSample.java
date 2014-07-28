package com.yesun.sample.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledSample {
	

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-3-15 上午9:57:59 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				System.out.println("task start...");
				System.out.println(Thread.currentThread().getName()+" -> "+System.currentTimeMillis());
				
			}}, 1, 1, TimeUnit.MINUTES);

	}

}
