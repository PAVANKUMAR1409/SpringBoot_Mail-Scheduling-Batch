package com.pk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.pk.service.IPurchaseOrder;
import com.pk.service.PurchaseOrderMgmtServiceImpl;

@SpringBootApplication
public class SpringBootMail01SendingMailApplication {

	public static void main(String[] args) {
		// get IOC Container
		ApplicationContext ctx = SpringApplication.run(SpringBootMail01SendingMailApplication.class, args);
		
		// get service class object reference
		IPurchaseOrder bean = ctx.getBean("po",PurchaseOrderMgmtServiceImpl.class);
		// invoke the bean
		try {
			String msg=bean.purchase(new String[] {"Shirt","Paint","Watch"},
															new Double[] {5000.0,6000.0,3200.0}, 
															"pavanelagandula@gmail.com", 
															new String[] {"pavankumar.e1425@gmail.com","jukanti13@gmail.com"});
			System.out.println(msg);
		}catch(Exception e) {e.printStackTrace();}
		
		// close the container
		((ConfigurableApplicationContext) ctx).close();
	}

}
