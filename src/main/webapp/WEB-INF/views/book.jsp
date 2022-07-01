<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search for flights</title>
</head>
<body>
<form method="post" action="/book">

	<p>Flight Number:<br/>
	<input type='text' name='flightId' readonly='true' value="${flightId }"/>
	</p>
	<p>Last Name: <br/>
	<input type='text' name='lastName'/>
	</p>
		<p>First Name: <br/>
	<input type='text' name='firstName'/>
	</p>
		<p>Phone #<br/>
	<input type='text' name='phoneNumber'/>
	</p>
		<p>Email:<br/>
	<input type='text' name='email'/>
	</p>
		<p>Zip Code:<br/>
	<input type='text' name='zipCode'/>
	
	</p>
	<hr/>
			<p>Credit Card#<br/>
	<input type='text' name='creditCard'/>
	
	</p>
			<p>Expriation Date:<br/>
	<input type='text' name='expDate'/>
	
	</p>
			<p>Security Code:<br/>
	<input type='text' name='secCode'/>
	
	</p>
	<p>
		<input type="submit" value="Search"/>
	</p>
	
	
</form>
</body>
</html>