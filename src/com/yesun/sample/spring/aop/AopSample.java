package com.yesun.sample.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * sample AopSample.java
 * Description: AOP Sample，需要AOP拦截SysuserServiceImpl业务，在addUser和deleteUser前验证，在执行之后记录日志 
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN Apr 26, 2012 8:22:06 AM Create.
 * ChangeLog:
 */
public class AopSample {
	
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		SysuserServiceImpl sysuserServiceImpl = (SysuserServiceImpl)context.getBean("sysuserServiceImpl");
		sysuserServiceImpl.addUser("yesun");
		sysuserServiceImpl.deleteUser("yesun");
//		sysuserServiceImpl.init();
	}

}
