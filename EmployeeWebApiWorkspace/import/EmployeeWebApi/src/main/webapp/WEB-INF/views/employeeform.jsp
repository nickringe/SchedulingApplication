<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update ${employee.firstname} ${employee.lastname}</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

<form class="form" action="/postEmployee" method="post">
<input type="hidden" value="${employee.id}" name="id">
First Name: <input type="text" value="${employee.firstname}" name="firstname">
Last Name: <input type="text" value="${employee.lastname}" name="lastname">
Email Address: <input type="text" value="${employee.email}" name="email">
Employee ID: <input type="text" value="${employee.empId}" name="empId">
<input type="submit" class="btn-success" value="Save Changes">
<button onclick="window.location.href='/'" type="button" class="btn-danger">Cancel</button>

</form>

</body>
</html>