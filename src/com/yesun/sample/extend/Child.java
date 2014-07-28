package com.yesun.sample.extend;

public class Child extends Parent{
	
	static{
		System.out.println("Child static code");
	}

	String username = "child";
	int age = 20;
	{
		System.out.println("Child non-static code");
	}
	
	public Child(){
		System.out.println("Child()");
	}
	
	public void print(){
		System.out.println(username);
		System.out.println(age);
	}
	
	
	public static void main(String[] args){
		Parent child = new Child();
		System.out.println("username" + child.username);
		System.out.println("age" + child.age);
		child.print();
		
		/*
		 	parent static code
			Child static code
			parent non-static code
			Parent()
			Child non-static code
			Child()
		 */
		
	}

}
