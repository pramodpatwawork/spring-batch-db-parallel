package com.mywork.pp.spring.batch.db.parallel;

import java.time.LocalDateTime;

public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private LocalDateTime birthdate;
	
	public Customer() {
		
	}
	public Customer(Long id, String firstName,String lastName,LocalDateTime birthdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDateTime getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDateTime birthdate) {
		this.birthdate = birthdate;
	}
	
	public String toString() {
		
		return id +": "+ firstName; 
	}
	
	
}
