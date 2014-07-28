package com.yesun.sample.thread;

/**
 * sample NotifySample.java
 * Description: 结论：synchronized会阻塞
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN Oct 29, 2013 10:58:01 AM Create.
 * ChangeLog:
 */
public class NotifySample {
	
	

	public static void main(String[] args) {
		NotifySample notify = new NotifySample();
		MyThread mythread = notify.new MyThread();

		System.out.println("MyThread is start...");
		mythread.start();
		
		
//		try {
//			Thread.currentThread().sleep(5);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		synchronized(mythread){

			try {
				System.out.println("waiting MyThread is completed!");
				Thread.sleep(3000);
				mythread.wait();
				
				System.out.println("final total is " + mythread.total);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	
	
	class MyThread extends Thread{
		int total =0;
		public void run(){
			
			synchronized(this){
				System.out.println("MyThread is running...");
				for(int i=0;i<100;i++){
					total += i;
					try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				notify();
				
			}
		}
	}

}
