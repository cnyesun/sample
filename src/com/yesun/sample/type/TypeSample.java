package com.yesun.sample.type;

public class TypeSample {
	
	public static void print(StringBuffer message){
		message.append(" by dcm modify");
		System.out.println("print:" + message.toString());
	}
	

	public static void main(String[] args){
		
		StringBuffer message = new StringBuffer("12345667");
		
		print(message);

		System.out.println("main:" + message.toString());
		
		
		
		String s="abc";//会放进常量池
		String s1="abc";
		String s2=new String("abc");
		String s3=s2.intern();//当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（该对象由 equals(Object) 方法确定），则返回池中的字符串。否则，将此 String 对象添加到池中，并且返回此 String 对象的引用
		System.out.println(s1==s);
		System.out.println(s1==s2);
		System.out.println(s1==s3);
	}

}
