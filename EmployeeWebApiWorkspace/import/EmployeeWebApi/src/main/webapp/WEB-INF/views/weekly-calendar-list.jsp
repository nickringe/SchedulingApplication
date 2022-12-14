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
		<title>Weekly Schedule - List</title>
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
			<h2>Weekly Schedule - List</h2>
		</div>
		<div class="child">
			<a href="#0" class="btn btn-primary active">List View</a> &nbsp;
			<a href="/weekly-calendar?date=${curDayDate}" class="btn btn-info">Weekly View</a> &nbsp;
			<a href="/create-shift" class="btn btn-success">Add Shift</a>
		</div> 
		<br><br>
		<div class ="child">Search by Date:</div>
		<div class ="child">
			<form action="/weekly-calendar-list" class="form-group">
				<input type="date" name="date" id="date" required>
				<input type="submit" value="Search" class="btn btn-info active">
			</form>
		</div>
		
		<div class="month-navigation">

			<a id="prevButton" href="weekly-calendar-list?date=${prevWeekDate}"><i
				class="fa-solid fa-arrow-left"></i></a>&nbsp;

			<!-- Gets current month and year as a String -->
			<div>
				Week of: ${curWeekMonthString} ${curWeekDate.dayOfMonth} ${curWeekDate.year}&nbsp;<br>
				<div class="child"><a href="/weekly-calendar-list" title="Go to Today"><i class="fa-solid fa-calendar-check"></i></a></div>
			</div>

			<a id="nextButton" href="/weekly-calendar-list?date=${nextWeekDate}"><i
				class="fa-solid fa-arrow-right"></i></a>

		</div>
				<div class="childCenter">
					<c:forEach var="date" varStatus="status" items="${dates}" begin="0" end="6" step="1">
						<a href="/weekly-calendar-list?date=${date}" class="btn1 btn-primary <c:if test="${status.index == 3}">active</c:if>">
							${weekDays[status.index]}
						</a> &nbsp;
					</c:forEach>
				</div>
			<div class="child"><h4>${dayOfWeek}</h4></div>
			
			<table class="table">
				<tr>
					<th>Employee:</th>
					<th>Shift Name:</th>
					<th>Start Time:</th>
					<th>End Time:</th>
					<th>Shift Length (Hours):</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>

						<c:forEach var="shift" items="${sortedDates}">
						<tr>
						<td><a href="/shift-details?id=${shift.shiftOwnerId }&shiftId=${shift.id}">${shift.shiftOwner}</a></td>
						<td>${shift.shiftName}</td>
						<td>${shift.startTimeString}</td>
						<td>${shift.endTimeString}</td>
						<td>${shift.shiftLength}</td>
						<td><a href="/shift-edit?id=${shift.shiftOwnerId}&shiftId=${shift.id}" class="btn btn-info">Edit</a></td>
						 <td>
							<form action="/confirm-delete-shift">
								<input type="hidden" value="${shift.shiftOwnerId }" name="id" id="id">
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
		

	</body>
</html>