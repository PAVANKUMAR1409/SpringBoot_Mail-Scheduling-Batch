package com.pk.report;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReportGenerateService {

	/*@Scheduled(cron="* 58 * * * *")
	public void generateReport() {
		System.out.println("Start the App::=>"+new Date());
	}*/

	/*@Scheduled(cron="0 40 19 * * 5#2")
	public void generateReport()
	{		
		System.out.println("Start the App::=>"+new Date());
	}*/

	@Scheduled(cron = "0 40 19 * * 5#2")
	public void generateReport() {
		System.out.println("Start the App::=>" + new Date());
	}
}
