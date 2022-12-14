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
		<script src="https://kit.fontawesome.com/aa77e8e357.js"	crossorigin="anonymous"></script>
		<title>Employees</title>
	</head>
<body>

	<header class="header">
	<h1>Scheduling App</h1> 
	
	</header>
	<br>
	<div class="text-center">
		<a href="" class="btn btn-primary active">Employees</a>
		<a href="/weekly-calendar" class="btn btn-info">View Schedule</a>	
		<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
		<a href="/add-employee" class="btn btn-info">Add Employee</a>
	</div>

	<br>
	<h2 class="text-center">Employees</h2>

	<div class="box">

		<table class="table">
			<tr>
				<th class="calCenter">Name</th>
				<th class="calCenter">Email</th>
				<th class="calCenter">Employee ID</th>
				<th class="calCenter">View Schedule</th>
				<th class="calCenter">Update</th>
				<th class="calCenter">Delete</th>

			</tr>

			<c:forEach var="emp" items="${sortedList}">
				<tr>
					<td class="text-center"><a href="/employee-details?id=${emp.id}">${emp.firstname} ${emp.lastname}</a></td>
					<td class="text-center">${emp.email}</td>
					<td class="text-center">${emp.empId}</td>
					<td class="text-center"><a href="/schedule?id=${emp.id}"><i class="fa-solid fa-eye"></i></a></td>
					<td class="text-center"><a href="/form?id=${emp.id}"><i class="fa-solid fa-pencil"></i></a> </td>
					<td class="text-center">
						<form action="confirm-delete">
							<input type="hidden" value="${emp.id }" name="id" id="id">
							<input type="submit" value="Delete" class="btn btn-danger">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
	
</body>
</html>