package com.mywork.pp.spring.batch.db.parallel;

import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemWriter;

public class ListWriter implements ItemWriter<Customer> {
	
	Map<Long,String> status;

	public ListWriter(Map<Long,String> status) {		
		this.status = status;
	}


	@Override
	public void write(List<? extends Customer> customers) throws Exception {
		for (Customer customer : customers) {
			System.out.println("Writing the data " + customer.toString());
			status.put(customer.getId(), "FINISHED");
		}
	}

}