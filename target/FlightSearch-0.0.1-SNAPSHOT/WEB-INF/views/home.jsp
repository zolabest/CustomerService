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
<h1>Welcome Home!!!</h1>
<table border='1'>
		<tr>
		<th>ID</th>
			<th>User Name</th>
			<th>Full Name</th>
			<th>Email</th>
			<th>Role ID</th>
		</tr>
<c:forEach items="${userList}" var="user">
    <tr>
        <td>${user.id}</td>
    
    	<td>${user.username}</td>
     	<td>${user.fullname}</td>
      	<td>${user.email}</td>
       	<td>${user.roleId}</td>
   	</tr>
</c:forEach>
</table>
</body>
</html>