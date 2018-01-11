+<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invoices</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">
</head>
<body>
<div class = "container">

 <h1>Invoice Lines for ${fName} ${lName}</h1>
	<table class = "table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${invoices}" var="invoice">
			
				<tr>
					<td>${invoice.id}</td>
					<td>${invoice.description}</td>
					<td>${invoice.price}</td>
					<td>${invoice.quantity}</td>
					<td>${invoice.subtotal}</td>
				</tr>
			</c:forEach>
		</tbody>
		
		
		
	</table>
	<a href = "addInvoiceLine">Add an Invoice Line</a>
	
	
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
			
</div>
</body>
</html>