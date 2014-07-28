package com.yesun.sample.extend;

public class Parent {

	String username = "parent";
	int age = 40;
	
	static{
		System.out.println("parent static code");
	}
	
	{
		System.out.println("parent non-static code");
	}
	
	public Parent(){
		System.out.println("Parent()");
	}
	
	public void print(){
		System.out.println(username);
		System.out.println(age);
	}
	

}
