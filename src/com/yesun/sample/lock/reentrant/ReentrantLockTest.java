package com.yesun.sample.lock.reentrant;

public class ReentrantLockTest {
	
	
	public static void main(String args[]){
		
		BufferInterrupt buffer = new BufferInterrupt();
		
		Writer writer  = new Writer(buffer);
		final Reader reader  = new Reader(buffer);
		
		writer.start();
		reader.start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				
				long startTime = System.currentTimeMillis();			
				for(;;){
					if(System.currentTimeMillis() - startTime > 5000){
						reader.interrupt();
						break;					
					}				
				}
				
				
				
			}}).start();
		
		
	}

}
