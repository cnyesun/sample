package com.yesun.sample.lock;

public class SynchronizeSample implements Counter {
	
	private long count;

	@Override
	public long getValue() {
		return count;
	}

	@Override
	public synchronized void increment() {
		count++;
	}

}
