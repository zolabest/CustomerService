<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form id="userForm" name="userForm" method="POST"
		commandName="user" action="/save" modelAttribute="user">
		<form:input type="hidden" id="id" path="id"></form:input>
		<form:input type="hidden" id="roleId" path="roleId"></form:input>
		<form:input type="hidden" id="status" path="status"></form:input>
		<p>
			Username:<br />
			<form:input type="text" id="username" path="username"></form:input>
		</p>
		<p>
			Full Name:<br />
			<form:input type="text" id="fullname" path="fullname"></form:input>
		</p>
		<p>
			Email:<br />
			<form:input type="text" id="email" path="email"></form:input>
		</p>
		<p>
			Phone#<br />
			<form:input type="text" id="phone" path="phone"></form:input>
		</p>
		<p>
			<input type='submit' value='Save' />
		</p>
	</form:form>
</body>
</html>