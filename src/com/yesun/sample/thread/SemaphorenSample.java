package com.yesun.sample.thread;

import java.util.concurrent.Semaphore;

public class SemaphorenSample {

	/**
	 * Description: 信号量控制同时允许访问资源线程数
	 * 
	 * 
	 * 信号量是个很重要的概念，在进程控制方面都有应用。
	 * Java 并发库的Semaphore可以很轻松完成信号量控制，Semaphore可以控制某个资源可被同时访问的个数，acquire()获取一个许可，
	 * 如果没有就等待，而release()释放一个许可。比如在Windows下可以设置共享文件的最大客户端访问个数。 
	 * Semaphore维护了当前访问的个数，提供同步机制，控制同时访问的个数。
	 * 在数据结构中链表可以保存“无限”的节点，用Semaphore可以实现有限大小的链表。
	 * 另外重入锁ReentrantLock也可以实现该功能，但实现上要复杂些，代码也要复杂些。 
	 * 
	 * @param args
	 * 1.0 YESUN Aug 29, 2013 10:22:15 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		
		final Semaphore pool = new Semaphore(5);
		
		for(int i = 0; i < 20; i++) {
			
			Thread thread = new Thread(){
				@Override
				public void run() {
					try {
						pool.acquire();
						System.out.println(Thread.currentThread().getName() + " 执行中...");
						Thread.sleep(2000);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						System.out.println(Thread.currentThread().getName() + " 执行完毕！");
						pool.release();
					}
				}
			};
			thread.start();
			
			
		}
		
		

	}
	
}
