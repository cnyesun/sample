package com.yesun.sample.lock.reentrant;

public class Reader extends Thread{
	
	private BufferInterrupt buffer;
	
	public Reader(BufferInterrupt buffer){
		this.buffer = buffer;
	}

	@Override
	public void run() {

		System.out.println("开始读取...");
		try {
			buffer.read();
			System.out.println("读取完毕！");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("我不读取了，退出！");
		}
	}

}
