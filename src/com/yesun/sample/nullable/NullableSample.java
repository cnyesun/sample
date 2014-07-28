package com.yesun.sample.nullable;

import javax.annotation.Nullable;

/**
 * sample NullableSample.java
 * Description: Nullable 发现有没有都一样啊，有什么区别呢？的确没有却别，仅仅是一个标识而已
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2014年7月8日 Create.
 * ChangeLog:
 */
public class NullableSample {

	public static void main(String[] args) {

		
		NullableSample.checkArgs(false,null);

	}

	
	public static void checkArgs(boolean expression, @Nullable Object message){
		if(!expression) {
			throw new IllegalArgumentException(String.valueOf(message));
		}
	}
	
}
