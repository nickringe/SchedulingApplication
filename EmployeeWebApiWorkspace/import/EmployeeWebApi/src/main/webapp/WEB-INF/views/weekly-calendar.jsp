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
		<h2 class="text-center">Weekly Schedule - Calendar</h2>
		<div class="child">
			<a href="/weekly-calendar-list" class="btn btn-info">List View</a> &nbsp;
			<a href="#0" class="btn btn-primary active">Weekly View</a> &nbsp;
			<a href="/create-shift" class="btn btn-success">Add Shift</a>
		</div> 
		<br><br>
		<div class ="child">Search by Date:</div>
		<div class ="child">
			<form action="" class="form-group">
				<input type="hidden" value="${employee.id }" name="id" id="id">
				<input type="date" name="date" id="date" required>
				<input type="submit" value="Search" class="btn btn-info active">
			</form>
		</div>
		
	
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
					<th style="width: 14%" class="cal">Sunday</th>
					<th style="width: 14%" class="cal">Monday</th>
					<th style="width: 14%" class="cal">Tuesday</th>
					<th style="width: 14%" class="cal">Wednesday</th>
					<th style="width: 14%" class="cal">Thursday</th>
					<th style="width: 14%" class="cal">Friday</th>
					<th style="width: 14%" class="cal">Saturday</th>
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
									<a href="/shift-details?id=${shift.shiftOwnerId}&shiftId=${shift.id}">
										<div class="child">${shift.shiftOwner}</div>
										<div class="child"> ${shift.startTimeString} - ${shift.endTimeString}</div>								
										<div class="child"><div class="bold">${shift.shiftName}
										</div></div>
									</a>
								<br>
								</c:forEach>
							
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	

</body>
</html>