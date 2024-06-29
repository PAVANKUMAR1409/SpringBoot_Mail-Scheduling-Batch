package com.pk.listeners;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobMonitoringListeners implements JobExecutionListener {

	private long start,end;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		start= System.currentTimeMillis();
		System.out.println("Job Execution started at :: "+new Date());
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		end= System.currentTimeMillis();
		System.out.println("Job Execution ended at :: "+new Date());
		System.out.println("job execution time is :: "+(end-start)+" ms");
		System.out.println("Job execution status :: "+jobExecution.getExitStatus());
	}
}
