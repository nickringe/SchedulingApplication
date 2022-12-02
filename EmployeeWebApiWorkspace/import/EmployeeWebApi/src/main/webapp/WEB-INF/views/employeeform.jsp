<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Update ${employee.firstname} ${employee.lastname}</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles.css">
		<script src="https://kit.fontawesome.com/aa77e8e357.js"	crossorigin="anonymous"></script>
	</head>
	<body>
		<header class="header">
			<h1>Scheduling App</h1> 
		</header>
	<br><br>
	<div class="text-center">
	<a href="/" class="btn btn-info">Home</a>
		<a href="/weekly-calendar" class="btn btn-info">View Schedule</a>	
		<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
		<a href="/add-employee" class="btn btn-info">Add Employee</a>	
	</div>
		<h2 class="text-center">Update ${employee.firstname} ${employee.lastname}</h2>

		<div class="box">
			<br>
			<form class="form" action="/postEmployee" method="post">
			<br>
				<input type="hidden" value="${employee.id}" name="id">
				<div class="bold">First Name: </div><input type="text" value="${employee.firstname}" name="firstname"><br><br>
				<div class="bold">Last Name: </div><input type="text" value="${employee.lastname}" name="lastname"><br><br>
				<div class="bold">Email Address:</div> <input type="text" value="${employee.email}" name="email"><br><br>
				<div class="bold">Employee Id:</div> <input type="text" value="${employee.empId}" name="empId"><br><br>
				<div class="bold">Main Phone:</div> <input type="text" value="${employee.mainPhone}" name="mainPhone"><br><br>
				<div class="bold">Other Phone:</div> <input type="text" value="${employee.otherPhone}" name="otherPhone"><br><br>
				<div class="bold">Street Address:</div> <input type="text" value="${employee.streetAddress}" name="streetAddress"><br><br>
				<div class="bold">City:</div> <input type="text" value="${employee.city}" name="city"><br><br>
				<div class="bold">State:</div> <input type="text" value="${employee.state}" name="state"><br><br>
				<div class="bold">Zip Code:</div> <input type="text" value="${employee.zipcode}" name="zipcode"><br><br>
				<div class="bold">Pay Rate:</div> <input type="text" value="${employee.payRate}" name="payRate"><br><br>
				<div class="bold">Emergency Contact:</div> <input type="text" value="${employee.emergencyContact}" name="emergencyContact"><br><br>
				<div class="bold">Emergency Phone:</div> <input type="text" value="${employee.emergencyPhone}" name="emergencyPhone"><br><br>
				
				
					<div class="child">
						<input type="submit" class="btn-success" value="Save Changes"> &nbsp;&nbsp;&nbsp;
						<button onclick="window.location.href='/'" type="button" class="btn-danger">Cancel</button>
					</div>
				<br>
			</form>
			<br>
		</div>
	</body>
</html>