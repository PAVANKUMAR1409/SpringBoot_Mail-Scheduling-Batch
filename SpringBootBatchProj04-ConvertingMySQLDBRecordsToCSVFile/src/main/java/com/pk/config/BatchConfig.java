package com.pk.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.pk.listeners.JobMonitoringListener;
import com.pk.model.ExamResult;
import com.pk.proccesor.IExamResultsProcessor;

@Configuration
public class BatchConfig {
	
	@Autowired
	private DataSource ds;
	@Autowired
	private JobMonitoringListener listener;
	@Autowired
	private IExamResultsProcessor processor;

	@Bean(name="reader")
	public JdbcCursorItemReader<ExamResult> createReader(){
		return new JdbcCursorItemReaderBuilder<ExamResult>()
					.dataSource(ds)
					.sql("SELECT ID,DOB,PERCENTAGE,SEMESTER FROM EXAM_RESULT")
					.saveState(false)
					.beanRowMapper(ExamResult.class)
					.build();
	}
	
	@Bean(name="writer")
	public FlatFileItemWriter<ExamResult> createWriter(){
		return new FlatFileItemWriterBuilder<ExamResult>()
					.name("writer")
					.resource(new FileSystemResource("e:\\csv's\\topbrains.csv"))
					.lineSeparator("\r\n")
					.delimited().delimiter(",")
					.names("id","dob","percentage","semester")
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
