package com.mywork.pp.spring.batch.db.parallel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl {
	
	public List<Customer> getCustomers (){
		List<Customer> customers = new ArrayList<Customer>();
		
		customers.add(new Customer(1L,"ABC1","XYZ1",LocalDateTime.now()));
		customers.add(new Customer(2L,"ABC2","XYZ2",LocalDateTime.now()));
		customers.add(new Customer(3L,"ABC3","XYZ3",LocalDateTime.now()));
		customers.add(new Customer(4L,"ABC4","XYZ4",LocalDateTime.now()));
		customers.add(new Customer(5L,"ABC5","XYZ5",LocalDateTime.now()));
		customers.add(new Customer(6L,"ABC6","XYZ6",LocalDateTime.now()));
		customers.add(new Customer(7L,"ABC7","XYZ7",LocalDateTime.now()));
		customers.add(new Customer(8L,"ABC8","XYZ8",LocalDateTime.now()));
		customers.add(new Customer(9L,"ABC9","XYZ9",LocalDateTime.now()));
		customers.add(new Customer(10L,"ABC10","XYZ10",LocalDateTime.now()));
		customers.add(new Customer(11L,"ABC11","XYZ11",LocalDateTime.now()));
		customers.add(new Customer(12L,"ABC12","XYZ12",LocalDateTime.now()));
		customers.add(new Customer(13L,"ABC13","XYZ13",LocalDateTime.now()));
		customers.add(new Customer(14L,"ABC14","XYZ14",LocalDateTime.now()));
		customers.add(new Customer(15L,"ABC15","XYZ15",LocalDateTime.now()));
		customers.add(new Customer(16L,"ABC16","XYZ16",LocalDateTime.now()));
		customers.add(new Customer(17L,"ABC17","XYZ17",LocalDateTime.now()));
		
		System.out.println("Record to be processed"+ customers.size());
		
		return customers;
	}

}
