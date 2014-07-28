package com.yesun.sample.jetty.thread;

/**
 * mipPlugin-sfp ThreadPool.java
 * Description: 线程池接口
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN 2013年11月28日 下午2:28:32 Create.
 * ChangeLog:
 */
public interface ThreadPool {
	
	/**
	 * Description: 向线程池中加入任务
	 * @param job
	 * @return
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 下午2:29:23 Create.
	 * ChangeLog:
	 */
	public boolean dispatch(Runnable job);
	
	/**
	 * Description: block until the thread pool stopped.
	 * @throws InterruptedException
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 下午2:30:51 Create.
	 * ChangeLog:
	 */
	public void join() throws InterruptedException;
	
	/**
	 * Description: the total number of the threads currently in the pool.
	 * @return
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 下午2:31:46 Create.
	 * ChangeLog:
	 */
	public int getThreads();
	
	/**
	 * Description: the number of idle threads in the pool.
	 * @return
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 下午2:32:49 Create.
	 * ChangeLog:
	 */
	public int getIdleThreads();
	
	/**
	 * Description:
	 * @return
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 下午2:33:30 Create.
	 * ChangeLog:
	 */
	public boolean isLowOnThreads();
	
	
	public interface SizedThreadPool extends ThreadPool {
        public int getMinThreads();
        public int getMaxThreads();
        public void setMinThreads(int threads);
        public void setMaxThreads(int threads);
    }

}
