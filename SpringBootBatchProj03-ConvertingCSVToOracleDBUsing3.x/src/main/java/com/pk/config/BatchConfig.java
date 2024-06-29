package com.pk.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.pk.Entity.IEmployeeRepository;
import com.pk.Model.Employee;
import com.pk.listeners.JobMonitoringListeners;
import com.pk.processor.EmployeeItemProcessor;

@Configuration
public class BatchConfig {

	@Autowired
	private EmployeeItemProcessor processor;
	@Autowired
	private JobMonitoringListeners listeners;
	@Autowired
	private IEmployeeRepository empRepo;
	
	@Bean(name="reader")
	public FlatFileItemReader<Employee> createReader(){
		return new FlatFileItemReaderBuilder<Employee>()
				.name("file-reader")
				.resource(new ClassPathResource("employee-info.csv"))
				.delimited()
				.names("empno","ename","eadd","salary")
				.targetType(Employee.class)
				.build();
	}
	
	@Bean(name="writer")
	public RepositoryItemWriter<Employee> createWriter() {
	    return new RepositoryItemWriterBuilder<Employee>()
	            .repository(empRepo)
	            .methodName("save")
	            .build();
	}
	
	@Bean(name="step1")
	public Step createStep1(JobRepository jobRepo, PlatformTransactionManager ptm) {
		return new StepBuilder("step1",jobRepo)
					.<Employee,Employee>chunk(100, ptm)
					.reader(createReader())
					.processor(processor)
					.writer(createWriter())
					.build();
	}

	@Bean(name="job")
	public Job createJob(JobRepository jobRepo, Step step1) {
		return new JobBuilder("job", jobRepo)
				.listener(listeners)
				.start(step1)
				.build();
	}
	
}
