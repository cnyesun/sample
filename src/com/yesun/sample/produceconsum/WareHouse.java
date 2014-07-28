package com.yesun.sample.produceconsum;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
	
	int max = 10;
	List<Product> list = new ArrayList<Product>();
	
	/**
	 * Description: add
	 * @param product
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-2-16 下午3:40:48 Create.
	 * ChangeLog:
	 */
	public synchronized void add(Product product){
		
		System.out.println("当前产品数量：" + list.size());
		
		while(list.size() >= max){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list.add(product);
		this.notifyAll();
	}
	
	/**
	 * Description: get and remove
	 * @return
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-2-16 下午3:42:37 Create.
	 * ChangeLog:
	 */
	public synchronized Product getAndRemove(){
		

		System.out.println("当前产品数量：" + list.size());
		
		
		while(list.size() < 1){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Product product = list.get(0);
		list.remove(0);
		this.notifyAll();
		return product;
	}

}
