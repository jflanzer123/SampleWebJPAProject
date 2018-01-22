package com.jflanzer.springboot.web.jpa.customerInvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jflanzer.springboot.web.jpa.customerInvoices.entities.Customer;

//Customer interface for spring data jpa entity management
public interface CustomerRepository extends JpaRepository<Customer,
	Integer>{
	
	

}
