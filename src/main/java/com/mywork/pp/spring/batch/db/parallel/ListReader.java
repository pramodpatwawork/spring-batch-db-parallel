package com.mywork.pp.spring.batch.db.parallel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ListReader implements ItemReader<Customer> {

	//private int count = 0;
	 private AtomicInteger atomicCount = new AtomicInteger(0);

	List<Customer> customers;
	Map<Long,String> status;

	public ListReader(List<Customer> customers, Map<Long,String> status) {
		this.customers = customers;
		this.status = status;
	}

	@Override
	public synchronized Customer read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Customer customer = null;
		int count = atomicCount.getAndIncrement(); 
		if (count < customers.size()) {			
			customer = customers.get(count);	
			status.put(customer.getId(), "STARTED");
			return customer;
		} 
		return null;
	}
}