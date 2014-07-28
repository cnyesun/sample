package com.yesun.sample.algorithm;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sample Main.java
 * Description: HashMap是最高性能的数据结构,时间复杂度O(logn)
 * 1.0 YESUN Oct 8, 2013 3:25:28 PM Create.
 * ChangeLog:
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		StringBuffer aa;
		StringBuilder bb;

		ConcurrentHashMap<String, String> mm = new ConcurrentHashMap< String, String>();    
        Long aBeginTime=System.currentTimeMillis();//记录BeginTime   
        for(int i=0;i< 1000000;i++){   
        	mm.put(""+i, ""+i*100);   
        }   
        Long aEndTime=System.currentTimeMillis();//记录EndTime   
        System.out.println("insert time-->"+(aEndTime-aBeginTime));   
           
        Long lBeginTime=System.currentTimeMillis();//记录BeginTime   
        mm.get(""+100000);   
        Long lEndTime=System.currentTimeMillis();//记录EndTime   
        System.out.println("seach time--->"+(lEndTime-lBeginTime));   
        
        
        while(true){
        	
        }

	}

}
