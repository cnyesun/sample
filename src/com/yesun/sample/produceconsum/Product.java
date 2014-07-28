package com.yesun.sample.produceconsum;

/**
 * sample Product.java
 * Description: 产品
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN 2012-2-16 下午3:34:29 Create.
 * ChangeLog:
 */
public class Product {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String toString(){
		return "产品："+this.name;
	}

}
