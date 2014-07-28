package com.yesun.sample.jetty.util.component;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

public abstract class AbstractLifeCycle implements LifeCycle {

	public static final String STOPPED="STOPPED";
    public static final String FAILED="FAILED";
    public static final String STARTING="STARTING";
    public static final String STARTED="STARTED";
    public static final String STOPPING="STOPPING";
    public static final String RUNNING="RUNNING";
    
    Logger log = Logger.getLogger(getClass());
    
    private final Object _lock = new Object();
    private final int __FAILED = -1, __STOPPED = 0, __STARTING = 1, __STARTED = 2, __STOPPING = 3;
    private volatile int _state = __STOPPED;
    
    protected final CopyOnWriteArrayList<LifeCycle.Listener> _listeners=new CopyOnWriteArrayList<LifeCycle.Listener>();

    
    //doStart空方法，由子类调用，也可以子类重写，更灵活，所以没有采用abstract
    protected void doStart() throws Exception {
    	
    }
    //doStop空方法，由子类调用，也可以子类重写，所以没有采用abstract
    protected void doStop() throws Exception {
    	
    }

	/* 
	 * 启动流程固化，不允许重写
	 * (non-Javadoc)
	 * @see com.dheaven.mip.sfp.util.component.LifeCycle#start()
	 */
	@Override
	public final void start() throws Exception {
		synchronized (_lock){
            try{
                if (_state == __STARTED || _state == __STARTING)
                    return;
                setStarting();
                doStart();
                setStarted();
            }
            catch (Exception e){
                setFailed(e);
                throw e;
            }
            catch (Error e) {
                setFailed(e);
                throw e;
            }
        }
	}

	@Override
	public final void stop() throws Exception {
		synchronized (_lock){
            try{
                if (_state == __STOPPING || _state == __STOPPED)
                    return;
                setStopping();
                doStop();
                setStopped();
            }
            catch (Exception e){
                setFailed(e);
                throw e;
            }
            catch (Error e){
                setFailed(e);
                throw e;
            }
        }
	}

	@Override
	public boolean isRunning() {
		final int state = _state;
        return state == __STARTED || state == __STARTING;
	}

	@Override
	public boolean isStarting() {
		return _state == __STARTED;
	}

	@Override
	public boolean isStarted() {
		return _state == __STARTING;
	}

	@Override
	public boolean isStopping() {
		return _state == __STOPPING;
	}

	@Override
	public boolean isStopped() {
		return _state == __STOPPED;
	}

	@Override
	public boolean isFailed() {
		return _state == __FAILED;
	}

	@Override
	public void addLifeCycleListener(Listener listener) {
		_listeners.add(listener);
	}

	@Override
	public void removeLifeCycleListener(Listener listener) {
		_listeners.remove(listener);
	}

	public String getState()
    {
        switch(_state)
        {
            case __FAILED: return FAILED;
            case __STARTING: return STARTING;
            case __STARTED: return STARTED;
            case __STOPPING: return STOPPING;
            case __STOPPED: return STOPPED;
        }
        return null;
    }
    
    public static String getState(LifeCycle lc)
    {
        if (lc.isStarting()) return STARTING;
        if (lc.isStarted()) return STARTED;
        if (lc.isStopping()) return STOPPING;
        if (lc.isStopped()) return STOPPED;
        return FAILED;
    }

    private void setStarted()
    {
        _state = __STARTED;
        log.debug(STARTED + this);
        for (Listener listener : _listeners)
            listener.lifeCycleStarted(this);
    }

    private void setStarting()
    {
        log.debug("starting " + this);
        _state = __STARTING;
        for (Listener listener : _listeners)
            listener.lifeCycleStarting(this);
    }

    private void setStopping()
    {
        log.debug("stopping "+this);
        _state = __STOPPING;
        for (Listener listener : _listeners)
            listener.lifeCycleStopping(this);
    }

    private void setStopped()
    {
        _state = __STOPPED;
        log.debug(STOPPED+" "+this);
        for (Listener listener : _listeners)
            listener.lifeCycleStopped(this);
    }

    private void setFailed(Throwable th)
    {
        _state = __FAILED;
        log.warn(FAILED+" " + this + " " + th);
        for (Listener listener : _listeners)
            listener.lifeCycleFailure(this,th);
    }
}
