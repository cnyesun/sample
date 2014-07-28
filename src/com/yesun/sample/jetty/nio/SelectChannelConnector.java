package com.yesun.sample.jetty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.yesun.sample.jetty.server.Connector;
import com.yesun.sample.jetty.server.Server;

public class SelectChannelConnector extends AbstractConnector implements Connector{
	
	//存放客户端的SocketChannel
	private final ConcurrentLinkedQueue<Object> channels = new ConcurrentLinkedQueue<Object>();
	private Logger log = Logger.getLogger(this.getClass());
	private Server server;
	private int port = 80;
	private String host ;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}
	
	@Override
	public void setServer(Server server) {
		this.server = server;
		super.setServer(server);
	}

	protected void doStart() throws Exception
    {
		
		super.doStart();
		
		//开启一个线程处理channel，可以开启多个
        server.getThreadPool().dispatch(new Runnable()
        {
            public void run()
            {
            	log.debug(Thread.currentThread().getName() +"　Connector Runner已启动，用户处理Channels.");
                String name=Thread.currentThread().getName();
                try
                {
                    Thread.currentThread().setName(name+" Selector  "+ this);
                    while (isRunning())
                    {
                    	//TODO,从channels中取得请求，并处理
                    	SocketChannel channel = (SocketChannel) channels.poll();
                    	if(channel != null) {
                    		log.debug("处理channel" + channel);
                    	}
                    }
                }
                finally
                {
                    Thread.currentThread().setName(name);
                }
            }
        });
    }
	
	
	protected ServerSocketChannel _acceptChannel;
	private transient Thread[] _acceptorThread;
	
	

	/* (non-Javadoc)
	 * @see com.dheaven.sample.jetty.server.Connector#getConnection()
	 */
	@Override
	public Object getConnection() {
		return _acceptChannel;
	}

	@Override
	public void accept(int acceptorID) throws IOException{

		log.info("accept["+acceptorID+"]...");
        ServerSocketChannel server = _acceptChannel;
        if (server!=null && server.isOpen())
        {
            SocketChannel channel = _acceptChannel.accept();//阻塞，等待客户端连接
            channel.configureBlocking(false);
            Socket socket = channel.socket();
            configure(socket);
            //存入channels，等待被处理
            log.debug(Thread.currentThread().getName() + " save channel");
            channels.add(channel);
//            _manager.register(channel);//客户端channel存入到SelectSet中，等待处理
        }
		
	}
	
	protected void configure(Socket socket) throws IOException
    {
        try
        {
            socket.setTcpNoDelay(true);
            socket.setSoLinger(false,0);
        }
        catch (Exception e)
        {
        }
    }

	@Override
	public void setPort(int port) {
		this.port = port;
	}


	@Override
	public String getName() {
		return null;
	}

	/* 
	 * open socket
	 * (non-Javadoc)
	 * @see com.dheaven.sample.jetty.server.Connector#open()
	 */
	@Override
	public void open() throws IOException {

		 synchronized(this)
	        {
	            if (_acceptChannel == null)
	            {
	                // Create a new server socket
	                _acceptChannel = ServerSocketChannel.open();
	                log.debug(_acceptChannel);
	                // Set to blocking mode
	                _acceptChannel.configureBlocking(true);//设置阻塞模式，上面的accept才能阻塞

	                // Bind the server socket to the local host and port
	                _acceptChannel.socket().setReuseAddress(true);
	                InetSocketAddress addr = getHost()==null?new InetSocketAddress(getPort()):new InetSocketAddress(getHost(),getPort());
	                _acceptChannel.socket().bind(addr);
	            }
	        }
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}


}
