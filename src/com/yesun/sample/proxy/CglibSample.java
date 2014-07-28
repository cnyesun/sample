package com.yesun.sample.proxy;

public class CglibSample {
	
		
	public static void main(String[] args) {
		CglibProxy cglib=new CglibProxy();
		BookServiceImpl bookCglib=(BookServiceImpl)cglib.getInstance(new BookServiceImpl());
		bookCglib.addBook();
	}
}
