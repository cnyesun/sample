package com.yesun.sample.scheduled;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.JobBuilder.*; 



/**
 * sample JobRunner.java
 * Description: 统一JOB管理器
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * 1.0 YESUN 2013年11月25日 下午4:44:24 Create.
 * ChangeLog:
 */
public class JobRunner {
	
	private static Logger log = Logger.getLogger(JobRunner.class);
	private static boolean isRunning = false;
	private static Object monitor = new Object();
	public static Scheduler scheduler = null;
	private static ConcurrentHashMap<Class<? extends Job>,Trigger> map = new ConcurrentHashMap<Class<? extends Job>,Trigger>();
	
	
	/**
	 * Description:启动JOB管理器
	 * 1.0 YESUN 2013年11月25日 下午4:56:28 Create.
	 * ChangeLog:
	 */
	public static void start(){
		//start jobRunner
		synchronized(monitor) {
			try {
				scheduler = new org.quartz.impl.StdSchedulerFactory().getScheduler();
				scheduler.start();//启动JOB管理器
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			}
			
			//加载队列中的job任务
			Iterator<Class<? extends Job>> iterator = map.keySet().iterator();
			while(iterator.hasNext()){
				Class<? extends Job> job = iterator.next();
				
				JobDetail jobDetail = newJob(job)
						.withIdentity(job.getSimpleName(), "jobrunner_group")
						.build();
				try {
					log.info("start job " + jobDetail.toString());
					scheduler.scheduleJob(jobDetail, map.get(job));
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			}
			
			//更新状态
			isRunning = true;
		}
	}
	
	
	/**
	 * Description: 增加JOB，只有启动前加入有效
	 * 1.0 YESUN 2013年11月25日 下午4:43:07 Create.
	 * ChangeLog:
	 */
	public static void addJob(Class<? extends Job> jobClass, Trigger trigger) {
		
		if(isRunning ==  true){
			//直接启动，并存入队列
			JobDetail jobDetail = newJob(jobClass)
					.withIdentity(jobClass.getSimpleName(), "jobrunner_group")
					.build();
			try {
				log.debug("add job " + jobClass.toString() + ", execute job!");
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		else {
			//存入队列
			log.debug("add job " + jobClass.toString() + ", save to queue!");
			map.put(jobClass, trigger);
		}
	}
	
	/**
	 * Description:删除job
	 * @param jobClass
	 * @param trigger
	 * 1.0 YESUN 2013年11月27日 上午11:01:35 Create.
	 * ChangeLog:
	 */
	public static void removeJob(Class<? extends Job> jobClass, Trigger trigger) {
		
		log.debug("remove job " + jobClass.toString() + "!");
		
		//remove from map
		if(map.contains(jobClass)) {
			map.remove(jobClass);
		}
		
		//remove from scheduler
		try {
			scheduler.deleteJob(trigger.getJobKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Description:暂停job
	 * @param jobClass
	 * @param trigger
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月27日 上午11:01:26 Create.
	 * ChangeLog:
	 */
	public static void pauseJob(Class<? extends Job> jobClass, Trigger trigger) {
		//pause from scheduler
		try {
			log.debug("pause job " + jobClass.toString() + "!");
			scheduler.pauseJob(trigger.getJobKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Description:恢复job	 
	 * @param jobClass
	 * @param trigger
	 * 1.0 YESUN 2013年11月27日 上午11:01:17 Create.
	 * ChangeLog:
	 * 问题1：job恢复后，job会将暂停期间要执行的次数全部立即执行掉，如果不需要恢复，则应当采取删除job，然后重新执行job
	 * 
	 * 
	 */
	public static void resumeJob(Class<? extends Job> jobClass, Trigger trigger) {
		//resume from scheduler
		try {
			log.debug("resume job " + jobClass.toString() + "!");
			scheduler.resumeJob(trigger.getJobKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
