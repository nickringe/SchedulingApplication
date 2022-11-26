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
		<a href="/master" class="btn btn-info">View Open Shifts</a>	
		<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
		<a href="/" class="btn btn-primary active">Home</a>
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
				<div class="bold">Employee ID:</div> <input type="text" value="${employee.empId}" name="empId"><br><br>
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