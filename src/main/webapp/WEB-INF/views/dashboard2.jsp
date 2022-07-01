<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Dashboard</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
	const list=[];
	<c:forEach items="${complaints}" var="complaint">
	list.push ({id: ${complaint.id}, problem: "${complaint.typeOfProblem}"});
	</c:forEach>
	
	function feedback(id)
	{
		const msg = prompt ("Feedback");
		$.post("/feedback",{complaintId:id, feedback:msg}, (resp)=>{
			alert(resp);	
		})
	}
</script>

</head>
<body>
<h1>Customer Dashboard</h1>
<h1>Welcome ${user.username }</h1>
<p>What do you want to do?</p>
<ol>
<li><a href="/create">New Complaint</a></li>

<li><a href="/userComplaints">My Complaints</a></li>
<li><a href="/logout">Logout</a></li>
</ol>
<table border="1">
<tr><th>Status</th><th>Problem Type</th></tr>
<c:forEach items="${complaints}" var="complaint">
    <tr>
        <td><a href="/complaint/${complaint.id}">${complaint.typeOfProblem}</a></td>
     	
       	<td>${complaint.status}</td><td>${complaint.typeOfProblem}</td>
       	<c:if test="${complaint.status == 'RESOLVED'}">
		<td><button onClick='feedback(${complaint.id})'>Feedback</button>
		</c:if>
   	</tr>
</c:forEach>
</table>
</body>
</html>