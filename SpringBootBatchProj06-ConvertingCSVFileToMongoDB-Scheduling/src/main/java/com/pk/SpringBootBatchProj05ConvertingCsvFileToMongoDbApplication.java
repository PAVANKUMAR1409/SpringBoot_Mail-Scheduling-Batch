package com.pk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootBatchProj05ConvertingCsvFileToMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatchProj05ConvertingCsvFileToMongoDbApplication.class, args);
	}

}
