<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<script>
	const user = "${user}";
	const list=[];
	<c:forEach items="${complaints}" var="complaint">
		list.push ({id: ${complaint.id}, problem: "${complaint.typeOfProblem}"});
	</c:forEach>
</script>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
<h1>Admin Dashboard</h1>
<h1>Welcome ${user.username }</h1>
<p>What do you want to do?</p>
<ol>
<li><a href="/create">New Complaint</a></li>

<li><a href="/userComplaints">All Complaints</a></li>
<li><a href="/newUser">New User</a></li>
<li><a href="/users">User List</a></li>
<li><a href="/logout">Logout</a></li>
</ol>
<table border="1">
<tr><th>Status</th><th>Problem Type</th></tr>
<c:forEach items="${complaints}" var="complaint">
    <tr>
        <td><a href="/complaint/${complaint.id}">${complaint.typeOfProblem}</a></td>
     	
       	<td>${complaint.status}</td><td>${complaint.typeOfProblem}</td>
   	</tr>
</c:forEach>
</table>
</body>
</html>