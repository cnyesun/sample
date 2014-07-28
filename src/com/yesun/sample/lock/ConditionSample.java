package com.yesun.sample.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSample {
	
	final Lock lock = new ReentrantLock();//默认为非公平锁，fasle
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putptr, takeptr, count;
	
	

	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		
		final ConditionSample sample = new ConditionSample();
		Thread threadTake = new Thread(new Runnable(){
			
			@Override
			public void run() {
				
				try {
					System.out.println(sample.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
		threadTake.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Thread threadPut = new Thread(new Runnable(){

			@Override
			public void run() {

				try {
					sample.put("dcm");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
		threadPut.start();
		
		
		
	}
	
	

	/**
	 * put a object
	 * @param x
	 * @throws InterruptedException
	 */
	public void put(Object x) throws InterruptedException {
		System.out.println("put...");
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "put locked...");
		try {
			while (count == items.length){
				System.out.println("queue is full, waiting...");
				notFull.await();//释放当前contition所绑定的锁，相当于unlock，但是阻塞在这里，cpu切换
				System.out.println("notFull.await()");
			}
			System.out.println("put continue...");
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			System.out.println("put finished!");
			notEmpty.signal();
			System.out.println("notEmpty signal finished!");

		} finally {
			System.out.println("put unlocked!!!");
			lock.unlock();
		}
	}

	/**
	 * @return take a object
	 * @throws InterruptedException
	 */
	public Object take() throws InterruptedException {
		System.out.println("take...");
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " take locked!!!");
		try {
			while (count == 0){
				System.out.println("queue is empty, waiting...");
				notEmpty.await();//释放当前contition所绑定的锁
				System.out.println("notEmpty.await();");
			}
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			System.out.println("notFull signal finished!");
			return x;

		} finally {
			System.out.println("take thread unlocked!!!");
			lock.unlock();
		}
	}
	
	

}
