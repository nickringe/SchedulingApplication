<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles.css">
		<meta charset="ISO-8859-1">
		<title>Employee Schedule</title>
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
			<h2>Schedule for ${employee.firstname} ${employee.lastname}</h2>
		</div>
		<div class="child">
			<a href="/add-shift?id=${employee.id }" class="btn btn-primary">Add Shift</a>
		</div> <br>

		<c:if test="${not empty employee.schedule}">
			<table class="table">
				<tr>
					<th>Date:</th>
					<th>Shift Name:</th>
					<th>Start Time:</th>
					<th>End Time:</th>
					<th>Shift Length (Hours):</th>
				</tr>

				<c:forEach var="shift" items="${employee.schedule}">
					<tr>
						<td>${shift.dateString}</td>
						<td>${shift.shiftName}</td>
						<td>${shift.startTimeString}</td>
						<td>${shift.endTimeString}</td>
						<td>${shift.shiftLength}</td>
						<td><a href="/remove?shiftId=${shift.id}&id=${employee.id}">Delete Shift</a></td>
					<!-- <td><button onclick="myFunction()" type="button" class="btn btn-danger">Delete Shift</button>
						<script>
							function myFunction() {
  								if (confirm("Are you sure you want to delete this shift?")) {
	 								location.href = "/remove?shiftId=${shift.id}&id=${employee.id}";
  									} 
								}
						</script>
						</td> -->
					</tr>
				</c:forEach>
			</table>
			
			<div class="text-center">
				<b>Total Hours:</b> ${totalHours}
			</div>
		</c:if>
	

		<c:if test="${empty employee.schedule}">There are no shifts to display!</c:if>
	</body>
</html>