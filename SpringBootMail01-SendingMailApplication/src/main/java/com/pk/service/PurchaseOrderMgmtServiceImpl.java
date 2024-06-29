package com.pk.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service("po")
public class PurchaseOrderMgmtServiceImpl implements IPurchaseOrder {
	
	@Autowired
	private JavaMailSender sender;
	

	@Override
	public String purchase(String[] orders, Double[] prices, String fromMail, String[] toMails) throws Exception {
		Double billAmt=0.0;
		for(Double p:prices) 
			billAmt+=p;
		String msg=Arrays.toString(orders)+" with Prices "+Arrays.toString(prices)+" are purchased with billAmount "+billAmt;
		
		//send mail
		String status=sendMail(msg,fromMail,toMails);
		return msg+" ===>"+status;
	}


	private String sendMail(String msg, String fromMail, String[] toMails) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setFrom(fromMail);
		helper.setCc(toMails);
		helper.setSubject("Open it to know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("pavan.jpg",new ClassPathResource("pavan.jpg"));
		sender.send(message);
		return "Mail Sent";
	}

}
