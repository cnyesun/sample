package com.yesun.sample.lock;

public class LockStatic {
	
	
	public static synchronized void print(){
		
		System.out.println("i am coming..");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("unlock");
		
	}
	
	
	public static synchronized void print2(){
		
		System.out.println("i am coming print2..");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("unlock");
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		new Thread(){
			
			public void run(){
				LockStatic.print();
			}
			
		}.start();
		
		new Thread(){
			
			public void run(){
				LockStatic.print2();
			}
			
		}.start();
		
	}

}
