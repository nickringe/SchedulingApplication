<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
		<head>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
			<meta charset="ISO-8859-1">
			<title>Create Shifts</title>
		</head>
	<body>
		<div class="justify-content-center">
			<h4>${shiftAdded }</h4>

		</div>
		<h1>Create Shift</h1>
		<div class="justify-content-center">

			<form class="form" action="/add-created-shift" method="post">
				Shift Name:  <input type="text" name="shiftName" id="shiftName" placeholder="ex. Carpenter" required> <br>
				Date: <input type="datetime-local" name="date" id="date" required> <br>
				Start Time: <input type="datetime-local" name="startTime" id="startTime" required > <br>
				End Time: <input type="datetime-local" name="endTime" id="endTime"  required > <br>
				Assign to Employee (optional) <br>

					<c:forEach var="employee" items="${employees }">
						${employee.firstname} ${employee.lastname } <input type="checkbox" name="id" id="id" value="${employee.id }">  <br>
					</c:forEach>

				<input type="submit" class="btn-success" value="Add Shift">
			</form>
		</div>
	
<br><br>

		<form action="/" method="get">
			<input type="submit" class="btn-info" value="Back To Home">
		</form>
	</body>
</html>