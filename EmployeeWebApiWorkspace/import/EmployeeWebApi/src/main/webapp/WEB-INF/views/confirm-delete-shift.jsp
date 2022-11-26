<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles.css">
		<title>Confirm Delete Shift</title>
	</head>
	<body>
		<header class="header">
			<h1>Scheduling App</h1> 
		</header>
	<br>
		<div class="text-center">
			<a href="/" class="btn btn-info">Home</a>
			<a href="" class="btn btn-info">View Open Shifts</a>	
			<a href="/create-shift" class="btn btn-info">Create New Shifts</a> <br><br>
			<h2>Are you sure you want to delete ${firstname}'s [${shiftname}] shift?</h2>
		</div>
		
		<div class="child"><h3>Shift Details:</h3></div>
		<div class="boxCenterText">
			<br>
			<div class="bold">Shift Name:</div> ${shiftname} <br><br>
			<div class="bold">Date:</div> ${date} <br><br>
			<div class="bold">Start Time: </div> ${startTime}<br><br>
			<div class="bold">End Time: </div> ${endTime}<br><br>
			<div class="bold">Total Hours: </div> ${shiftLength} <br><br>
		</div>
		
		<div class="child">
			<a href="/remove?shiftId=${shiftId}&id=${id}" class="btn btn-danger">Yes, Delete</a> &nbsp;&nbsp;
			<a href="/schedule?id=${id}" class="btn btn-primary active">Cancel</a>
		</div>

	</body>
</html>