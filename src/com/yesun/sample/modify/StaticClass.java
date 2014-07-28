package com.yesun.sample.modify;

public class StaticClass {
//public static class StaticClass 静态类只能修饰内部类
	
	static int a = 2;
	int c = 33;
	
	public void add(){
		//可直接访问静态类中的私有
		ChildClass.add();
	}
	
	public static void update(){

	}
	
	private static class ChildClass {
		
		int b;
		
		private static void add(){
			a = 3;
			//b = 3;无法访问非静态的变量，内部或外部
			//c =3;
		}
		
		public static void update(){
			
		}
		
		public void delete(){
			b = a;//非静态方法可以访问内部的非静态变量
		}
		
	}

}
