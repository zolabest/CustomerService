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
<p><a href="/newUser">[Add User]</a> | <a href='dashboard'>[Dashboard]</a>

</p>
<table border='1'>
		<tr>
			<th>User Name</th>
			
			<th>Role ID</th>
		</tr>
<c:forEach items="${userList}" var="user">
    <tr>
        <td><a href="/user/${user.id}">${user.username}</a></td>
     	
       	<td>${user.roleId}</td>
   	</tr>
</c:forEach>
</table>
</body>
</html>