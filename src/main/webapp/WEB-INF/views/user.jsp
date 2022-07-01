<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	let pins=[];
	<c:forEach items="${pins}" var="pin">
		pins.push(${pin});
	</c:forEach>
	
	const user = ${user};
	$().ready (()=>{
		if(pins.length==0)
			return;
		showPins();
	});
	function deleteUser ()
	{
		window.location.replace("/deleteUser/"+user.id);
	}
	function addPin ()
	{
		const pin = prompt ("Enter pin: ");
		if(pin)
		{
			$.get("/addPin/"+user.id+"/"+user.roleId+"/"+pin, 
				function(resp){
					alert(resp);
					pins.push (pin);
					showPins();
			});
		}
	}

	function showPins()
	{
		let ol="<ol>";
		for (let i in pins)
			{
			let li = "<li>"+pins[i]+"</li>";
			ol+=li;
			}
		$("#divPins").html(ol+"</ol>");
	}
</script>
</head>
<body>
	<form:form id="userForm" name="userForm" method="POST"
		commandName="user" action="/saveUser" modelAttribute="user">
		<form:input type="hidden" id="id" path="id"></form:input>
		
		<form:input type="hidden" id="status" path="status"></form:input>
		<p>
			Username:<br />
			<form:input type="text" id="username" path="username"></form:input>
		</p>
		
				<p>
			Password:<br />
			<form:input type="password" id="password" path="password"></form:input>
		</p>
		<p>
			<form:select path="roleId" id="roleId">
				<form:option value="1">Admin</form:option>
				<form:option value="2">Customer</form:option>
				<form:option value="3">Manager</form:option>
				<form:option value="4">Engineer</form:option>
			</form:select>
		</p>
		
		<div id='divPins'>
			
		</div>
		<p>
			<input type='submit' value='Save' />
			<input type='button' value='Delete' id='deleteButton' onClick='deleteUser()'/>
			<input type='button' value='Add Pin' id='addPinButton' onClick='addPin()'/>
			<input type='button' value='Cancel' 
				onClick='window.location.replace("/dashboard")'/>
		</p>
	</form:form>
</body>
</html>