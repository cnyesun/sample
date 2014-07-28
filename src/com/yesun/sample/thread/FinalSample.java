package com.yesun.sample.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * sample FinalSample.java
 * Description: Set final也可以修改
 * 1.0 YESUN Sep 17, 2013 5:42:36 PM Create.
 * ChangeLog:
 */
public class FinalSample {
	
	private final Set<String> students = new HashSet<String>();
	
	public FinalSample(){
		students.add("dcm");
		students.add("yesun");
		students.add("xxxx");
	}
	
	
	

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-3-7 下午12:20:02 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
