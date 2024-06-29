package com.pk.listeners;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component("jobListener")
public class JobMonitorningListener implements JobExecutionListener{
	
	private long start,end;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("JobMonitorningListener.beforeJob()::Job Started::=>"+new Date());
		start=System.currentTimeMillis();
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("JobMonitorningListener.afterJob()::=>"+ jobExecution.getStatus()+"  Job Ended::=>"+new Date());
		end=System.currentTimeMillis();
		System.out.println("Job Execution time is : "+(end-start));
	}
}
