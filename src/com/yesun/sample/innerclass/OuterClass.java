package com.yesun.sample.innerclass;

/**
 * @author yesun
 * 
 * 内部类的存在意义就是可以方便的访问外部类的私有属性，代码简洁
 *
 */
public class OuterClass {
	
	
	public String username;
	public static String password;
	
	
	public class InnerClass {
		
		//非静态内部类不允许出现静态变量，因为累的静态变量必须在类初始化之前完成，而InnerClass是非静态类
		//private static int AGE =23;
		public void print(){
			System.out.println(username);
			System.out.println(password);
		}
		
//		public static void print2(){ 非静态内部类，不允许出现任何静态成员
//			System.out.println(username);
//			System.out.println(password);
//		}
		
	}
	
	public static class InnerStaticClass {
		
		//静态内部类允许出现静态变量
		private static int AGE =23;
		
		public void print(){
//			System.out.println(username);
			System.out.println(password);
		}
		
		public static void print2(){
//			System.out.println(username);
			System.out.println(password);
		}
		
	}

	
	public static void main(String[] args){
		OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
	}
	
}
