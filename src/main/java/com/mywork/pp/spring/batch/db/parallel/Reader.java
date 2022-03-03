package com.mywork.pp.spring.batch.db.parallel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;

public class Reader extends JdbcCursorItemReader<Customer> {
		
	private DataSource dataSource;
	
	private JdbcCursorItemReader<Customer> reader;
	
	 private List<Customer> customers;
	 
	 //private int nextIndex;
	
	public Reader(DataSource dataSource) {
		this.dataSource = dataSource; 
		cursorItemReader();
		 // initialize();
	}	
	
	
		
	public synchronized void cursorItemReader(){
				
		this.setSql("SELECT id, firstName, lastName, birthdate FROM customer ORDER BY lastName, firstName");
		this.setDataSource(dataSource);
		this.setFetchSize(100);
		this.setRowMapper(new CustomerRowMapper());
		//this.setVerifyCursorPosition(false);
		
	}
	
	@Override
    public  synchronized Customer read() throws Exception {
		return super.read();
	}
	
}
	