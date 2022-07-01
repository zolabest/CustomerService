<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head><script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	let engineers=null;
	const id = ${complaint.id};
	let pinCode = "${complaint.pinCode}";
	let status = "${complaint.status}";
	let assigneeId = ${complaint.assigneeId};
	let feedback = "${feedback}";
	
	 
	
	$().ready(()=>{
		console.log("ready:", pinCode);
		getAssigneeList(pinCode);
		
	});
	function pinCodeBlur(fld)
	{
		const pin = fld.value;
		assigneeId=0;
		getAssigneeList(pin)
	}
	function getAssigneeList(pin)
	{
		
		const url = "/engineers/"+pin;
		$.get(url, (resp)=>{
			console.log(resp.list);
			engineers=resp.list;
			//alert(engineers[0].username);
			buildAssigneeList();
		})
	}
	function buildAssigneeList ()
	{
		let c="";
		for (let i in engineers)
		{
			const row = engineers[i];
			console.log("row", row);
			const o = new Option (row.username, row.id);
			$(o).html(row.username);
			$("#assigneeId").append(o);
//			let o ="<option value='"+row.id+"'>"+row.username+"</option>";
//			c+=o;
		}
		if(assigneeId>0)
			{
			$("#assigneeId").val(assigneeId);
			}
		//console.log(c);
		//$("#assigneeId").html(c);	
	}
	function delComplaint ()
	{
		window.location.replace("/deleteComplaint/"+id);
	}
</script>
</head>
<body>
	<form:form id="complaintForm" name="complaintForm" method="POST"
		commandName="complaint" action="/saveComplaint" modelAttribute="complaint">
		<form:input type="hidden" id="id" path="id"></form:input>
		
				<form:input type="hidden" id="userId" path="userId"></form:input>
		
		<p>
			Name:<br />
			<form:input type="text" id="name" path="name"></form:input>
		</p>
		<p>
			Address:<br />
			<form:input type="text" id="address" path="address"></form:input>
		</p>
		<p>
			PIN:<br />
			<form:input type="text" id="pinCode" path="pinCode" onBlur="pinCodeBlur(this)"></form:input>
		</p>
		<p>
			typeOfProblem<br />
			<form:input type="text" id="typeOfProblem" path="typeOfProblem"></form:input>
		</p>
		<p>
		<form:select path='status' id='status'>
			<option>*** Select status: </option>
			<form:option value="RAISED">RAISED</form:option>
			<form:option value="WIP">WIP</form:option>
			<form:option value="ESCALATED">ESCALATED</form:option>
			<form:option value="RESOLVED">RESOLVED</form:option>
		</form:select>
		
		</p>
		<p>
		<form:select path='assigneeId' id='assigneeId'>
		<option value='0'>Select Engineer</option>
		</form:select>
		<p>
			<input type='submit' value='Save' />
			<input type='button' value='Delete' onClick="delComplaint()"/>
						<input type='button' value='Cancel' 
				onClick='window.location.replace("/dashboard")'/>
		</p>
	</form:form>
	<h1>Feedback:</h1>
		
			<p>${feedback }</p>
    	
	
</body>
</html>