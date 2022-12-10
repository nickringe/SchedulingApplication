<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Edit Shift</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles.css">
	</head>
	<body>
		<header class="header">
			<h1>Scheduling App</h1> 
		</header>
	<br>
		<div class="text-center">
			<a href="/" class="btn btn-info">Home</a>
			<a href="/weekly-calendar" class="btn btn-info">View Schedule</a>	
			<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
			<a href="/add-employee" class="btn btn-info">Add Employee</a> <br><br>
			
			<h2>Edit ${employee.firstname }'s ${shift.shiftName } Shift</h2>
		</div>
		
		<div class="box">
		<div class="child">
			<form class="form" action="/postShift" method="post">
			<br>
				<input type="hidden" value="${employee.id }" name="id">
				<input type="hidden" value="${shift.id }" name="shiftId">
				<div class="bold">Shift Name: </div><input type="text" value="${shift.shiftName}" name="shiftName"><br><br>
				<div class="bold">Date: </div><input type="date" value="${shift.date}" name="date"><br><br>
				<div class="bold">Start Time: </div><input type="time" value="${shift.startTime}" name="startTime"><br><br>
				<div class="bold">Shift Name: </div><input type="time" value="${shift.endTime}" name="endTime"><br><br>
				
				<div class="boxCenterText">
						<input type="submit" class="btn-success" value="Save Changes"> &nbsp;&nbsp;&nbsp;
						<button onclick="window.location.href='/shift-details?id=${employee.id}&shiftId=${shift.id}'" type="button" class="btn-danger">Cancel</button>
					</div>
				
			</form>
		</div>
		</div>
	</body>
</html>