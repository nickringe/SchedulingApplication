<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Add a Shift</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles.css">
		<meta charset="ISO-8859-1">
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
			<div class="child"><a href="/schedule?id=${employee.id}" class="btn btn-primary">Back to ${employee.firstname}'s Schedule</a></div>
			<h2>Pick up a new shift for ${employee.firstname} ${employee.lastname}</h2>
		</div>
		
			<%-- 	Select a time range to see open shifts.<br>
					<form action="/search" method="get">
						<input type="hidden" name="id" id="id" value="${employee.id}">
						From:<input type="datetime-local" name="searchStart" id="searchStart" required><br>
						To: <input type="datetime-local" name="searchEnd" id="searchEnd" required><br>
						<input type="submit" value="Search">
					</form> --%>
		<br>	
		<h4>Open Shifts:</h4>
			<c:forEach var="master" items="${master }">
				<ul>
					<li class="btn btn-info">
						<form action="/add-to-schedule" method="post">
							${master.dateString}
							Position: ${master.shiftName }
							Time: ${master.startTimeString} - ${master.endTimeString}
							Length: ${master.shiftLength} hours
							<input type="hidden" name="id" id="id" value="${employee.id }">
							<input type="hidden" name="shiftId" id="shiftId" value="${master.id }">
							<input type="hidden" name="shiftName" id="shiftName" value="${master.shiftName }">
							<input type="hidden" name="date" id="date" value="${master.date }">
							<input type="hidden" name="startTime" id="startTime" value="${master.startTime }">
							<input type="hidden" name="endTime" id="endTime" value="${master.endTime }">
							<input type="submit" value="Add to Schedule">
						</form>
					</li>
				</ul>
			</c:forEach>
		

		<c:if test="${empty openShifts }"></c:if>
		<c:if test="${not empty openShifts }">
			<h3>Open Shifts between</h3>
			<h4>${searchStartDateString} ${searchStartString } to <br>
				${searchEndDateString} ${searchEndString }</h4>
				<c:forEach var="openShifts" items="${openShifts}">
					<ul>
						<li class="btn btn-info"><a href="">
							${openShifts.dateString }
							Position: ${openShifts.shiftName }
							Time: ${openShifts.startTimeString } - ${openShifts.endTimeString }
							</a>
						</li>
					</ul>
				</c:forEach> 
		</c:if> 

<br><br>
	<h4>Your current shifts:</h4><br>
	<c:if test="${empty employee.schedule}">There are no shifts to display! </c:if>

	<c:if test="${not empty employee.schedule}">
		<c:forEach var="shifts" items="${shifts}">
			<ul>

				<li class="btn btn-primary">
					${shifts.dateString }
					Position: ${shifts.shiftName }
					Time: ${shifts.startTimeString } - ${shifts.endTimeString }
				</li>
			</ul>
		</c:forEach>
	</c:if>
</body>
</html>