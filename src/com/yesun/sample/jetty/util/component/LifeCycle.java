package com.yesun.sample.jetty.util.component;

import java.util.EventListener;

/**
 * mipPlugin-sfp LifeCycle.java
 * Description: the lifecycle interface for generic component
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN 2013年11月28日 上午8:49:07 Create.
 * ChangeLog:
 */
public interface LifeCycle {

	/**
	 * Description: start the component.
	 * @throws Exception
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:07 Create.
	 * ChangeLog:
	 */
	public void start() throws Exception;
	
	/**
	 * Description: stop the component.
	 * @throws Exception
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:09 Create.
	 * ChangeLog:
	 */
	public void stop() throws Exception;
	
	/**
	 * Description:true if the component is starting or has been started. 
	 * @return
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:11 Create.
	 * ChangeLog:
	 */
	public boolean isRunning();
	
	/**
	 * Description:true if the component is starting.
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:14 Create.
	 * ChangeLog:
	 */
	public boolean isStarting();
	
	/**
	 * Description:true if the component has been started.
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:16 Create.
	 * ChangeLog:
	 */
	public boolean isStarted();
	
	/**
	 * Description:true if the component is stopping.
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:22 Create.
	 * ChangeLog:
	 */
	public boolean isStopping();
	
	/**
	 * Description:true if the component has been stopped.
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:18 Create.
	 * ChangeLog:
	 */
	public boolean isStopped();
	
	/**
	 * Description:true if the component has faile to start or has faile to stop.
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:59:24 Create.
	 * ChangeLog:
	 */
	public boolean isFailed();
	
	/**
	 * Description:add listener for this component
	 * @param listener
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:19 Create.
	 * ChangeLog:
	 */
	public void addLifeCycleListener(LifeCycle.Listener listener);
	
	/**
	 * Description: remove listener for this component
	 * @param listener
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:53:20 Create.
	 * ChangeLog:
	 */
	public void removeLifeCycleListener(LifeCycle.Listener listener);
	
	/**
	 * mipPlugin-sfp LifeCycle.java
	 * Description: A listener for lifecycle events
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午8:52:33 Create.
	 * ChangeLog:
	 */
	public interface Listener extends EventListener {

		public void lifeCycleStarting(LifeCycle event);
		public void lifeCycleStarted(LifeCycle event);
		public void lifeCycleFailure(LifeCycle event, Throwable cause);
		public void lifeCycleStopping(LifeCycle event);
		public void lifeCycleStopped(LifeCycle event);
		
	}

}
