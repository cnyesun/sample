package com.yesun.sample.unicode;

public class UnicodeSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("严："+UnicodeConvert.toEncodedUnicode("严", true));
		
		
		System.out.println("\u4E25");
		
		//十六进制转二进制
		System.out.println(Integer.toBinaryString(Integer.valueOf("6C49", 16)));

		//十六进制转八进制
		System.out.println(Integer.toOctalString(Integer.valueOf("6C49", 16)));
		
		//11100110 10110001 10001001
		//二进制转十六进制
		System.out.println(Integer.toHexString(Integer.valueOf("111001101011000110001001", 2)));
		
		int i = 1;
		Integer.toHexString(i); //转十六进制
		Integer.toString(i); //转十进制
		Integer.toOctalString(i); //转八进制
		Integer.toBinaryString(i); //转二进制
		
		Integer.valueOf("111001101011000110001001", 2).toString();//二进制转十进制
		Integer.valueOf("66111", 8).toString();//八进制转十进制
		Integer.valueOf("6C49", 16).toString();//十六进制转十进制
		

	}

}
