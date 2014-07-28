package com.yesun.sample.mq;

import com.yesun.sample.modify.PrivateClass;
import com.yesun.sample.modify.ProtectClass;

public class Main {

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Oct 12, 2013 9:22:05 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		PrivateClass test1 = new PrivateClass();
		test1.add();
		//test1.new ChildClass(); 因为私有所以无法调用
		
		ProtectClass test2 = new ProtectClass();
		test2.add();
//		ProtectClass.ChildClass child = test2.new ChildClass();//protected，包名不一致，没有权限调用
		
		
		
		
		
	}

}
