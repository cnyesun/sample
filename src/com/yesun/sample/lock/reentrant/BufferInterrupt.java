package com.yesun.sample.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yesun
 * 
 * ReentrantLock可实现锁的中断
 * 
 *
 */
public class BufferInterrupt {
	
	private ReentrantLock lock = new ReentrantLock();
	

	public void write(){	
		System.out.println("开始写入数据...");		
		lock.lock();
		try{
			long startTime = System.currentTimeMillis();			
			for(;;){
				if(System.currentTimeMillis() - startTime > Integer.MAX_VALUE){
					break;					
				}				
			}
		}
		finally{
			lock.unlock();
		}
		System.out.println("写入完成！");
	}
	
	
	public void read() throws InterruptedException{	
		lock.lockInterruptibly(); //表示可响应中断
		try{	
			System.out.println("读取数据...");	
		}
		finally{
			lock.unlock();
		}
	}
}
