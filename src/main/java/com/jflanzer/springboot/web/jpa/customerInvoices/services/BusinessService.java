package com.jflanzer.springboot.web.jpa.customerInvoices.services;

import java.text.NumberFormat;
import org.springframework.stereotype.Component;
import com.jflanzer.springboot.web.jpa.customerInvoices.entities.InvoiceLine;

@Component
public class BusinessService {
	
	private double lineSubtotal;
	private double InvSubtotal;
	private double tax;
	private double total;
	
	//method to calculate line subtotals
	public double CalcLineSubtotal(double price, int quantity) {
		lineSubtotal = 0;
		lineSubtotal = price * quantity;
		return lineSubtotal;
	}
	//method to calculate invoice subtotal
	public double CalcInvSubtotal(java.util.List<InvoiceLine> invoices) {
		InvSubtotal = 0;
		for(InvoiceLine invoice:invoices)
			InvSubtotal += invoice.getSubtotal();
		   	return InvSubtotal;
	}
	public double CalcTax(double InvoiceSubtotal) {
		tax = 0;
		tax = InvSubtotal * .06;
		return tax;
	}
	public double CalcTotal(double invoiceSubtotal, double tax) {
		total = 0;
		total = InvSubtotal + tax;
		return total;
	}
}
