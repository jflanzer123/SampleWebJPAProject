<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Invoice Line</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">
</head>
<body>
<div class="container">
	<font color="red">${message}</font>
	<form method = "POST">
		Id			<input type="number" name="id" autofocus required><br>
		Description: <input type="text" name="description" required><br>
		Price:       <input type ="text" id = "price" name="price" required pattern="^\d+\.*\d*$" 
						title= "must be a number"><br>
		Quantity: 	<input type ="number" name="quantity" required><br>
		            <input type="submit" value="Submit"/>		
	</form>
</div>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>