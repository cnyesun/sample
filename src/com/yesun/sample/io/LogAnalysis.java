package com.yesun.sample.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.yesun.sample.sort.BubbleSort;

/**
 * sample LogAnalysis.java
 * Description: 日志分析
 * 1.0 YESUN Aug 14, 2013 12:40:41 PM Create.
 * ChangeLog:
 */
public class LogAnalysis {

	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SSS");
	static SimpleDateFormat sdfFull = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	
	/**
	 * Description:日志分析
	 * @param filename
	 * 1.0 YESUN Aug 14, 2013 12:42:11 PM Create.
	 * ChangeLog:
	 */
	public List<ThreadExecuteTime> analysis(String filename){
		HashMap<String, ThreadExecuteTime> map = new HashMap<String,ThreadExecuteTime>();
		List<ThreadExecuteTime> result = new ArrayList<ThreadExecuteTime>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			String tmp;
			StringBuffer sb = new StringBuffer();
			int i = 0;
			
			String threadName = "", strDate = "";
			while((tmp = reader.readLine()) != null) {

				if(tmp.startsWith("http-8080-")) {
					//首行，处理上一段
					if(sb.length() > 0) {
						
						String header = "";
						if(sb.toString().indexOf("DEBUG") > -1){
							header = sb.toString().substring(0, sb.toString().indexOf("DEBUG"));
							threadName = header.substring(0, header.indexOf(" ")).trim();
							strDate = header.substring(header.indexOf(" ")).trim();
						}
						if(sb.indexOf("开始保存文件") > -1) {
							//起始时间
							ThreadExecuteTime time = new ThreadExecuteTime();
							time.name = threadName;
							time.firstRequestStartTime = sdf.parse(strDate);
							
							map.put(threadName, time);
							
							i++;
						}
						else if(sb.indexOf("reset filename") > -1){
							//可以得到转换完成的时间
							ThreadExecuteTime time = map.get(threadName);
							String file = sb.substring(sb.indexOf("reset filename") + 16);
							if(time != null) {
								time.filename = file;
							}
						}
						else if(sb.indexOf("tempfiles.length is") > -1) {
							//可以得到转换完成的时间
							ThreadExecuteTime time = map.get(threadName);
							if(time != null) {
								time.firstRequestEndTime = sdf.parse(strDate);
							}
						}
						else if(sb.indexOf("转换耗时") > -1) {
							//可以得到转换完成的时间
							ThreadExecuteTime time = map.get(threadName);
							if(time != null && time.firstRequestEndTime == null) {
								time.firstRequestEndTime = sdf.parse(strDate);
							}
						}
						else if(sb.indexOf("/temp-1.") > -1){
							//可以得到第一张图片读取的时间
							ThreadExecuteTime time = map.get(threadName);
							if(time != null) {
								time.secondRequestStartTime = sdf.parse(strDate);
								//当看第一页的时候，如果没有文件名，则从这里读取
								if(time.filename == null && sb.indexOf("pdfpath=") > -1) {
									String s = sb.substring(sb.indexOf("pdfpath=") ,sb.indexOf(".pdf"));
									s = s.substring(s.lastIndexOf("/") + 1);
									time.filename = s;
								}
							}
							
							
						}
						else if(sb.indexOf("当前文件路径：") > -1){
							
							ThreadExecuteTime time = map.get(threadName);
							if(time != null) {
								time.secondRequestEndTime = sdf.parse(strDate);

								//从map中移除，加入到已完成map
								map.remove(time.name);
								
								result.add(time);
							}
						}
						
						//重置
						sb = new StringBuffer();
					}
					
					//存储当前行
					sb.append(tmp);
					sb.append(" ");
				}
				else {
					//其他行
					sb.append(tmp);
				}
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally{
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
		}
		return result;
	}

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Aug 14, 2013 12:40:34 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		LogAnalysis tool = new LogAnalysis();
		List<ThreadExecuteTime> list = tool.analysis("E:\\DHEAVEN\\12. 项目人工\\河南移动宕机\\HA0812\\mkey日志11-13\\MKey3GProject.out.2013.08.12");
		
		System.out.println("总记录：" + list.size());
		System.out.println("------------------------------------------------------------------");
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < list.size(); i++) {
			ThreadExecuteTime time = list.get(i);
			if(time != null && time.filename != null) {
				if(map.containsKey(time.filename)){
					//存在
					map.put(time.filename, map.get(time.filename) + 1);
				}
				else {
					map.put(time.filename, 1);
				}
			}
			
			if(time.firstRequestEndTime != null && time.firstRequestStartTime != null && time.secondRequestEndTime != null && time.secondRequestStartTime != null) {
				System.out.println(i + " 线程：" + time.name + "  1耗时：" + (time.firstRequestEndTime.getTime() - time.firstRequestStartTime.getTime()) + " 2耗时：" + (time.secondRequestEndTime.getTime() - time.secondRequestStartTime.getTime()) + "  文件:" + time.filename +"  1开始：" +  time.firstRequestStartTime +" 1结束："+  time.firstRequestEndTime + " 2开始：" + time.secondRequestStartTime  +" 2结束："+ time.secondRequestEndTime);
			}
			else{
				System.out.println(i + " 线程：" + time.name + " 1开始：" +  time.firstRequestStartTime +" 1结束："+  time.firstRequestEndTime + " ２开始：" + time.secondRequestStartTime  +" 2结束："+ time.secondRequestEndTime  +"文件:" + time.filename);
			}
		}
		
		
		System.out.println("-------------------------Division------------------------------------");
		int[] sort = new int[map.size()];
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0 ;
		while(iterator.hasNext()) {
			String key = iterator.next();
			sort[i] = map.get(key);
			System.out.println( ++i + " 文档名称："+key + " 解析次数：" + map.get(key) + "次");
		}
		
		//排序
		BubbleSort.sort(sort);
		System.out.println(Arrays.toString(sort));		
	}
	
	
	public class ThreadExecuteTime {
		
		String name;
		//第一次请求开始时间
		Date firstRequestStartTime;
		//第一次请求结束时间
		Date firstRequestEndTime;
		
		//第二次请求开始时间
		Date secondRequestStartTime;
		//第二次请求结束时间
		Date secondRequestEndTime;
		
		String filename;
		
		
		int docToPdfTime;
		int pdfToJpgTime;
		
		
	}

}
