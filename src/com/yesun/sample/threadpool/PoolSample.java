package com.yesun.sample.threadpool;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolSample {

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Oct 8, 2013 10:27:24 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

//		BlockingQueue queue = new SynchronousQueue<Runnable>();
//		BlockingQueue queue = new LinkedBlockingQueue<Runnable>();
		BlockingQueue queue = new ArrayBlockingQueue<Runnable>(3);
		
		final ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 10, 60L, TimeUnit.SECONDS, queue);
		
		
		for (int i = 0; i < 10; i++) {
			System.out.println("准备第" +(i+1) +" 个任务");
			final int j = i;
			final String taskName = "task" + i;
			pool.execute(new Runnable() {
				public void run() {
					System.out.println(new Date() + " " + taskName + " Run...");

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(new Date() + " " + taskName + " Executed!");

				}
			});
			
			Object obj = queue.size();
			if(obj != null){
				System.out.println(obj.toString());
			}
			
//			try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
			
		}
		
		pool.shutdown();
		
		System.out.println("pool execute finished!");
		
	}

}
