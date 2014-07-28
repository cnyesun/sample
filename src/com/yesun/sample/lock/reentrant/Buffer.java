package com.yesun.sample.lock.reentrant;

public class Buffer {
	
	private Object lock;
	
	public Buffer() {
		lock = this;
	}

	public void write(){	
		System.out.println("开始写入数据...");		
		synchronized(lock){
			long startTime = System.currentTimeMillis();			
			for(;;){
				if(System.currentTimeMillis() - startTime > Integer.MAX_VALUE){
					break;					
				}				
			}
		}
		System.out.println("写入完成！");
	}
	
	
	public void read(){	
		synchronized(lock){	
			System.out.println("读取数据...");	
		}
	}
}
