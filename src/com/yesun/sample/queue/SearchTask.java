package com.yesun.sample.queue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable {
	
	private BlockingQueue<File> queue;
	private String keyword;
	
	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}
	

	@Override
	public void run() {

		
		
		
	}

}
