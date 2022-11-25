<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<title>Home - Scheduling</title>
</head>
<body>

<h1 class="text-center">Scheduling App</h1>
<c:if test="${not empty empIdAlreadyExists }">Employee ID already exists</c:if>
	<table class="table">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Employee ID</th>
			<th>View Schedule</th>
			<th></th>
			<th></th>

		</tr>

		<c:forEach var="emp" items="${employees}">
			<tr>
				<td>${emp.firstname} ${emp.lastname}</td>
				<td>${emp.email}</td>
				<td>${emp.empId}</td>
				<td><a href="/schedule?id=${emp.id}">View Schedule</a></td>
				<td><a href="/form?id=${emp.id}">Update Employee Info</a></td>
				<td><a href="/delete?id=${emp.id}">Delete Employee</a></td>
				<!-- <td><button onclick="myFunction()" type="button" class="btn-danger">Delete</button>
				<script>
					function myFunction() {
  						if (confirm("Are you sure you want to delete this employee?")) {
	 						location.href = "/delete?id=${emp.id}";
  							} 
						}
				</script>
				</td> -->
				
			</tr>
		
		</c:forEach>

	</table>
	<h3 class="text-center">Add an Employee</h3>
	<div class="container">
	
	<form class="form" action="/add" method="post">
	First Name: <input type="text" placeholder="First Name"  name="firstname" required/>
	Last Name: <input type="text" placeholder="Last Name"  name="lastname" required/>
	Email: <input type="text" placeholder="Email"  name="email" required/>
	Employee ID: <input type="number" placeholder="Employee ID"  name="empId" required/>
	<input type="hidden" name="schedule" value=""/> 
	<input type="submit" class="btn-success" />	
	</form>
	</div>
	

	<a href="/master" class="btn btn-info">View Open Shifts</a>	
	<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
</body>
</html>