package com.jflanzer.springboot.web.jpa.customerInvoices.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import com.jflanzer.springboot.web.jpa.customerInvoices.Repository.CustomerRepository;
import com.jflanzer.springboot.web.jpa.customerInvoices.Repository.InvoiceLineRepository;
import com.jflanzer.springboot.web.jpa.customerInvoices.entities.Customer;
import com.jflanzer.springboot.web.jpa.customerInvoices.entities.InvoiceLine;

import java.util.List;

@SessionScope
@Controller
public class CustomerInvoiceLineController {
	
	//dependency injection for Customer's spring data management interface
	@Autowired
	CustomerRepository customerRepository;
	
	//depency injection for InvoiceLine's spring data management interface
	@Autowired
	InvoiceLineRepository invoiceLineRepository;
	
	
	//display page for looking up a customer by id
	@RequestMapping(value = "/customerLookup" ,method = RequestMethod.GET)
	public String firstPage(){
		
		return "customerLookup";
	}
	

	// input customer id and display name and invoice lines for that customer 
	@RequestMapping(value = "/customerLookup", method = RequestMethod.POST)
	public String customerLookup(@RequestParam int id,  ModelMap model){ 
		
		////identify customer by id from database
		Optional<Customer> customer = customerRepository.findById(id);
		//validate if customer exists, if customer does exist
		if (customer.isPresent()){
		//get customer from the Optional
		Customer cust = customer.get();
		//get the customer's invoice lines and put them on the model
		List<InvoiceLine> invoice = cust.getInvoice();
		model.put("invoices", invoice);
		//get the customers first and last names and put them on the model
		String fName = cust.getFirstName();
		model.put("fName", fName);;
		String lName = cust.getLastName();
		model.put("lName",lName);
		//display the customers invoice lines
		return "invoices";
		}
		//if customer doesn't exist, display error message
		else
			model.put("message", "Invalid Customer id");
			return "customerLookup";
	}
	
	//input customer id and new invoice line parameters
	@RequestMapping(value="/addInvoiceLine", method= RequestMethod.POST)
	public String addInvoiceLineForCustomer(@RequestParam int id,@RequestParam String description,@RequestParam int quantity,
			@RequestParam double price,	ModelMap model){
		
			
		
		
		Optional<Customer> customer = customerRepository.findById(id);
		//validate if customer exists
		if(customer.isPresent()){
		//if customer exists, compute subtotal
		double subtotal = quantity*price;
		//instantiate new invoiceLine
		InvoiceLine invoiceLine = new InvoiceLine(description,2,price,subtotal);
		//get the customer from the Optional
		Customer cust = customer.get();
		//get first name from customer instance and place in model
		String fName = cust.getFirstName();
		model.put("fName", fName);;
		//get last name from customer instance and place in model
		String lName = cust.getLastName();
		model.put("lName",lName);
		//add the new invoice line to the customer instance
		cust.addInvoiceLine(invoiceLine);
		//associate the customer with the new invoice line
		invoiceLine.setCustomer(cust);
		//save the new invoice line to the database
		invoiceLineRepository.save(invoiceLine);
		//get the customers invoice lines and put them on the model
		java.util.List<InvoiceLine> invoices= cust.getInvoice();
		model.put("invoices",invoices);
		//display the view of customers invoices
		return "invoices";
		}
		//if customer doesn't exist, display error message
		else 
			
			model.put("message", "Invalid Customer id");
			return "addInvoiceLine";
	}
	
	//handel link for displaying page for adding invoice lines
	@RequestMapping("/addInvoiceLine")
	public String showAddPage(){
		
		return "addInvoiceLine";
		
		
	}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
