package com.pk.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.pk.listeners.JobMonitoringListener;
import com.pk.model.Employee;
import com.pk.processor.EmployeeInfoItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	//@Autowired
	@Autowired
	private JobMonitoringListener listener;
	@Autowired
	private JobBuilderFactory jobFactory;
	@Autowired
	private StepBuilderFactory stepFactory;
	@Autowired
	private EmployeeInfoItemProcessor processor;
	@Autowired
	private DataSource ds;

	@Bean(name = "reader")
	public FlatFileItemReader<Employee> createReader() {
		// create reader object
		FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();

		// set CSV file
		reader.setResource(new ClassPathResource("employee-info.csv"));
		// create LineMapper object (to read each line from csv file)
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
		// create LineTokenizer to get tokens from lines
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames("empno", "ename", "eadd", "salary");
		// create FieldSetMapper (to set the tokens to model object)
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Employee.class);

		// set Tokenizer ,fieldSetMapper object to LineMapper
		lineMapper.setFieldSetMapper(fieldSetMapper);
		lineMapper.setLineTokenizer(tokenizer);

		// set LineMapper to reader object
		reader.setLineMapper(lineMapper);

		return reader;

	}

	@Bean(name = "writer")
	public JdbcBatchItemWriter<Employee> createWriter() {
		// create JdbcBatchItemWriter object
		JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();

		// set datasource
		writer.setDataSource(ds);
		// set sql
		writer.setSql("INSERT INTO BATCH_EMPLOYEEINFO VALUES(:empno,:ename,:salary,:grossSalary,:netSalary");
		// create BeanPropertyItemSqlParameterSourceProvider object
		BeanPropertyItemSqlParameterSourceProvider<Employee> sourceProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		// set sourceprovider to writer
		writer.setItemSqlParameterSourceProvider(sourceProvider);

		return writer;
	}

	@Bean(name = "step1")
	public Step createStep1() {
		return stepFactory.get("step1").<Employee, Employee>chunk(3).reader(createReader()).processor(processor)
				.writer(createWriter()).build();
	}

	@Bean(name = "job")
	public Job createJob() {
		return jobFactory.get("job").listener(listener).incrementer(new RunIdIncrementer()).start(createStep1())
				.build();

	}
}
