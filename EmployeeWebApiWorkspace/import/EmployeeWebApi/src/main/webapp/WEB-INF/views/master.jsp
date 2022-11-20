<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Master Schedule</title>
</head>
<body>
<h2>View open shifts</h2>

<c:forEach var="masterList" items="${masterList }">
<ul>

<li class="btn btn-info"><a href="/shift?id=">

${masterList.dateString }
Position: ${masterList.shiftName }
Time: ${masterList.startTimeString } - ${masterList.endTimeString }</a></li>
</ul>
</c:forEach>
<form action="/" method="get">
		<input type="submit" class="btn-success" value="Back To Home">
	</form>
</body>
</html>