package com.yesun.sample.spring.aop;

import org.springframework.stereotype.Service;

/**
 * sample SysuserServiceImpl.java
 * Description:用户业务
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN Apr 26, 2012 8:23:30 AM Create.
 * ChangeLog:
 */
@Service
public class SysuserServiceImpl extends BaseService {
	
	public void addUser(String username){
		System.out.println("add user successed!");
	}
	
	public void deleteUser(String username){
		System.out.println("delete user successed!");
		this.init();
	}

}
