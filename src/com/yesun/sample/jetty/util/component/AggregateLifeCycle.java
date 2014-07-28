package com.yesun.sample.jetty.util.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;



public class AggregateLifeCycle extends AbstractLifeCycle implements Destroyable{
	
	private final Queue<Object> _dependentBeans = new ConcurrentLinkedQueue<Object>();
	private Logger log = Logger.getLogger(getClass());


	@Override
	public void destroy() {
		for (Object o : _dependentBeans){
            if (o instanceof Destroyable) {
                ((Destroyable)o).destroy();
            }
        }
        _dependentBeans.clear();
	}

	/* 
	 * 先启动依赖，再启动当前父类
	 * (non-Javadoc)
	 * @see com.dheaven.mip.sfp.util.component.AbstractLifeCycle#doStart()
	 */
	@Override
	protected void doStart() throws Exception {
		for (Object o:_dependentBeans) {
            if (o instanceof LifeCycle)
                ((LifeCycle)o).start();
        }
		//调用父类doStart，如果父类使用了abstract则没法调用，所以父类采用空方法
        super.doStart();
	}

	/* 
	 * 先关闭当前父类，再关闭依赖
	 * (non-Javadoc)
	 * @see com.dheaven.mip.sfp.util.component.AbstractLifeCycle#doStop()
	 */
	@Override
	protected void doStop() throws Exception {
		super.doStop();
        for (Object o:_dependentBeans) {
            if (o instanceof LifeCycle)
                ((LifeCycle)o).stop();
        }
	}
	
	public boolean addBean(Object o) {
		if (o == null)
            return false;
        boolean added=false;
        if (!_dependentBeans.contains(o)) 
        {
            _dependentBeans.add(o);
            added=true;
        }
        
        try
        {
            if (isStarted() && o instanceof LifeCycle)
                ((LifeCycle)o).start();
        }
        catch (Exception e)
        {
            throw new RuntimeException (e);
        }
        return added;
	}
	
	public Collection<Object> getBeans(){
        return _dependentBeans;
    }
	
	public <T> List<T> getBeans(Class<T> clazz) {
        ArrayList<T> beans = new ArrayList<T>();
        Iterator<?> iter = _dependentBeans.iterator();
        while (iter.hasNext()) {
            Object o = iter.next();
            if (clazz.isInstance(o))
                beans.add((T)o);
        }
        return beans;
    }
	
	
	public<T> T getBean(Class<T> clazz) {
		
		Iterator<?> iter = _dependentBeans.iterator();
		T t = null;
		int count = 0;
		while(iter.hasNext()) {
			Object o = iter.next();
			if(clazz.isInstance(o)) {
				count++;
				if(t == null) {
					t = (T)o;
				}
			}
		}
		if (count>1) {
            log.debug("getBean({0}) 1 of {1}");
		}
		return t;
	}
	
	public void removeBeans () {
        _dependentBeans.clear();
    }

    public boolean removeBean (Object o) {
        if (o == null)
            return false;
        return _dependentBeans.remove(o);
    }
    

}
