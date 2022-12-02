<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Shift Details</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles.css">
		<script src="https://kit.fontawesome.com/aa77e8e357.js"	crossorigin="anonymous"></script>
	</head>
	<body>
		<header class="header">
			<h1>Scheduling App</h1> 
		</header>
	<br>
		<div class="text-center">
			<a href="/" class="btn btn-info">Home</a>
			<a href="/weekly-calendar" class="btn btn-info">View Schedule</a>	
			<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
			<a href="/add-employee" class="btn btn-info">Add Employee</a> <br><br>
			
			<h2>Shift Details</h2>
		</div>
		
		<div class="boxCenterText"><br>
			<div class="bold">Shift Name:</div>${shift.shiftName} <br><br>
			<div class="bold">Date:</div>${shift.dateString} <br><br>
			<div class="bold">Shift Time:</div>${shift.startTimeString} - ${shift.endTimeString} <br><br>
			<div class="bold">Shift Length:</div>${shift.shiftLength} hours<br><br>
				<form action="/confirm-delete-shift">
					<input type="hidden" value="${employee.id }" name="id" id="id">
					<input type="hidden" value="${shift.id }" name="shiftId" id="shiftId">
					<input type="hidden" value="${shift.shiftName }" name="shiftName" id="shiftName">
					<input type="hidden" value="${shift.dateString }" name="date" id="date">
					<input type="hidden" value="${shift.startTimeString }" name="startTime" id="startTime">
					<input type="hidden" value="${shift.endTimeString }" name="endTime" id="endTime">
					<input type="hidden" value="${shift.shiftLength }" name="shiftLength" id="shiftLength">
					<input type="submit" value="Delete" class="btn btn-danger"> 
				</form>
				<br>
				<a href="/schedule-weekly?id=${employee.id }" class="btn btn-primary active">Go Back</a>
				<br>
				<br>
							
		</div>
		

	</body>
</html>