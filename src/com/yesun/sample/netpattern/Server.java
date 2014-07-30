package com.yesun.sample.netpattern;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * sample Server.java
 * Description: 经典NIO网络模型
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2014年7月29日 Create.
 * ChangeLog:
 */
public class Server {
     private final int PORT = 1234;
     private ExecutorService pool;
     private ServerSocketChannel ssc;
     private Selector selector;
     private static Charset charset = Charset.forName("utf-8");
     private int n;
    
     public static void main(String[] args) throws IOException{
          Server server = new Server();
          server.start();
     }
    
     public Server() throws IOException{
          pool = Executors.newFixedThreadPool(5);
         
          ssc = ServerSocketChannel.open();
          ssc.configureBlocking(false);
          ServerSocket ss = ssc.socket();
          ss.bind(new InetSocketAddress(PORT));
          selector = Selector.open();
          ssc.register(selector,SelectionKey.OP_ACCEPT);
          System.out.println("Server started...");
     }
    
     
     /**
     * Description: 启动服务
     * @version 1.0 YESUN 2014年7月29日 Create.
     */
    public void start(){//由一个线程来处理所有的链接IO，但处理业务是多线程
          while(true){
               try{
                    n = selector.select();
               }catch (IOException e) {
                    throw new RuntimeException("Selector.select()异常!");
               }
               if(n==0)
                    continue;
               Set<SelectionKey> keys = selector.selectedKeys();
               Iterator<SelectionKey> iter = keys.iterator();
               while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    iter.remove();
                    if(key.isAcceptable()){
                         SocketChannel sc = null;
                         try{
                              sc = ((ServerSocketChannel)key.channel()).accept();
                              sc.configureBlocking(false);
                              System.out.println("客户端:"+sc.socket().getInetAddress().getHostAddress()+" 已连接");
                              SelectionKey k = sc.register(selector, SelectionKey.OP_READ);
                              ByteBuffer buf = ByteBuffer.allocate(1024);
                              k.attach(buf);
                         }catch (Exception e) {
                              try{
                                   sc.close();
                              }catch (Exception ex) {
                              }
                         }
                    }
                    else if(key.isReadable()){
                         key.interestOps(key.interestOps()&(~SelectionKey.OP_READ));
                         pool.execute(new Worker(key));//使用多线程处理业务，业务处理完，在各自线程中返回数据
                    }
               }
          }
     }
    
     public static class Worker implements Runnable{
          private SelectionKey key;
          public Worker(SelectionKey key){
               this.key = key;
          }
         
          @Override
          public void run() {
               SocketChannel sc = (SocketChannel)key.channel();
               ByteBuffer buf = (ByteBuffer)key.attachment();
               buf.clear();
               int len = 0;
               try{
                    while((len=sc.read(buf))>0){//非阻塞，立刻读取缓冲区可用字节
                         buf.flip();
                         
                         String msg = charset.decode(buf).toString();
                         
                         System.out.println(Thread.currentThread().getName() + "收到客户端信息：" + msg);

                         if(msg.equals("yesun")){
                        	 
                        	 Thread.sleep(30000);
                         }
                         //业务处理，返回数据
                         msg = msg + ",给你返回数据了";
                         try {
                 			ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes("UTF-8"));
                 			while(buffer.hasRemaining()) {
                 				sc.write(buffer);
                 			}
                 			
                 		} catch (IOException e) {
                 			if(sc.isOpen()){
                 				try {
                 					sc.close();
                 				} catch (IOException e1) {
                 					e1.printStackTrace();
                 				}
                 			}
                 			e.printStackTrace();
                 		}

                         System.out.println(Thread.currentThread().getName() + "客户端："+ msg);
                         
                         
                         buf.clear();
                    }
                    if(len==-1){
                         System.out.println(Thread.currentThread().getName() + "客户端断开。。。");
                         sc.close();
                    }
                    //没有可用字节,继续监听OP_READ
//                    key.interestOps(key.interestOps()|SelectionKey.OP_READ);
//                    key.selector().wakeup();
                    
                    key.interestOps(0);//业务处理完毕，取消注册事件，否则一直监听
               }catch (Exception e) {
                    try {
                         sc.close();
                    } catch (IOException e1) {
                    }
               }
          }
     }
}