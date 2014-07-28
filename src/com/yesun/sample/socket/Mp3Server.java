package com.yesun.sample.socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Mp3Server {

	/**
	 * Description: 同步单线程下载（原始）
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-3-27 上午8:50:47 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		Mp3Server server = new Mp3Server();
		try {
			server.server();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void server() throws Exception{
		
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("server start...");
		while(true){
			//等待客户端请求
			final Socket server = serverSocket.accept();
			System.out.println("client " + server.getLocalAddress() + " connected!");
			//读取socket输入流
			final InputStream is = server.getInputStream();
			byte[] buffer = new byte[1024];
			int readCount = is.read(buffer);
			
			//这里是单线程阻塞模式，效率比较低，依次处理客户端请求，如果需要提高效率，可以改成多线程阻塞模式，以下代码new一个线程来处理
			
			String str = new String(buffer, "utf-8");
			
			System.out.println("client request : " + str);
			
			FileInputStream fiStream = new FileInputStream(str);
			OutputStream os = server.getOutputStream();
			
			buffer = new byte[1024];
			while(fiStream.read(buffer) > 0){
				os.write(buffer);
			}
			fiStream.close();
			os.close();	
			is.close();
			System.out.println("client " + server.getLocalAddress() + " close!");
			server.close();
			
		}

		
		
		
	}
	
	

}
