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
		<title>Master Schedule</title>

	</head>
	
	<body>
		<header class="header">
			<h1>Scheduling App</h1> 
		</header>
	<br>
		<div class="text-center">
			<a href="/" class="btn btn-info">Home</a>
			<a href="" class="btn btn-primary active">View Open Shifts</a>	
			<a href="/create-shift" class="btn btn-info">Create New Shifts</a> <br><br>
			<h2>View open shifts</h2>
		</div>

		<c:forEach var="masterList" items="${masterList }">
			<ul>
				<li class="btn btn-info"><a href="/shift?id=">
					${masterList.dateString }
					Position: ${masterList.shiftName }
					Time: ${masterList.startTimeString } - ${masterList.endTimeString }</a></li>
			</ul>
		</c:forEach>
	</body>
</html>