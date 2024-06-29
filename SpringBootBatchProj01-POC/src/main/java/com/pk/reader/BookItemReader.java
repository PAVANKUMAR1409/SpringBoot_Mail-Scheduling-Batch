package com.pk.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;
@Component("bookReader")
public class BookItemReader implements ItemReader<String> {

	private String[] books=new String[] {"Think in Java","Effective Java","Head First Java","BlackBook of Java","Complete Reference of Java"};
	int count=0;
	
	public BookItemReader()
	{
		System.out.println("BookItemReader.BookItemReader()::0 param");
	}	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(count<books.length) {
			return books[count++];
		}
		return null;
	}

}
