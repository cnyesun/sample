package com.yesun.sample.lock.reentrant;



public class Writer extends Thread{ 
	
	private BufferInterrupt buffer;
	
	public Writer(BufferInterrupt buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		buffer.write();
	}

}
