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
public class BatchTestRunner implements CommandLineRunner {
	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("BatchTestRunner.run()");
		JobParameters param= new JobParametersBuilder()
																.addDate("date",new Date())
																.toJobParameters();
		JobExecution run = launcher.run(job, param);
		System.out.println(run.getStartTime());
		System.out.println(run.getEndTime());
		System.out.println(run.getExitStatus());
		

	}

}
