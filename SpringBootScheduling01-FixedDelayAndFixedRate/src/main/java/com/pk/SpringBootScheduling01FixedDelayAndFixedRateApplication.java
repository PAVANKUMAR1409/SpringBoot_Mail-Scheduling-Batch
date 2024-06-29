package com.pk;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootScheduling01FixedDelayAndFixedRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScheduling01FixedDelayAndFixedRateApplication.class, args);
		System.out.println("Start::=>"+new Date());
	}

}
