package com.mywork.pp.spring.batch.db.parallel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {
	private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		//@// @formatter:off
		
		Customer customer = new Customer();
		customer.setId(rs.getLong("id"));
		customer.setFirstName(rs.getString("firstName"));
		customer.setLastName(rs.getString("lastName"));
		customer.setBirthdate(LocalDateTime.parse(rs.getString("birthdate"), DT_FORMAT));
				
		
		return  customer;
		// @formatter:on
	}
}