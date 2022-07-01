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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
const user = ${user};
$(()=>{
	$("#statusList").val("${complaint.status}");
});
function statusListChange(lst)
{
	$("#status").val(lst.value);
	console.log("status change to ", lst.value);
}
</script>
</head>
<body>
	<form:form id="complaintForm" name="complaintForm" method="POST"
		commandName="complaint" action="/saveComplaint" modelAttribute="complaint">
		<form:input type="hidden" id="id" path="id"></form:input>
		<form:input type="hidden" id="assigneeId" path="assigneeId"></form:input>
				<form:input type="hidden" id="userId" path="userId"></form:input>
		<form:input type="hidden" id="status" path="status"></form:input>
		<p>
			Name:<br />
			<form:input type="text" id="name" path="name"></form:input>
		</p>
		<p>
			Address:<br />
			<form:input type="text" id="address" path="address"></form:input>
		</p>
		<p>
			Phone#:<br />
			<form:input type="text" id="telephone" path="telephone"></form:input>
		</p>
		<p>
			PIN#:<br />
			<form:input type="text" id="pinCode" path="pinCode"></form:input>
		</p>
		<p>
			typeOfProblem<br />
			<form:input type="text" id="typeOfProblem" path="typeOfProblem"></form:input>
		</p>
		<div id='divStatus'>
			<select id='statusList' onChange='statusListChange(this)'>
				<option>RAISIED</option>
				<option>WIP</option>
				<option>RESOLVED</option>
				<option>ESCALATED</option>
			</select>
		</div>
		<p>
			<input type='submit' value='Save' />
			<input type='button' value='Cancel' 
				onClick='window.location.replace("/dashboard")'/>
		</p>
	</form:form>
	<h1>Feedback: ${feedback }</h1>
</body>
</html>