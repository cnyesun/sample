package com.yesun.sample.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * @author yesun
 * 测试Reactor NIO
 */
public class Reactor implements Runnable{
	
	final Selector selector;
	final ServerSocketChannel serverSocket;
	/*标识数字*/
    private  int flag = 0;
    /*缓冲区大小*/
    private  int BLOCK = 4096;
    /*接受数据缓冲区*/
    private  ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

	
	public static void main(String[] args) {
		Reactor server;
		try {
			server = new Reactor(9999);
			server.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 构造
	 * @param port
	 * @throws IOException 
	 */
	public Reactor(int port) throws IOException {
		InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);

		serverSocket = ServerSocketChannel.open();
		serverSocket.socket().bind(address);
		serverSocket.configureBlocking(false);
		selector = Selector.open();
		//注册Accept事件，向selector注册channel
		SelectionKey key = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("--> start server register!");
		//绑定Accept事件的具体实现
		//key.attach(new Acceptor());
		System.out.println("--> attach accept!");
	}

	@Override
	public void run() {

		while(!Thread.interrupted()) {
			try {
				selector.select();
				Set<SelectionKey> selected = selector.selectedKeys();   
				Iterator<SelectionKey> it = selected.iterator();   
				//Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。   
				while (it.hasNext()){
					//来一个事件 第一次触发一个accepter线程   
					SelectionKey key = it.next();
					it.remove();
					//以后触发SocketReadHandler
					System.out.println("--> dispatch");
					dispatch(key);
				}
				selected.clear();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void dispatch(SelectionKey selectionKey) throws IOException {
		
		 // 接受请求
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count=0;
        // 测试此键的通道是否已准备好接受新的套接字连接。
        if (selectionKey.isAcceptable()) {
            // 返回为之创建此键的通道。
            server = (ServerSocketChannel) selectionKey.channel();
            // 接受到此通道套接字的连接。
            // 此方法返回的套接字通道（如果有）将处于阻塞模式。
            client = server.accept();
            // 配置为非阻塞
            client.configureBlocking(false);
            // 注册到selector，等待连接
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            // 返回为之创建此键的通道。
            client = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下次读取
            receivebuffer.clear();
            //读取服务器发送来的数据到缓冲区中
            count = client.read(receivebuffer);
            if (count > 0) {
                receiveText = new String( receivebuffer.array(),0,count);
                System.out.println("服务器端接受客户端数据--:"+receiveText);
                client.register(selector, SelectionKey.OP_WRITE);
            }
        } else if (selectionKey.isWritable()) {
            //将缓冲区清空以备下次写入
            sendbuffer.clear();
            // 返回为之创建此键的通道。
            client = (SocketChannel) selectionKey.channel();
            sendText="message from server--" + flag++;
            //向缓冲区中输入数据
            sendbuffer.put(sendText.getBytes());
             //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            sendbuffer.flip();
            //输出到通道
            client.write(sendbuffer);
            System.out.println("服务器端向客户端发送数据--："+sendText);
            client.register(selector, SelectionKey.OP_READ);
        }

		
		
		
	}
	
	/**
	 * @author yesun
	 * 绑定在accept事件上的实现
	 */
	class Acceptor implements Runnable {

		@Override
		public void run() {
			System.out.println("--> ready for accept");
			SocketChannel channel;
			try {
				channel = serverSocket.accept();
				if(channel != null) {
					//调用Handler来处理channel 
					new SocketReadHandler(selector, channel);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @author yesun
	 * 业务实现
	 */
	class SocketReadHandler implements Runnable {
		
		final SocketChannel channel;
		final SelectionKey key;
		public SocketReadHandler(Selector selector, SocketChannel channel) throws IOException {
			this.channel = channel;
			channel.configureBlocking(false);
			key = channel.register(selector, 0);
			key.attach(this);
			
			//标记可读
			key.interestOps(SelectionKey.OP_READ);
			selector.wakeup();
		}
		

		@Override
		public void run() {
			
			Set<SelectionKey> selected = selector.selectedKeys();   
			Iterator<SelectionKey> it = selected.iterator();   
			//Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。   
			while (it.hasNext()){
				//来一个事件 第一次触发一个accepter线程   
				SelectionKey key = (SelectionKey)(it.next());
				it.remove();
				//以后触发SocketReadHandler
				System.out.println("--> read");
				
				if(key.isReadable()) {
					try {
						readRequest();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				it.remove();
			}
			selected.clear();  
			
		}
		
		private void readRequest() throws IOException {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.clear();
			int len = channel.read(buffer);
			//激活线程池，处理这些请求，此处再使用多线程处理
			//TODO
			System.out.println("process...");
		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
