package com.yesun.sample.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * sample AtomicStampedReferenceSample.java
 * Description: AtomicStampedReference可以防止ABA问题，ABA后A其实变化了，应该不允许CAS
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2014年5月22日 Create.
 * ChangeLog:
 */
public class AtomicStampedReferenceSample {
	
	private static AtomicInteger atomicInt = new AtomicInteger(100);
	private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);


	public static void main(String[] args) throws InterruptedException {
		
		Thread intT1 = new Thread(new Runnable() {
			@Override
			public void run() {
				atomicInt.compareAndSet(100, 101);
				atomicInt.compareAndSet(101, 100);
			}
		});
		
		Thread intT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				}
				boolean c3 = atomicInt.compareAndSet(100, 101);
				System.out.println(c3); // true
			}
		});
		intT1.start();
		intT2.start();
		intT1.join();//阻塞主线程，直到t1完成
		intT2.join();
		
		
		
		
		Thread refT1 = new Thread(new Runnable() {
			@Override
			public void run(){
				try {
						TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				}
				atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
				atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
			}
		});
		
		Thread refT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				int stamp = atomicStampedRef.getStamp();
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
				}
				boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
				System.out.println(c3); // false
			}
		});
		refT1.start();
		refT2.start();
	}

}
