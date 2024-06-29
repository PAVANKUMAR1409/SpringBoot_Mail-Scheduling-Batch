package com.pk.proccesor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.pk.model.ExamResult;

@Component
public class IExamResultsProcessor implements ItemProcessor<ExamResult, ExamResult> {

	
	@Override
	public ExamResult process(ExamResult item) throws Exception {
		if(item.getPercentage()>90) {
			return item;
		}
		return null;
	}

}
