package com.yesun.sample.lock;

public class SyncSample {
	
	public synchronized void method1() {
		System.out.println("print method1");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void method2() {
		synchronized(this){
			System.out.println("print method2");	
		}
	}

	

	public static void main(String[] args) {
		final SyncSample sample = new SyncSample();
		new Thread(){
			@Override
			public void run(){
				System.out.println("method1 ready... ");
				sample.method1();
			}
		}.start();
		
		
		new Thread(){
			@Override
			public void run(){
				System.out.println("method2 ready... ");
				sample.method2();
			}
		}.start();
		
	}
}
