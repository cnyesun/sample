package com.yesun.sample.thread;

public class WaitMain {

	public static void main(String[] args) {

		final Object obj = new Object();
		
		Thread t1 = new Thread(){
			@Override
			public void run(){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t1..");
				synchronized(obj){
					obj.notify();
				}
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
		System.out.println("main wait");
		synchronized(obj){
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("start t2");
		t2.start();
		
		

	}

}
