<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Welcome</title>
</head>
<body>
	<h2>Welcome</h2>
	<div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="/admin">Admin</a>
		</sec:authorize>
		<a href="/apartment">Apartments</a>
		<sec:authorize access="!isAuthenticated()">
			<a href="/registration">Registration</a>
			<a href="/login">Login</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<form:form method="POST" action="/logout">
				<button class="btn btn-danger">Sign out</button>
			</form:form>
		</sec:authorize>
	</div>
</body>
</html>