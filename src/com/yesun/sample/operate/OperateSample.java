package com.yesun.sample.operate;

import org.junit.Test;

public class OperateSample {
	
	/**
	 * Description:位运算
	 * &(按位与)、|(按位或)、^(按位异或)、~ (按位取反)
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-3-15 上午8:35:40 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		
		
		System.out.println(1 << 0);
		System.out.println(1 << 1);
		System.out.println(1 << 2);
		System.out.println(1 << 3);
		System.out.println(1 << 4);
		
		
		
		int a = 12;
		int b = 4;
		

		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		

		System.out.println(a & b);
		
		
		byte bit = 0x01;
		System.out.println(bit);
		System.out.println(Integer.toBinaryString(bit));
		System.out.println(Integer.toBinaryString(bit << 2));


		a = 12;
		b = -12;//负数为正数的反码+1，即-x = ~x + 1
		//公式
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		
		System.out.println(~a);

		//对第2位清零
		System.out.println(Integer.toBinaryString(~(1<<2)));
		System.out.println(Integer.toBinaryString(b & ~(1<<2)));
		
		
		a = 2;
		b = 4;
		int c = 6;
		System.out.println(a | b);
		System.out.println(c & a);
		System.out.println(c & b);
		
		//swap 
		int x = 5;
		int y = 9;
		System.out.println("x:" + Integer.toBinaryString(x));
		System.out.println("y:" + Integer.toBinaryString(y));
		x ^= y;
		System.out.println("new x:" + Integer.toBinaryString(x));
		y ^= x;
		System.out.println("new y:" + Integer.toBinaryString(y));
		x ^= y;
		System.out.println(x);
		System.out.println(y);
		

	}
	
	
	
	@Test
	public void count() {
		
		int a = 88;
//		int b = 87;
//		int c = 86;
		System.out.println(Integer.toBinaryString(a));
//		System.out.println(Integer.toBinaryString(b));
//		System.out.println(Integer.toBinaryString(c));
//		System.out.println(Integer.toBinaryString(a & b));//相邻两个数&之后，会将更大的数的低位1变成0，因为相邻两数之间就差一个1
		
		int count = 0;
		while(a>0){
			a = a & ( a - 1 );
			count++;
		}
		System.out.println(count);
		
	}
	
	
	@Test
	public void test(){
		System.out.println(Integer.toBinaryString(5));
		System.out.println(Integer.toBinaryString(4));
		System.out.println(5&4);
		
		
		System.out.println(Integer.toBinaryString(6));
		System.out.println(Integer.toBinaryString(5));
		System.out.println(6&5);
		
		

		System.out.println(Integer.toBinaryString(-6));
		
	}
	
	
	

}
