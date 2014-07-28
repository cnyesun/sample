package com.yesun.sample.jetty.server;

import org.apache.log4j.Logger;

import com.yesun.sample.jetty.nio.SelectChannelConnector;
import com.yesun.sample.jetty.thread.QueuedThreadPool;
import com.yesun.sample.jetty.thread.ThreadPool;
import com.yesun.sample.jetty.util.component.AggregateLifeCycle;
import com.yesun.sample.jetty.util.component.LifeCycle;

/**
 * sample Server.java
 * Description:
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN 2013年11月29日 上午11:05:01 Create.
 * ChangeLog:
 * 
 * 总结：
 * 1、启动Connector，监听8080端口，可以多个
 * 2、有客户端连接了，将channel保存到队列
 * 3、有一个JOB定期处理channel队列
 * 4、还有一个专门启动Runnable的LOOP
 * 
 * 
 */
public class Server extends AggregateLifeCycle {
	
	Logger log = Logger.getLogger(this.getClass());
	private ThreadPool threadPool;
    private Connector[] connectors;
	
	public Server(int port) {
		//默认为一个SelectChannelConnector
        Connector connector = new SelectChannelConnector();
        connector.setPort(port);
        Connector connector1 = new SelectChannelConnector();
        connector1.setPort(port);
        setConnectors(new Connector[]{connector, connector1});
	}
	
	public void setConnectors(Connector[] connectors){
		this.connectors = connectors;
        if (this.connectors!=null)
        {
            for (int i=0;i<this.connectors.length;i++)
            	this.connectors[i].setServer(this);
        }
    }
    
    public ThreadPool getThreadPool()
    {
        return threadPool;
    }
    
    /* ------------------------------------------------------------ */
    /**
     * @param threadPool The threadPool to set.
     */
    public void setThreadPool(ThreadPool threadPool)
    {
        this.threadPool = threadPool;
    }
    
    @Override
    protected void doStart() throws Exception
    {
        
        if (threadPool==null)//新建线程池
            setThreadPool(new QueuedThreadPool());
        
        try { 
        	//启动Server
            super.doStart(); 
        } 
        catch(Throwable e) { 
        	log.debug(e);
        }
        
        //启动线程池JOB Runner
        ((LifeCycle)getThreadPool()).start();
        
        
        //启动Connector，可以设置多个
        if (this.connectors!=null){
            for (int i=0;i<this.connectors.length;i++){
                try{
                	//触发start()，connector将阻塞accept等待客户端连接
                	this.connectors[i].start();
                }
                catch(Throwable e) {
                	log.debug(e);
                }
            }
        }
    }

    /* ------------------------------------------------------------ */
    @Override
    protected void doStop() throws Exception
    {
        if (this.connectors!=null)
        {
            for (int i=this.connectors.length;i-->0;)
            {
                log.info("Graceful shutdown {}");
                try{this.connectors[i].close();}catch(Throwable e){log.debug(e);}
            }
        }
        
        if (this.connectors!=null)
        {
            for (int i=this.connectors.length;i-->0;)
                try{this.connectors[i].stop();}catch(Throwable e){log.debug(e);}
        }

        try {super.doStop(); } catch(Throwable e) { log.debug(e);}
       
    }
    
    
    public static void main(String[] args) {
    	Server server = new Server(8080);
    	try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	

}
