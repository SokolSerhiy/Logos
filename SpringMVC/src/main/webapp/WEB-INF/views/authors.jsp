<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authors</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Age></th>
			</tr>
		</thead>
		<tbody>
			<!-- This is JSTL foreach cycle that allows web page to pass through collection. Items - attribute name, which was given at controller method -->
			<!-- Це є JSTL foreach цикл, який дозволяє веб сторінці проходити по елементах колекції, яка приходить зі сервера. Items - назва атрибуту, яка була записана на методі контролера -->
			<c:forEach items="${allAuthors}" var="author">
				<tr>
					<td>${author.name}</td>
					<td>${author.age}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>