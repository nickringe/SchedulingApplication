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
			<title>Create Shifts</title>
		</head>
	<body>
	<header class="header">
	<h1>Scheduling App</h1> 
	
	</header>
	<br>
		
		<div class="text-center">
		<a href="/" class="btn btn-info">Home</a>
		<a href="/weekly-calendar" class="btn btn-info">View Schedule</a>	
		<a href="" class="btn btn-primary active">Create New Shifts</a>
		<a href="/add-employee" class="btn btn-info">Add Employee</a>
	</div>
	
	<div class="child">
		<h2>Create Shift</h2>
		
			
		
		</div>
		<div class="child"><h4>${shiftAdded }</h4></div>
		<div class="boxCenterThin">
			<br>
			<!-- <form class="form" action="/test" method="get"> -->
			<form class="form" action="/add-created-shift" method="post">
				<div class="bold">Shift Name:  </div><input type="text" name="shiftName" id="shiftName" placeholder="ex. Carpenter" required><br> <br>
				<div class="bold">Date: </div><input type="date" name="date" id="date" required> <br><br>
				<div class="bold">Start Time: </div><input type="time" name="startTime" id="startTime" required > <br><br>
				<div class="bold">End Time: </div><input type="time" name="endTime" id="endTime"  required > <br><br>
				<div class="bold">Assign to Employee (optional) </div>
		
				<select name="id">
					<option value="">Select an employee</option>
						<c:forEach var="employee" items="${sortedList}">
							<option value="${employee.id }" id="id">${employee.firstname} ${employee.lastname }</option>
						</c:forEach>
				</select>
				
				<br><br>

				<div class="child"><input type="submit" class="btn-success" value="Add Shift"></div>
			</form>
			
			<br>
		</div>
		<div class="child">*Unassigned shifts are added to the Master Schedule*</div>
	

	</body>
</html>