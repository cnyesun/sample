package com.yesun.sample.thread;

public class SynchronizedSample {

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN Oct 28, 2013 3:57:09 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		final Object obj = new Object();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized(obj) {//如果obj锁被占用，讲阻塞在这里
					System.out.println("AAAA");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("AAAA!!!");
				}
			}
		});
		t1.start();
		
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized(obj) {//如果obj锁被占用，讲阻塞在这里
					System.out.println("BBBB");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("BBBB!!!");
				}
			}
		});
		t2.start();
		

	}

}
