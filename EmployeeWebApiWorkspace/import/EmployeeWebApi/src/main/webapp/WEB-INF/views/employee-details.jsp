<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Employee Details - ${employee.firstname} ${employee.lastname}</title>
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
			<a href="/employees" class="btn btn-info">Employees</a>
			<a href="/weekly-calendar" class="btn btn-info">View Schedule</a>
			<a href="/create-shift" class="btn btn-info">Create New Shifts</a>	
			<a href="/add-employee" class="btn btn-info">Add Employee</a>
		</div>
		
		<h2 class="text-center">Employee Details for ${employee.firstname} ${employee.lastname}</h2> 
		<h4 class="text-center"><a href="/form?id=${employee.id}"><i class="fa-solid fa-pencil"></i> Edit</a></h4>
		
		<div class="boxCenterText">
		<br>
		<div class="bold">First Name: </div>${employee.firstname}<br><br>
		<div class="bold">Last Name:  </div>${employee.lastname}<br><br>
		<div class="bold">Email: </div>${employee.email}<br><br>
		<div class="bold">Employee Id: </div>${employee.empId}<br><br>
		<div class="bold">Main Phone: </div>${employee.mainPhone}<br><br>
		<div class="bold">Other Phone: </div>${employee.otherPhone}<br><br>
		<div class="bold">Street Address: </div> ${employee.streetAddress}<br><br>
		<div class="bold">City: </div> ${employee.city}<br><br>
		<div class="bold">State: </div>${employee.state}<br><br>
		<div class="bold">Zip Code: </div>${employee.zipcode}<br><br>
		<div class="bold">Pay Rate: </div>${employee.payRate}<br><br>
		<div class="bold">Emergency Contact: </div>${employee.emergencyContact}<br><br>
		<div class="bold">Emergency Phone: </div>${employee.emergencyPhone}<br>
		</div>
	</body>
</html>