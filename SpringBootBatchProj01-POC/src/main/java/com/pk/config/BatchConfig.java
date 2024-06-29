package com.pk.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pk.listeners.JobMonitorningListener;
import com.pk.processor.BookItemProcessor;
import com.pk.reader.BookItemReader;
import com.pk.writer.BookItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private BookItemProcessor processor;
	@Autowired
	private BookItemReader reader;
	@Autowired
	private BookItemWriter writer;
	@Autowired
	private JobMonitorningListener listener;
	@Autowired
	private StepBuilderFactory stepFactory;
	@Autowired
	private JobBuilderFactory jobFactory;
	
	@Bean(name="step1")
	public Step createStep1() {
		return stepFactory.get("step1")
					.<String,String>chunk(3)
					.reader(reader)
					.processor(processor)
					.writer(writer)
					.build();
	}
	@Bean(name="job")
	public Job createJob() {
		return jobFactory.get("job")
					.incrementer(new RunIdIncrementer())
					.listener(listener)
					.start(createStep1())
					.build();
	}
	

}
