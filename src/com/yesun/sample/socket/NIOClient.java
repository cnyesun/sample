package com.yesun.sample.socket;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NIOClient {
	
	private static int SIZE = 2;
	private final static int bufferSize = 500 * 1024;
	private static InetSocketAddress ip = new InetSocketAddress("localhost", 12345);
	private static CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
	
	public static class Download implements Runnable{

		protected int index;
		String outfile = "";
		
		public Download(int index){
			this.index = index;
			this.outfile = "c:\\download_nio_" + index + ".mp3";
		}
		
		@Override
		public void run() {
			FileOutputStream foStream = null;
			try {
				foStream = new FileOutputStream(outfile);
				long start = System.currentTimeMillis();
				try {
					SocketChannel client = SocketChannel.open();
					client.configureBlocking(false);
					Selector selector = Selector.open();
					client.register(selector, SelectionKey.OP_CONNECT);
					client.connect(ip);
					
					ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
					int total = 0;
					FOR:for(;;){
						//没有判断的死循环
						selector.select();
						Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
						while(iterator.hasNext()){
							SelectionKey key = iterator.next();
							System.out.println("Thread" + index + " >>> " + key.readyOps());
							iterator.remove();
							if(key.isConnectable()){
								//获取管道对象
								SocketChannel channel = (SocketChannel) key.channel();
								//判断管道对象已经连接好了
								if(channel.isConnectionPending()){
									channel.finishConnect();
								}
								
								//向管道里写一些信息
								channel.write(encoder.encode(CharBuffer.wrap("c:\\1.mp3")));
								//注册读事件，为了让对方server端能够读取我们写的内容
								channel.register(selector, SelectionKey.OP_READ);	
							}
							else if(key.isReadable()){
								
								//获取管道对象
								SocketChannel channel = (SocketChannel) key.channel();
								
								//从管道中读取数据到缓冲区
								int count = channel.read(buffer);
								if(count > 0){
									total += count;
									//buffer.clear();
									buffer.flip();
									if(count < bufferSize){
										byte[] overByte = new byte[count];
										for(int index=0;index<count;index++){
											overByte[index] = buffer.get(index);
										}
										foStream.write(overByte);
									}
									else{
										foStream.write(buffer.array());
									}
								}
								else{
									break FOR;
								}
							}
						}
					}

					double last = (System.currentTimeMillis() - start) * 1.0 / 1000;
					System.out.println("Thread" + index + " download " + total /1024 + "kbytes in" + last + "s.");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					foStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}
	
	}
	
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		ExecutorService exec = Executors.newFixedThreadPool(SIZE);
		for(int index = 0;index<1;index++){
			exec.execute(new Download(index));
		}
		exec.shutdown();
		
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		System.out.println("download time: " + time);
	}

}
