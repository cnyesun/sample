package com.yesun.sample.thread;

public class JoinMain {

	public static void main(String[] args) throws InterruptedException {


		Thread t1 = new Thread(){
			@Override
			public void run(){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t1..");
			}
		};
		
		Thread t2 = new Thread(){
			@Override
			public void run(){
				System.out.println("t2...");
			}
		};
		
		System.out.println("start t1");
		t1.start();
		System.out.println("join t1");
		t1.join();//阻塞主线程，直到t1完成

		System.out.println("start t2");
		t2.start();

		System.out.println("finished");
		
		/*
		 * 使用join可以让线程按顺序执行
		 * 
		 * 
				start t1
				join t1
				t1..
				start t2
				finished
				t2...
		
		 * 
		 */
		
		
	}

}
