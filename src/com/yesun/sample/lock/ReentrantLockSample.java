package com.yesun.sample.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSample {

	private Lock lock = new ReentrantLock();
	public void execute(String name) {
		System.out.println(Thread.currentThread().getName() + "进入");
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "加锁");
		try {
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
			}
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("\r\n" + Thread.currentThread().getName() + "解锁");
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final ReentrantLockSample sample = new ReentrantLockSample();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				sample.execute("abcdefghijklmn");
			}
		}, "T1");
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				sample.execute("1234567890");
			}
		}, "T2");
		t2.start();

	}

}
