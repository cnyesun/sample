package com.yesun.sample.modify;

/**
 * sample PublicClass.java
 * Description: protected也只能修饰内联类
 * 1.0 YESUN Oct 12, 2013 9:29:09 AM Create.
 * ChangeLog:
 */
 public class ProtectClass {
	 
	 public void add(){
		 ChildClass child = new ChildClass();
		 child.add();
	 }
	
	 protected class ChildClass{
			private void add(){
				System.out.println("child class add");
			}
		}
}
