package com.yesun.sample.produceconsum;

public class Consumer implements Runnable{


	WareHouse wareHouse;
	public Consumer(WareHouse wareHouse){
		this.wareHouse = wareHouse;
	}
	
	@Override
	public void run() {
		
		for(int i=0; i<5; i++){
			Product product = wareHouse.getAndRemove();
			System.out.println(Thread.currentThread()+"消费了："+ product.toString());  
		}
	}

}
