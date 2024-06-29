package com.pk.runners;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExamResultTestRunner implements CommandLineRunner {

	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;
	@Override
	public void run(String... args) throws Exception {
		JobParameters params= new JobParametersBuilder().addDate("jobDate", new Date()).toJobParameters();
		
		JobExecution run = launcher.run(job, params);
		System.out.println("The status of run is :: "+run.getStatus());

	}

}
