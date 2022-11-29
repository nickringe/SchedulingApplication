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
			<a href="/master" class="btn btn-info">View Open Shifts</a>	
			<a href="/create-shift" class="btn btn-info">Create New Shifts</a> <br><br>
			<div class="text-center"><h3>${shiftRemoved}</h3></div>
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
					<th>Delete</th>
				</tr>

				<c:forEach var="shift" items="${employee.schedule}">
					<tr>
						<td>${shift.dateString}</td>
						<td>${shift.shiftName}</td>
						<td>${shift.startTimeString}</td>
						<td>${shift.endTimeString}</td>
						<td>${shift.shiftLength}</td>
						<td>
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
						</td>
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