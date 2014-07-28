package com.yesun.sample.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;



/**
 * sample Txt2Xml.java
 * Description:
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN Dec 16, 2011 12:06:09 PM Create.
 * ChangeLog:
 */
public class Txt2Xml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//将txt转换为xml格式
		
		FileReader fileReader = null;
		BufferedReader bufferReader = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		try {
			fileReader = new FileReader("c:\\address.txt");
			bufferReader = new BufferedReader(fileReader);
			
			fileWriter = new FileWriter("c:\\xml.txt"); 
			bufferWriter = new BufferedWriter(fileWriter);

			String line;
			String nextLine;
			int level = 0;
			int nextLevel = 0;
			line = bufferReader.readLine();
			StringBuffer sb = new StringBuffer("<msc>");
			while(line != null){
				String beforeSpace = line.replaceAll("(\\t+)(.+)(\\t+)?","$1");
				level = StringUtils.countMatches(beforeSpace, "\t");
				
				//如果有子节点，则不需要</item>
				sb.append("<item value='"+ line.trim().replace("&", "&amp;") +"'>");
				nextLine = bufferReader.readLine();
				if(StringUtils.isEmpty(nextLine)){
					sb.append("</item>");
					for(int i=0;i<level;i++){
						sb.append("</item>");
					}
					
				}
				else{
					beforeSpace = nextLine.replaceAll("(\\t+)(.+)(\\t+)?","$1");
					nextLevel = StringUtils.countMatches(beforeSpace, "\t");
					if(nextLevel < level){
						//表示没有子节点
						sb.append("</item>");
						for(int i=0;i<(level - nextLevel);i++){
							sb.append("</item>");
						}
					}
					else if(nextLevel ==  level){
						sb.append("</item>");
					}
					else{
						//TODO
					}
				}
				System.out.println(line);
				line = nextLine;
			}
			
			sb.append("</msc>");
			bufferWriter.write(sb.toString());
			bufferWriter.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			IOUtils.closeQuietly(fileReader);
			IOUtils.closeQuietly(bufferReader);
			IOUtils.closeQuietly(fileWriter);
			IOUtils.closeQuietly(bufferWriter);
		}

	}
	
	

}
