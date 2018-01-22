package com.jflanzer.springboot.web.jpa.customerInvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jflanzer.springboot.web.jpa.customerInvoices.entities.InvoiceLine;

//InvoiceLine interface for spring data jpa entity management
public interface InvoiceLineRepository extends JpaRepository<InvoiceLine,
	Integer>{

}
