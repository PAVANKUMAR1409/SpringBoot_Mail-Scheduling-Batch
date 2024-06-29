package com.pk.report;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("report")
public class ReportGenerator {

	/*
	 * @Scheduled(initialDelay =2000,fixedDelay = 3000) public
	 * voidgenerateSaleReport() { System.out.println("Sales Report on::=>"+new
	 * Date()); }
	 */

	/*
	 * @Scheduled(fixedDelay = 3000) public void generateSaleReport() {
	 * System.out.println("Sales Report on::=>"+new Date()); }
	 */

	/*
	 * BeanCreationException and illegaluStateException
	 * 
	 * @Scheduled(fixedDelay = 3000) // if we don't write any parameters
	 * in @Scheduling annotation then we BeanCreationException public void
	 * generateSaleReport(int start, int end) {
	 * System.out.println("Sales Report on::=>"+new Date()); }
	 */

	// ================FIXED DELAY=======================

	/*@Scheduled(fixedDelay = 3000)     //this method output will get for every 18 seconds (15 for sleep and 3 for fixed delay)
	public void generateSaleReport() {
		try{
			Thread.sleep(15000);
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("Sales Report on::=>" + new Date());
	}*/
	
	/*@Scheduled(fixedDelay = 3000)    //this method output will get for every 4 seconds (1 for sleep and 3 for fixed delay)
	public void generateSaleReport() {
		try{
			Thread.sleep(1000);
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("Sales Report on::=>" + new Date());
	}*/
	
	//=======================FIXED RATE=========================================
	
	/*@Scheduled(fixedRate  = 3000)    //this method output will get for every 3 seconds ( 3 for fixed Rate)
	public void generateSaleReport() {
		try{
			Thread.sleep(1000);
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("Sales Report on::=>" + new Date());
	}*/
	
	
	@Scheduled(fixedRate  = 3000)    //this method output will get for every 10 seconds ( 10 for sleep and not waiting time for fixed rate)
	public void generateSaleReport() {
		try{
			Thread.sleep(10000);
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("Sales Report on::=>" + new Date());
	}
}
