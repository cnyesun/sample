package com.yesun.sample.proxy;

/**
 * sample BookServiceImpl.java
 * Description: cglib 动态代理，可以不用实现接口
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN 2012-4-10 下午3:54:21 Create.
 * ChangeLog:
 */
public class BookServiceImpl {
	
	public void addBook() {
		System.out.println("增加图书的普通方法...");
	}

}

