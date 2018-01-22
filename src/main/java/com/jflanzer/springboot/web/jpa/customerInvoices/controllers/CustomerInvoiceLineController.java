package com.jflanzer.springboot.web.jpa.customerInvoices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import com.jflanzer.springboot.web.jpa.customerInvoices.repository.CustomerRepository;
import com.jflanzer.springboot.web.jpa.customerInvoices.repository.InvoiceLineRepository;
import com.jflanzer.springboot.web.jpa.customerInvoices.entities.Customer;
import com.jflanzer.springboot.web.jpa.customerInvoices.entities.InvoiceLine;
import com.jflanzer.springboot.web.jpa.customerInvoices.services.BusinessService;
import com.jflanzer.springboot.web.jpa.customerInvoices.services.ValidateService;

import java.text.NumberFormat;
import java.util.List;

@SessionScope
@Controller
public class CustomerInvoiceLineController {
	
	@Autowired
	CustomerRepository customerRepository;
	//dependency injection for InvoiceLine's spring data management interface
	@Autowired
	InvoiceLineRepository invoiceLineRepository;
	
	@Autowired
	BusinessService service;
	
	@Autowired
	ValidateService validateService;
	
	//instantiate a NumberFormat instance for currency formating
		NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	//display page for looking up a customer by id
	@RequestMapping(value = "/customerLookup" ,method = RequestMethod.GET)
	public String firstPage(){
		
		return "customerLookup";
	}
	// input customer id and display name and invoice lines for that customer 
	@RequestMapping(value = "/customerLookup", method = RequestMethod.POST)
	public String customerLookup(@RequestParam int id,  ModelMap model){
		
		//call validate service to determine whether customer exists	
		if(validateService.customerExists(id)){
		
			//call validateService to get validated customer
			Customer cust = validateService.getValidatedCustomer(id);
			//get the customer's invoice lines and put them on the model
			List<InvoiceLine> invoice = cust.getInvoice();
			model.put("invoices", invoice);
			//get the customers first and last names and put them on the model
			String fName = cust.getFirstName();
			model.put("fName", fName);;
			String lName = cust.getLastName();
			model.put("lName",lName);
			//call business service to get invoice subtotal
			double InvSubtotal = service.CalcInvSubtotal(invoice);
			//format invoice subtotal
			String fInvSubtotal = currency.format(InvSubtotal);
			//put invoice subtotal on model
			model.put("InvSubtotal",fInvSubtotal);
			//call business service to get tax
			double tax = service.CalcTax(InvSubtotal);
			//format tax
			String fTax = currency.format(tax);
			//put tax on model
			model.put("tax",fTax);
			//call business service to get total
			double total = service.CalcTotal(InvSubtotal, tax);
			//format total
			String fTotal =  currency.format(total);
			//put total on model
			model.put("total",fTotal);
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
						
		//call validate service to determine whether customer exists
		if(validateService.customerExists(id)){
			
			//call service to get subtotal
			double subtotal = service.CalcLineSubtotal(price, quantity);
			//put subtotal in model
			model.put("subtotal",subtotal);
			//instantiate new invoiceLine
			InvoiceLine invoiceLine = new InvoiceLine(description,quantity,price,subtotal);
			//put subtotal in invoice line
			invoiceLine.setSubtotal(subtotal);
			//call validateService to get validated customer
			Customer cust = validateService.getValidatedCustomer(id);
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
			//declare InvoiceSubtotal variable
			double InvSubtotal;
			//call service to get InvoiceSubtotal
			InvSubtotal = service.CalcInvSubtotal(invoices);
			//format invoice subtotal
			String fInvSubtotal = currency.format(InvSubtotal);
			//put InvoiceSubtotal in model
			model.put("InvSubtotal",fInvSubtotal);
			//declare a tax variable
			double tax;
			//call service to get tax
			tax = service.CalcTax(InvSubtotal);
			//format tax
			String fTax = currency.format(tax);
			//put tax in model
			model.put("tax", fTax);
			//declare total variable
			double total;
			//call service to get total
			total = service.CalcTotal(InvSubtotal,tax);
			//format total
			String fTotal =  currency.format(total);
			//put total in model
			model.put("total",fTotal);
			//display the view of customers invoices
			return "invoices";
		}
		//if customer doesn't exist, display error message
		else 
			model.put("message", "Invalid Customer id");
			return "addInvoiceLine";
	}
	//handle link for displaying page for adding invoice lines
	@RequestMapping("/addInvoiceLine")
	public String showAddPage(){
		
		return "addInvoiceLine";
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
