package com.yesun.sample.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadPoolExecutor;

import com.yesun.sample.proxy.BookServiceImpl;
import com.yesun.sample.proxy.CglibProxy;

public class Main {

	/**
	 * Description: 深入阅读Collection源码
	 * @param args
	 * 1.0 YESUN Oct 8, 2013 3:34:13 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		
		Properties p;
		java.util.Collection<String> collection;//接口
		
		//java.util.Collections;//工具类
		//SynchronizedMap其实就是对传入的map进行了包装，进行了加锁操作，实现线程同步
		java.util.Collections.synchronizedMap(new HashMap());
		
		//java.util.Collections.binarySearch(list, key); 二分法查找
		
		//java.util.Arrays 工具类
		
		ThreadPoolExecutor executor;
		StringBuffer sb;
		StringBuilder sbr;
		
		ConcurrentHashMap map;
		String str = null;
		str.equals("");
		
		
		CglibProxy cglib= new CglibProxy();
        BookServiceImpl bookCglib=(BookServiceImpl)cglib.getInstance(new BookServiceImpl());
        bookCglib.addBook();

	}

}
