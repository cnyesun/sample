package com.yesun.sample.core;

import java.util.BitSet;

/**
 * sample BitSetSample.java
 * Description: BitSet使用
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2014年5月20日 Create.
 * ChangeLog:
 */
public class BitSetSample {
	
	private BitSet used = new BitSet();
	public BitSetSample(String str) {
		for(int i = 0; i < str.length(); i++) {
			used.set(str.charAt(i));	
		}
	}
	
	public String toString() {
		String desc = "[";
		int size = used.size();
		for(int i = 0; i < size; i++) {
			if(used.get(i)) {
				desc += (char)i;
			}
		}
		desc += "]";
		return desc;
	}
	
	public static void main(String[] args) {
		
		BitSetSample sample = new BitSetSample("i am yesun");
		System.out.println(sample.toString());
		
	}
	
	
	

}
