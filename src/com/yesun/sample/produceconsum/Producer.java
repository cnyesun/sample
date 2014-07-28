package com.yesun.sample.produceconsum;

public class Producer implements Runnable {

	WareHouse wareHouse;
	public Producer(WareHouse wareHouse){
		this.wareHouse = wareHouse;
	}
	
	@Override
	public void run() {
		
		for(int i=0; i<10; i++){
			Product product = new Product();
			product.setName("P" + i);
			wareHouse.add(product);
			System.out.println(Thread.currentThread()+"生产了："+ product.toString());  
		}
	}

}
