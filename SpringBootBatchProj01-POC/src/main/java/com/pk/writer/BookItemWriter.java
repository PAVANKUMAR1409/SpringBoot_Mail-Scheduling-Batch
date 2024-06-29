package com.pk.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component("bookWriter")
public class BookItemWriter implements ItemWriter<String> {

	public BookItemWriter() {
		System.out.println("BookItemWriter.BookItemWriter()::0 param");
	}
	@Override
	public void write(List<? extends String> chunk) throws Exception {
		System.out.println("BookItemWriter.write()");
		
		System.out.println("books are::"+chunk.toString());
		
	}

}
