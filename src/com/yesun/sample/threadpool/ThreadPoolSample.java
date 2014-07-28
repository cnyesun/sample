package com.yesun.sample.threadpool;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * @author yesun
 * 连接池测试
 *
 */
public class ThreadPoolSample {

	static ExecutorService pool = Executors.newCachedThreadPool();//采用的是SychronousQueue
//	static ExecutorService pool = Executors.newFixedThreadPool(5);//采用LinkedBlockQueue

	
	
	public static void main(String[] args){
		for (int i = 0; i < 100; i++) {
			final String taskName = "task" + i;
			pool.execute(new Runnable() {
				public void run() {
					System.out.println(new Date() + " " +  taskName + " Run...");

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(new Date() + " " +  taskName +" Executed!");

				}
			});
			
			
		}
		
		pool.shutdown();
		
		System.out.println("pool execute finished!");
	}


	@Test
	public void testSyncQueue() {
		
		SynchronousQueue queue = new SynchronousQueue();
		System.out.println("offer....");
		boolean result = queue.offer("yesun");
		System.out.println("offer...." + result);
		Object o = queue.poll();
		System.out.println("poll...." + o.toString());
		
		
	}
	
	/**
	 * CALLBACK
	 */
	@Test
	public void testThreadPoolCallable() {
		
		Future future = pool.submit(new Callable(){
			public Object call() throws Exception {
				System.out.println(Thread.currentThread().getName() + " CALLBACK....");
				return "RETURN RESULT";
			}
		});
		
		//do something...
		//TODO
		
		//get result
		String result;
		try {
			result = (String) future.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	
	
	
	
	
	/*
	 * 测试ReentrantLock
	 * 加锁机制，在Java 5之前，我们只能使用synchronized来锁定。Java 5中引入了性能更加粒度更细的重入锁ReentrantLock。我们使用ReentrantLock保证代码线程安全
	 */
	private static ReentrantLock lock = new ReentrantLock();
	private static int count = 0;

	private int getCount(){ 
		int ret = 0; 
		try{ 
			lock.lock(); 
			ret = count; 
		}finally{ 
			lock.unlock(); 
		} 
		return ret; 
	}

	private void increaseCount(){ 
		try{ 
			lock.lock(); 
			++count; 
		}finally{ 
			lock.unlock(); 
		} 
	}
}

