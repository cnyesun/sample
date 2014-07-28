package com.yesun.sample.jetty.nio;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.yesun.sample.jetty.server.Connector;
import com.yesun.sample.jetty.server.Server;
import com.yesun.sample.jetty.thread.ThreadPool;
import com.yesun.sample.jetty.util.component.AbstractLifeCycle;
import com.yesun.sample.jetty.util.component.LifeCycle;

public abstract class AbstractConnector extends AbstractLifeCycle implements Connector{

    private Server _server;
    private ThreadPool _threadPool;
    private String _host;
    private int _port = 0;
    private int _acceptors = 1;	
    private Logger log = Logger.getLogger(this.getClass());
    private transient Thread[] _acceptorThread;
    
	public int getAcceptors() {
		return _acceptors;
	}

	public void setAcceptors(int _acceptors) {
		this._acceptors = _acceptors;
	}
	
	@Override
	public void setServer(Server server) {
		this._server = server;
	}
	
	@Override
    protected void doStart() throws Exception
    {
        // open listener port
        open();

        super.doStart();

        if (_threadPool == null)
            _threadPool = _server.getThreadPool();
        if (_threadPool != _server.getThreadPool() && (_threadPool instanceof LifeCycle))
            ((LifeCycle)_threadPool).start();

        // Start selector thread
        synchronized (this)
        {
            _acceptorThread = new Thread[getAcceptors()];

            for (int i = 0; i < _acceptorThread.length; i++)
                _threadPool.dispatch(new Acceptor(i));//开启accept监听，等待客户端连接
            if (_threadPool.isLowOnThreads())
                log.warn("insufficient threads configured for {}");
        }

    }

    /* ------------------------------------------------------------ */
    @Override
    protected void doStop() throws Exception
    {
        try
        {
            close();
        }
        catch (IOException e)
        {
        }

        if (_threadPool != _server.getThreadPool() && _threadPool instanceof LifeCycle)
            ((LifeCycle)_threadPool).stop();

        super.doStop();

        Thread[] acceptors = null;
        synchronized (this)
        {
            acceptors = _acceptorThread;
            _acceptorThread = null;
        }
        if (acceptors != null)
        {
            for (int i = 0; i < acceptors.length; i++)
            {
                Thread thread = acceptors[i];
                if (thread != null)
                    thread.interrupt();
            }
        }
    }
    
    private class Acceptor implements Runnable
    {
        int _acceptor = 0;

        Acceptor(int id)
        {
            _acceptor = id;
        }

        /* ------------------------------------------------------------ */
        public void run()
        {
            Thread current = Thread.currentThread();
            String name;
            synchronized (this)
            {
                if (_acceptorThread == null)
                    return;

                _acceptorThread[_acceptor] = current;
                name = _acceptorThread[_acceptor].getName();
                current.setName(name + " Acceptor" + _acceptor + " " + this);
            }
            int old_priority = current.getPriority();

            try
            {
                while (isRunning() && getConnection() != null)
                {
                    try
                    {
                    	//调用子类的实现，即SelectChannelConnector中的accept，然后阻塞，等待连接
                        accept(_acceptor);
                    }
                    catch (ThreadDeath e)
                    {
                        throw e;
                    }
                    catch (Throwable e)
                    {
                    }
                }
            }
            finally
            {
                current.setPriority(old_priority);
                current.setName(name);

                synchronized (this)
                {
                    if (_acceptorThread != null)
                        _acceptorThread[_acceptor] = null;
                }
            }
        }
    }

}
