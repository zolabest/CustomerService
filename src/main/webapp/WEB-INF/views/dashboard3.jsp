<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<script>
	const list=[];
	<c:forEach items="${complaints}" var="complaint">
	list.push ({id: ${complaint.id}, problem: "${complaint.typeOfProblem}"});
	</c:forEach>
</script>
<meta charset="UTF-8">
<title>Manager Dashboard</title>
</head>
<body>
<h1>Welcome ${user.username }</h1>
<p>What do you want to do?</p>
<ol>
<li><a href="/create">New Complaint</a></li>

<li><a href="/userComplaints">My Complaints</a></li>
<li><a href="/logout">Logout</a></li>
</ol>
<table border="1">
<tr><th>Problem Type</th><th>Status</th></tr>
<c:forEach items="${complaints}" var="complaint">
    <tr>
        <td><a href="/complaint/${complaint.id}">View/Edit</a></td>
     	
       	<td>${complaint.status}</td><td>${complaint.typeOfProblem}</td>
   	</tr>
</c:forEach>
</table>
</body>
</html>