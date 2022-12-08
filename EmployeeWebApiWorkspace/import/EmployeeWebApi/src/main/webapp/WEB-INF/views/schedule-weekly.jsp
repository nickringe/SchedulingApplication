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
		<title>Employee Schedule - Weekly</title>
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
			<div class="text-center"><h3>${shiftRemoved}</h3></div>
			<h2><a href="/schedule?id=${prevId}&date=${curDayDate}"><i class="fas fa-angle-left"></i></a>Schedule for ${employee.firstname} ${employee.lastname} <a href="/schedule?id=${nextId}&date=${curDayDate}"><i class="fas fa-angle-right"></i></a></h2>
		</div>
		<div class="child">
			<a href="/schedule?id=${employee.id}&date=${curDayDate}" class="btn btn-info">List View</a> &nbsp;
			<a href="#0" class="btn btn-primary">Weekly View</a> &nbsp;
			<a href="/add-shift?id=${employee.id }" class="btn btn-success">Add Shift</a>
		</div> 
		<br><br>
		<div class ="child">Search by Date:</div>
		<div class ="child">
			<form action="/schedule-weekly" class="form-group">
				<input type="hidden" value="${employee.id }" name="id" id="id">
				<input type="date" name="date" id="date" required>
				<input type="submit" value="Search" class="btn btn-info active">
			</form>
		</div>
		
		<div class="month-navigation">

			<a id="prevButton" href="/schedule-weekly?id=${employee.id }&date=${prevWeekDate}"><i
				class="fa-solid fa-arrow-left"></i></a>&nbsp;

			<!-- Gets current month and year as a String -->
			<div>
				Week of: ${curWeekMonthString} ${curWeekDate.dayOfMonth} ${curWeekDate.year}&nbsp;<br>
				<div class="child"><a href="/schedule-weekly?id=${employee.id}" title="Go to Today"><i class="fa-solid fa-calendar-check"></i></a></div>
			</div>

			<a id="nextButton" href="/schedule-weekly?id=${employee.id }&date=${nextWeekDate}"><i
				class="fa-solid fa-arrow-right"></i></a>

		</div>
		
		<div class="child">
			<table class="table table-bordered">
				<!-- Table Header (row 6) -->
				<tr>
					<th style="width: 14%">Sunday</th>
					<th style="width: 14%">Monday</th>
					<th style="width: 14%">Tuesday</th>
					<th style="width: 14%">Wednesday</th>
					<th style="width: 14%">Thursday</th>
					<th style="width: 14%">Friday</th>
					<th style="width: 14%">Saturday</th>
				</tr>

				<!-- Week -->

				<tr style="background-color: #e6eeff" class="col">
					<c:forEach var="date" items="${dates}" begin="0" end="6"
						varStatus="loop">

						<td>
						
							${date.dayOfMonth}
							<br>
							
								<c:set var="dateString">${date.toString()}</c:set>
								<c:forEach var="shift" items="${shifts[dateString]}">
										<a href="/shift-details?id=${employee.id}&shiftId=${shift.id}">		
										<div class="child"> ${shift.startTimeString} - ${shift.endTimeString}<br></div>
										<div class="child"><div class="bold"> ${shift.shiftName} </div></div></a>
										<div class="child">${shift.shiftLength} hours</div>
								</c:forEach>
							
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
		
	

	</body>
</html>