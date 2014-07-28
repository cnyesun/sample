package com.yesun.sample.hook.shutdown;

public class ShutdownHook extends Thread {

	@Override
	public void run() {

		System.out.println("JVM shutdown now...");
		
		System.out.println("clear tmp data...");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("clear tmp data finished!");
		
	}
	

}
