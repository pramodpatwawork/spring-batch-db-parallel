package com.mywork.pp.spring.batch.db.parallel;

import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionListener extends JobExecutionListenerSupport {
	
	Map<Long,String> status;

	public JobCompletionListener(Map<Long,String> status) {		
		this.status = status;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
			System.out.println("Record processed "+ status.size());
		}
	}

}