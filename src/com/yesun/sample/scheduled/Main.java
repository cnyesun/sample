package com.yesun.sample.scheduled;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.quartz.Trigger;

import static org.quartz.TriggerBuilder.*; 
import static org.quartz.SimpleScheduleBuilder.*; 



public class Main {
	
	
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("log4j.properties");
		System.out.println("start...");
		
		Logger log = Logger.getLogger(Main.class);
		
		JobRunner.start();
		
		Trigger trigger = newTrigger() 
		        .withIdentity("dataSourceMonitorTrigger", "mdp") 
		        .startNow()
		        .withSchedule(simpleSchedule() 
		                .withIntervalInSeconds(10)
		                .repeatForever())
		        .build();
		log.debug("add job");
		JobRunner.addJob(MdpDataSourceJob.class, trigger);
		
		//对mdp job的操作并不会影响dps job
		Trigger trigger1 = newTrigger() 
		        .withIdentity("dpsMonitorTrigger", "dps") 
		        .startNow()
		        .withSchedule(simpleSchedule() 
		                .withIntervalInSeconds(10)
		                .repeatForever())
		        .build();
		log.debug("add dps job");
		JobRunner.addJob(DpsDataSourceJob.class, trigger1);
		
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("pause job");
		JobRunner.pauseJob(MdpDataSourceJob.class, trigger);
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("resume job");
		JobRunner.resumeJob(MdpDataSourceJob.class, trigger);
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("remove job");
		JobRunner.removeJob(MdpDataSourceJob.class, trigger);
		
		
		
	}
	
	
	
	

}
