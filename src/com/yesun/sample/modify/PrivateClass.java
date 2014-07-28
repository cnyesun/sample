package com.yesun.sample.modify;

/**
 * sample PrivateClass.java
 * Description:私有的类只能修饰在内联类上
 * 1.0 YESUN Oct 12, 2013 9:28:27 AM Create.
 * ChangeLog:
 */
public class PrivateClass {
	
	public void add() {
		ChildClass child = new ChildClass();
		child.add();
	}
	
	private class ChildClass{
		
		private void add(){
			System.out.println("child class add");
		}
		
	}

}
