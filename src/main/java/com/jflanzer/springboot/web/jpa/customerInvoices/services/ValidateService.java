package com.jflanzer.springboot.web.jpa.customerInvoices.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jflanzer.springboot.web.jpa.customerInvoices.Repository.CustomerRepository;
import com.jflanzer.springboot.web.jpa.customerInvoices.entities.Customer;

@Component
public class ValidateService {
	
	//dependency injection for Customer's spring data management interface
	@Autowired
	CustomerRepository customerRepository;

	//method validates whether customer exists
	public boolean customerExists(int id) {
		
		//identify customer by id from database
		Optional<Customer> customer = customerRepository.findById(id);
		//validate if customer exists, if customer does exist
		boolean exists;
			if (customer.isPresent())
				exists = true;
			  	else exists = false;
			return exists;
	}
	//method gets validated customer from database
	public Customer getValidatedCustomer(int id) {
		
		Optional<Customer> customer = customerRepository.findById(id);
		//get customer from the Optional
		Customer cust = customer.get();
		return cust;
	}

}