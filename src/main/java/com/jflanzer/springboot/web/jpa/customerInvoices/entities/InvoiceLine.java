package com.jflanzer.springboot.web.jpa.customerInvoices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class InvoiceLine {
	
	@Id
	@GeneratedValue
	private int id;
	private String description;
	private int quantity;
	private double price;
	private double subtotal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Customer customer;
	
	public InvoiceLine(){
		super();
	}
	
	public InvoiceLine(String description, int quantity, double price, double subtotal) {
		super();
		
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "InvoiceLine [id=" + id + ", description=" + description + ", quantity="
				+ quantity + ", price=" + price + ", subtotal=" + subtotal + "]";
	}

	public void setCustomer(Customer customer){
		
		this.customer = customer;
		
	}
	
	public Customer getCustomer(){
		
		return customer;
	}
	

}
