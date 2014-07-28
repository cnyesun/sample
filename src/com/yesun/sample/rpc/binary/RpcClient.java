package com.yesun.sample.rpc.binary;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RpcClient {
	
	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Sep 25, 2013 11:28:00 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		String charset = "UTF-8";
		try {
			//http://localhost/user.do?action=download
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost/user.do?action=download").openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
//			connection.setRequestProperty("content-type", "text/html");//设置服务器处理流的方式
			connection.connect();//打开连接
			
			//输出数据
			ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
			oos.writeObject("yesun");
			oos.flush();
			InputStream response = connection.getInputStream(); 
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
