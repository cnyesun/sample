package com.yesun.sample.socket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

public class NIOServer {
	
	/**
	 * Description: NIO Server
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-3-27 上午9:12:37 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		int port = 12345;
		try {
			NIOServer server = new NIOServer(port);
			System.out.println("server start,listener on " + port);
			while(true){
				server.listen();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	static int BLOCK = 500*1024;
	
	public class HandleClient{
		protected FileChannel channel;
		protected ByteBuffer buffer;
		String filePath;
		
		public HandleClient(String filePath) throws FileNotFoundException{
			//文件管道
			this.channel = new FileInputStream(filePath).getChannel();
		
			//建立缓冲区
			this.buffer = ByteBuffer.allocate(BLOCK);
			this.filePath = filePath;
		}
		
		/**
		 * Description:读取管道数据到缓冲区
		 * @return
		 * Copyright (c) Digital Heaven. All Rights Reserved.
		 * 1.0 YESUN 2012-3-27 上午9:19:17 Create.
		 * ChangeLog:
		 */
		public ByteBuffer readBlock(){
			//清除缓冲区数据
			buffer.clear();
			
			int count;
			try {
				count = channel.read(buffer);
				buffer.flip();
				if(count <=0 ){
					return null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return buffer;
		}
		
		public void close(){
			try {
				channel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	protected Selector selector;
	protected String filename = "c:\\3.mp3";
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(BLOCK);
	protected CharsetDecoder decoder;
	
	public NIOServer(int port) throws IOException{
		selector = this.getSelector(port);
		Charset charset = Charset.forName("utf-8");
		decoder = charset.newDecoder();
	}
	
	protected Selector getSelector(int port) throws IOException{
		ServerSocketChannel server = ServerSocketChannel.open();
		Selector selector = Selector.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		//注册链接事件
		server.register(selector, SelectionKey.OP_ACCEPT);
		return selector;
	}
	
	
	/**
	 * Description:服务启动监听
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-3-27 上午9:26:41 Create.
	 * ChangeLog:
	 */
	public void listen(){
		
		try {
			for(;;){
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					iterator.remove();
					//处理事件
					handleKey(key);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected void handleKey(SelectionKey key) throws IOException{
		if(key.isAcceptable()){
			//接收请求
			
			//允许网络链接事件
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);
			//网络管道准备处理读事件
			channel.register(selector, SelectionKey.OP_READ);			
		}
		else if(key.isReadable()){
			//读信息
			SocketChannel channel = (SocketChannel) key.channel();
			
			int count = channel.read(clientBuffer);
			if(count > 0){
				clientBuffer.flip();
				CharBuffer charBuffer = decoder.decode(clientBuffer);
				System.out.println("Client >> download >> " + charBuffer.toString());
				
				//对网络管道注册写事件
				SelectionKey writeKey = channel.register(selector, SelectionKey.OP_WRITE);
				writeKey.attach(new HandleClient(charBuffer.toString()));

			}
			else{
				//如果客户端没有可读事件
				channel.close();
			}
			clientBuffer.clear();
		}
		else if(key.isWritable()){
			
			
			//写事件
			SocketChannel channel = (SocketChannel) key.channel();
			HandleClient handle = (HandleClient) key.attachment();
			ByteBuffer block = handle.readBlock();

			if(block != null){
				System.out.println("write " + System.currentTimeMillis() + " size:" + block.capacity()); 
				//写给客户端
				channel.write(block);
			}
			else{
				channel.close();
				channel.close();
			}
		}
	}

}
