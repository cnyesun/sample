package com.yesun.sample.thread;

import java.util.concurrent.CountDownLatch;

/**
 * sample CountDownLatchSample.java
 * Description: CountDownLatch提供的一个可用于控制多个线程都执行完毕后开始某动作的类
 * 如老板需要等待所有工人完成任务，在统一检查任务
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN Dec 20, 2011 3:36:12 PM Create.
 * ChangeLog:
 */
public class CountDownLatchSample implements Runnable {
	
	private int id;
	private CountDownLatch countDownLatch;
	
	public CountDownLatchSample(int id, CountDownLatch countDownLatch){
		this.id = id;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {

		try {
			System.out.println(String.format("第%s个工人开始工作了...",id));
			Thread.currentThread().sleep(id * 1000);
			System.out.println(String.format("第%s个工人完成任务",id));
			countDownLatch.countDown();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	 

	 public static void main(String[] args) {
		 
		 //多线程分发任务
		 CountDownLatch countDownLatch = new CountDownLatch(6);
		 for(int i=1;i<7;i++){
			 Thread thread = new Thread(new CountDownLatchSample(i, countDownLatch));
			 thread.start();
		 }

		 System.out.println("老板等待工人完成任务...");
		 try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//任务完成，开始检查任务		
		System.out.println("老板要开始检查任务了...");
		//TODO 
		 

		System.out.println("老板要开始检查任务完成！");
	 }
	 
}
