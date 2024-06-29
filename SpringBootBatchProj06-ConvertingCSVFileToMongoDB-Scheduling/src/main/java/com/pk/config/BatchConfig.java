package com.pk.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.pk.listeners.JobMonitoringListener;
import com.pk.model.ExamResult;
import com.pk.proccesor.IExamResultsProcessor;

@Configuration
public class BatchConfig {
	
	
	@Autowired
	private JobMonitoringListener listener;
	@Autowired
	private IExamResultsProcessor processor;
	@Autowired
	private MongoTemplate template;

	@Bean(name="reader")
	public FlatFileItemReader<ExamResult> createReader(){
		return new FlatFileItemReaderBuilder<ExamResult>()
					.name("reader")
					.resource(new ClassPathResource("topbrains.csv"))
					.delimited().delimiter(",")
					.names("id","dob","semester","percentage")
					.targetType(ExamResult.class)
					.build();
	}
	
	@Bean(name="writer")
	public MongoItemWriter<ExamResult> createWriter(){
		//System.out.println("Table is created");
		return new MongoItemWriterBuilder<ExamResult>()
					.collection("ultimatebrains98")
					.template(template)
					.build();
	}
	
	@Bean(name="step1")
	public Step createStep1(JobRepository jobRepo,PlatformTransactionManager ptm) {
		return new StepBuilder("step1",jobRepo)
					.<ExamResult,ExamResult>chunk(3,ptm)
					.reader(createReader())
					.processor(processor)
					.writer(createWriter())
					.build();				
	}
	
	@Bean(name="job")
	public Job createJob(JobRepository jobRepo, Step step1) {
		return new JobBuilder("job",jobRepo)
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(step1)
				.build();
	}
}
