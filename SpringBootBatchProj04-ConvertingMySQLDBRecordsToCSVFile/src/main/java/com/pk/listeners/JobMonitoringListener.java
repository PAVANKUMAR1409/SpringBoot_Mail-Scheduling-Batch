package com.pk.listeners;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobMonitoringListener implements JobExecutionListener {
	private long start,end;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
			start=System.currentTimeMillis();
			System.out.println("The execution Started at :: "+new Date());

	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		end=System.currentTimeMillis();
		System.out.println("The execution ended at :: "+new Date());
		System.out.println("The job execution status :: "+jobExecution.getStatus());
		System.out.println("Total time for execution is ::"+(end-start)+" ms");


	}

}
