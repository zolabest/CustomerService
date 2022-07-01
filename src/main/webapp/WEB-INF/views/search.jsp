<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search for flights</title>
</head>
<body>
<form method="post" action="/search">
	<p>Travel Date:<br/>
	<input type='date' name='travelDate'/>
	</p>
	<p>Source:<br/>
	<input type='text' name='source'/>
	</p>
		<p>Destination:<br/>
	<input type='text' name='destination'/>
	</p>
		<p>Number of Passengers:<br/>
	<input type='text' name='numPassengers'/>
	</p>
	<p>
		<input type="submit" value="Search"/>
	</p>
</form>
</body>
</html>