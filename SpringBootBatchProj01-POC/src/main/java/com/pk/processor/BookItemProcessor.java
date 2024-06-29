package com.pk.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("bookProcessor")
public class BookItemProcessor implements ItemProcessor<String, String> {

	public BookItemProcessor() {
		System.out.println("BookItemProcessor.BookItemProcessor()::=>0 param");
	}
	
	@Override
	public String process(String item) throws Exception {
		System.out.println("BookItemProcessor.process()");
		
		String bookWithAuthor=null;
		if(item.equalsIgnoreCase("Think in Java"))
			bookWithAuthor=item+" by Bruce Eckel";
		else if(item.equalsIgnoreCase("Effective Java"))
			bookWithAuthor=item+" by Joshua Bloch";
		else if(item.equalsIgnoreCase("BlackBook of Java"))
			bookWithAuthor=item+" by RNR";
		else if(item.equalsIgnoreCase("Head First Java"))
			bookWithAuthor=item+" by Kathy Sierra";
		else if(item.equalsIgnoreCase("Complete Reference of Java"))
			bookWithAuthor=item+" by ";
		else
			bookWithAuthor=null;
		return bookWithAuthor;
	}

}
