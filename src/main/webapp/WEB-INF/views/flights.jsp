<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border='1'>
		<tr>
			<th>Depart Time</th>
			<th>Depart Date</th>
			<th>Source</th>
			<th>Destination</th>
		</tr>
<c:forEach items="${flights}" var="flight">
    <tr>
        <td><a href="/book/${flight.id}">${flight.departTime}</a></td>
     	<td>${flight.departDate}</td>
      	<td>${flight.source}</td>
       	<td>${flight.destination}</td>
   	</tr>
</c:forEach>
</body>
</html>