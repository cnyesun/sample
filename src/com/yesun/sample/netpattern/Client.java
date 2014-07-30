package com.yesun.sample.netpattern;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
	
	SocketChannel channel;
	Selector selector;
	
	public Client(int port) throws IOException{
		
		//初始化
		InetSocketAddress addr = new InetSocketAddress(port);
		channel = SocketChannel.open();
		selector = Selector.open();
		
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_READ);
		
		channel.connect(addr);
		
		while (!channel.finishConnect()) {  
			System.out.println("check finish connection");  
		}  

	}
	
	public void start(String msg) throws IOException{
		System.out.println("send "+msg);
		while(true){
			
			write(msg);
			
			int sel = selector.select();
			if(sel > 0) {
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while(iterator.hasNext()) {
					 SelectionKey sk = iterator.next();
					 if(sk.isReadable()) {
						 //read
						 read(sk);
					 }
					 iterator.remove();
				}
			}
		}
		
	}
	
	public void read(SelectionKey sk) throws IOException {
		SocketChannel client = (SocketChannel) sk.channel();
		ByteBuffer buffer = ByteBuffer.allocate(256);  
		while (client.read(buffer) > 0) {
			buffer.flip();  
			System.out.println("Receive from server:" + new String(buffer.array(), "UTF-8"));  
			buffer.clear();
		}
	}
	
	
	public void write(String msg) {
		try {
			ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes("UTF-8"));
			while(buffer.hasRemaining()) {
				channel.write(buffer);
			}
			
		} catch (IOException e) {
			if(channel.isOpen()){
				try {
					channel.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		
		
	}
	

	public static void main(String[] args) throws IOException {
		
		Thread t1 = new Thread(){
			@Override
			public void run(){
				Client client;
				try {
					client = new Client(1234);
					client.start("yesun");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t1.start();
		
		
		Thread t2 = new Thread(){
			@Override
			public void run(){
				Client client;
				try {
					client = new Client(1234);
					client.start("dcm");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t2.start();

	}

}
