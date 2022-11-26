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
		<a href="" class="btn btn-primary active">Home</a>
		<a href="/master" class="btn btn-info">View Open Shifts</a>	
		<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
	</div>

	<br>
	<h2 class="text-center">Employees</h2>
	<div class="child"><h3> ${empIdAlreadyExists}</h3></div>
	<div class="box">

		<table class="table">
			<tr>
				<th class="text-center">Name</th>
				<th class="text-center">Email</th>
				<th class="text-center">Employee ID</th>
				<th class="text-center">View Schedule</th>
				<th class="text-center">Update</th>
				<th class="text-center">Delete</th>

			</tr>

			<c:forEach var="emp" items="${employees}">
				<tr>
					<td class="text-center">${emp.firstname} ${emp.lastname}</td>
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
					<%-- <td class="text-center"><a href="/delete?id=${emp.id}"><i class="fa-solid fa-trash"></i></a></td> --%>
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
	</div>
	<h3 class="text-center">Add New Employee</h3>
	
	<div class="boxCenter">
	
		<form class="form" action="/add" method="post">
			<div class="boxCenter">
			<div class="bold">First Name: </div> <input type="text" placeholder="First Name"  name="firstname" required/> &nbsp;&nbsp;
			<div class="bold">Last Name: </div> <input type="text" placeholder="Last Name"  name="lastname" required/> &nbsp;&nbsp;
			<div class="bold">Email: </div><input type="text" placeholder="Email"  name="email" required/> &nbsp;&nbsp;
			<div class="bold">Employee ID: </div><input type="number" placeholder="Employee ID"  name="empId" required/>&nbsp;&nbsp;
			<input type="hidden" name="schedule" value=""/> <br><br>
			<div class="child"><input type="submit" class="btn-success" />	</div>
			</div>
		</form>
	</div>
	
	<br>
	
	
</body>
</html>