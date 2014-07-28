package com.yesun.sample.produceconsum;

public class ProductConsumProvider {

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-2-16 下午3:46:49 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		WareHouse wareHouse = new WareHouse();
		Producer producer = new Producer(wareHouse);
		Producer producer2 = new Producer(wareHouse);
		
		Consumer consumer = new Consumer(wareHouse);
		Consumer consumer2 = new Consumer(wareHouse);

		Thread t1 =new Thread(producer);
		Thread t2 =new Thread(producer2);
		Thread t3 =new Thread(consumer);
		Thread t4 =new Thread(consumer2);  
		

		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		
	}

}
