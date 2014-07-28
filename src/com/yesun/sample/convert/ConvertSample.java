package com.yesun.sample.convert;

public class ConvertSample {

	/**
	 * Description: 进制转换
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-4-18 上午10:37:00 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		int i = 431;
		//十进制转成十六进制
		System.out.println( i + "的16进制：" + Integer.toHexString(i)); 
		//十进制转成八进制
		System.out.println( i + "的8进制：" + Integer.toOctalString(i));
		//十进制转成二进制 
		System.out.println( i + "的2进制：" + Integer.toBinaryString(i));
		
		
		//十六进制转成十进制
		System.out.println("10进制：" + Integer.valueOf("FFFF",16).toString());  
		//八进制转成十进制  
		System.out.println("10进制：" + Integer.valueOf("76",8).toString());		   
		//二进制转十进制 
		System.out.println("10进制：" + Integer.valueOf("0101",2).toString());
		  
		//有什么方法可以直接将2,8,16进制直接转换为10进制的吗?   		
		System.out.println(Integer.parseInt("7fffffff", 16));	
		System.out.println(Integer.toBinaryString(2147483647));

	}
	
	
	

}
