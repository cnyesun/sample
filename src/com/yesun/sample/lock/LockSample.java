package com.yesun.sample.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSample {

	private Lock lock = new ReentrantLock();
	public void put(String name) {
		System.out.println(Thread.currentThread().getName() + "进入PUT");
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "加锁PUT");
		try {
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
			}
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("\r\n" + Thread.currentThread().getName() + "解锁PUT");
			lock.unlock();
		}
	}
	
	public void get() {
		System.out.println(Thread.currentThread().getName() + "进入GET");
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "加锁GET");
		try {
			System.out.print("GET OBJECT");
		}finally {
			System.out.println("\r\n" + Thread.currentThread().getName() + "解锁GET");
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final LockSample sample = new LockSample();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				sample.put("abcdefghijklmn");
			}
		}, "T1");
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				sample.get();
			}
		}, "T2");
		t2.start();

	}

}
