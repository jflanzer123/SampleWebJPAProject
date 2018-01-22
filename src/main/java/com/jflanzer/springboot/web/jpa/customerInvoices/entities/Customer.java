package com.jflanzer.springboot.web.jpa.customerInvoices.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
		
	@Id
	@GeneratedValue
	Integer id;
	
	String firstName;
	String lastName;
	String email;
	String phone;
	
	@OneToMany(mappedBy = "customer")
	private List<InvoiceLine>invoice = new ArrayList<>();
	
	public Customer(){
		super();
	}
	
	public Customer(String firstName, String lastName, String email, String phone, List<InvoiceLine> invoice) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.invoice = invoice;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<InvoiceLine> getInvoice() {
		return invoice;
	}
	public void addInvoiceLine(InvoiceLine invoiceLine) {
		this.invoice.add(invoiceLine);
	}

	
	
	
	

}
