package com.yesun.sample.equals;

import java.util.HashSet;
import java.util.Set;

/**
 * sample Main.java
 * Description: 
 * 每次在往Set里add(object)时，先调用hashCode()，若hash码与set里面某个object的hash码不相等，则直接放入set；
 * 否则调用equals(object)，若与set中某个object相等(即hashCode相等，而且equals返回true)，则返回，不放入set，否则放入set。
 *
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2013年11月17日 Create.
 * ChangeLog:
 */
public class Main {

	public static void main(String[] args) {

		Set<Student> set = new HashSet<Student>();   
        set.add(new Student("1","1"));   
        System.out.println("----------");   
        set.add(new Student("2","2"));   
        System.out.println("----------");   
        set.add(new Student("3","3"));   
        System.out.println("----------");
        System.out.println(set.size());
        
        
        
	}

}
