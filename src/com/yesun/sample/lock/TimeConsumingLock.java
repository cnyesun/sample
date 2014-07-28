package com.yesun.sample.lock;

import java.util.HashMap;
import java.util.Map;

public class TimeConsumingLock implements Runnable {

	private final Map<String, String> map = new HashMap<String, String>();

	private int index;

	public TimeConsumingLock(int on) {
		index = on;
	}

	public synchronized void foo1(int k) {
		String key = Integer.toString(k);
		String value = key + "value";
		if (null == key) {
			return;
		} else {
			map.put(key, value);
		}
	}

	public void foo2(int k) {
		String key = Integer.toString(k);
		String value = key + "value";
		if (null == key) {
			return;
		} else {
			synchronized (this) {
				map.put(key, value);
			}
		}
	}

	public void run() {

		long start = System.currentTimeMillis();
		for (int i = 0; i < index; i++) {
			 foo1(i); //Time consuming
			 //foo2(i); // This will be better
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TimeConsumingLock lock = new TimeConsumingLock(1000000);
		lock.run();

	}

}
