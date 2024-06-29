package com.pk;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootScheduling02CornExpressionsApplication {

	public static void main(String[] args) {
		System.out.println("main start::=>"+new Date());
		SpringApplication.run(SpringBootScheduling02CornExpressionsApplication.class, args);
	}

}
