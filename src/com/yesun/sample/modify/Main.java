package com.yesun.sample.modify;

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
		ProtectClass.ChildClass child = test2.new ChildClass();//protected,所以同包名下可以调用
		//child.add();//但私有方法无法调
		
		
		FinalClass test3 = new FinalClass();
		test3.add();
		
		
		
		
	}

}
