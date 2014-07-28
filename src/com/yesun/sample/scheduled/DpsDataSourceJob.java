package com.yesun.sample.scheduled;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DpsDataSourceJob implements Job{
	
	int i = 0;
	
	Logger log = Logger.getLogger(DpsDataSourceJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		log.debug("DPS JOB...");
		
		i++;
		
		if(i > 2){
			Thread.currentThread().interrupt();
		}
		
	}

}
