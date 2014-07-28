package com.yesun.sample.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeCount {
	
	
	private static HashMap<String, Double> map = new HashMap<String, Double>();

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-4-1 下午6:49:51 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("c:\\month3.txt"));
			String tmp = null;
			int i=0;
			String dataPattern = "(2012-\\d+-\\d+ \\d+:\\d+(:\\d+)?)\\s*到\\s*(2012-\\d+-\\d+ \\d+:\\d+(:\\d+)?)";
			String namePattern = "(加班申请|请假公文|普通发文)\\s*([^\\d]+)";
			Pattern pattern;
			while((tmp = reader.readLine()) != null){
//				System.out.println("<" +i+ ">"+tmp);
				
//				<3> 3. 王春艳申请平时加班(2012-03-29 18:00:00到 2012-03-29 22:00:00) 加班申请  王春艳 2012-03-30 18:18:51  
//				<1>﻿ 1. 申请倒休 2012-03-30 09:00:00 到 2012-03-30 18:00:00 普通发文  陈小明 2012-03-31 09:25:08  
//				<2> 2. 张 震请事假(2012-03-30 09:00:00到 2012-03-30 12:30:00) 请假公文  张 震 2012-03-30 18:23:29  
//				<11> 11. 王子成申请周末加班(2012-03-25 11:30:00到 2012-03-25 17:00:00) 加班申请  王子成 2012-03-29 08:30:56  
				//获取用户名、时间
				

				pattern = Pattern.compile(namePattern);      
				Matcher matcher = pattern.matcher(tmp);
				String username = "";
				String type = "";
				while(matcher.find()){
					type = matcher.group(1);
					username = matcher.group(2);
				}
				
				pattern = Pattern.compile(dataPattern);      
				matcher = pattern.matcher(tmp);

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				while(matcher.find()){
					Date startTime = null;
					try {
						startTime = df.parse(matcher.group(1).trim());
					} catch (ParseException e) {
						e.printStackTrace();
						
					}
					Date endTime = null;
					try {
						endTime = df.parse(matcher.group(3).trim());
					} catch (ParseException e) {
						e.printStackTrace();
					}
//					System.out.println(username + " >>>> "+startTime + " TO " + endTime);
					i++;
					long diff = endTime.getTime() - startTime.getTime();
					long minute = diff / (1000 * 60);
					
					if(startTime.getHours() < 12 && endTime.getHours() > 12 && !type.equals("加班申请")){
						//表示起始时间在上午，需要减60分钟
						minute = minute - 60;
						
					}
					
					
					System.out.println( i + " "+username +  type + " >>>> "+df.format(startTime) + " TO " + df.format(endTime) + " 分钟：" + (type.equals("加班申请")?"+":"-") +minute);
				}
				
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
