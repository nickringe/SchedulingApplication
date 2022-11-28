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
			<a href="/" class="btn btn-info">Home</a>
			<a href="/master" class="btn btn-info">View Open Shifts</a>	
			<a href="/create-shift" class="btn btn-info">Create New Shifts</a>	
		</div>
		
		<h2 class="text-center">Employee Details for ${employee.firstname} ${employee.lastname}</h2> 
		<h4 class="text-center"><a href="/form?id=${employee.id}"><i class="fa-solid fa-pencil"></i> Edit</a></h4>
		
		<div class="boxCenterText">
		<br>
		<div class="bold">First Name: </div>${employee.firstname}<br><br>
		<div class="bold">Last Name:  </div>${employee.lastname}<br><br>
		<div class="bold">Employee Id: </div>${employee.empId}<br><br>
		<div class="bold">Email: </div>${employee.email}<br><br>
		<div class="bold">Main Phone: </div><br><br>
		<div class="bold">Other Phone: </div><br><br>
		<div class="bold">Street Address: </div><br><br>
		<div class="bold">City: </div><br><br>
		<div class="bold">State: </div><br><br>
		<div class="bold">Zip Code: </div><br><br>
		<div class="bold">Pay Rate: </div><br>
		</div>
	</body>
</html>