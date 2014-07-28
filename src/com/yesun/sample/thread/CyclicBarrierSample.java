package com.yesun.sample.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * sample CyclicBarrierSample.java
 * Description:CyclicBarrier是当await的数量到达了设定的数量后，才能继续往下执行。
在实际应用中，有时候需要多个线程同时工作以完成同一件事情，而且在完成过程中，往往会等待其他线程都完成某一阶段后再执行，等所有线程都到达某一个阶段后再统一执行。

与CountDownLatch的区别是，支持分阶段完成同时完成某阶段任务
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN Nov 24, 2012 5:48:01 PM Create.
 * ChangeLog:
 */
public class CyclicBarrierSample implements Runnable {
	
	
	private CyclicBarrier cyclicBarrier;
	private int id;
	
	public CyclicBarrierSample(int id, CyclicBarrier cyclicBarrier){
		this.id = id;
		this.cyclicBarrier = cyclicBarrier;
	}
	
	

	@Override
	public void run() {

		try {
			Thread.currentThread().sleep(id * 1000);
			System.out.println(String.format("第%s个用户进入第一关", this.id));
			cyclicBarrier.await();
			
			System.out.println(String.format("第%s个用户进入第二关", this.id));
			cyclicBarrier.await();
			
			System.out.println(String.format("第%s个用户进入第三关", this.id));
			cyclicBarrier.await();
			
			System.out.println(String.format("第%s个用户进入第四关", this.id));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN Dec 20, 2011 3:58:07 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable(){

			@Override
			public void run() {

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("第一关全部结束！");
				
			}});
		
		for(int i=0;i<4;i++){
			new Thread(new CyclicBarrierSample(i, cyclicBarrier)).start();
		}
		

	}







}
