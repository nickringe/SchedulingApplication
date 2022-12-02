<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles.css">
		<script src="https://kit.fontawesome.com/aa77e8e357.js"	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Weekly Schedule</title>
</head>
<body>
<header class="header">
	<h1>Scheduling App</h1> 
	
	</header>
	<br>
	<div class="text-center">
		<a href="/" class="btn btn-info">Home</a>
		<a href="" class="btn btn-primary active">View Schedule</a>	
		<a href="/create-shift" class="btn btn-info">Create New Shifts</a>
		<a href="/add-employee" class="btn btn-info">Add Employee</a>
	</div>

	<br>
	<h2 class="text-center">Weekly Schedule</h2>
	
	<!-- Calendar Grid -->
	
		<div class="month-navigation">

			<a id="prevButton" href="/weekly-calendar?date=${prevWeekDate}"><i
				class="fa-solid fa-arrow-left"></i></a>&nbsp;

			<!-- Gets current month and year as a String -->
			<div>
				Week of: ${curWeekMonthString} ${curWeekDate.dayOfMonth} ${curWeekDate.year}&nbsp;<br>
				<div class="child"><a href="/weekly-calendar?date=${displayToday}" title="Go to Today"><i class="fa-solid fa-calendar-check"></i></a></div>
			</div>

			<a id="nextButton" href="/weekly-calendar?date=${nextWeekDate}"><i
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
							<a href="/weekly-calendar?date=${date}">
								${date.dayOfMonth}
							</a>
							<br>
								<c:set var="dateString">${date.toString()}</c:set>
								<c:forEach var="shift" items="${shifts[dateString]}">								
									${shift.shiftName} ${shift.startTimeString} - ${shift.endTimeString}<br>
								</c:forEach>
							
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	

</body>
</html>