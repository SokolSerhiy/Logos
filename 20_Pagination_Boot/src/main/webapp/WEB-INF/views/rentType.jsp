<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Rent type</title>
<style type="text/css">
	.form-horizontal .control-label{
		text-align: left;
	}
</style>
</head>
<body>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="/admin/street">Street</a></li>
						<li class="active"><a href="/admin/renttype">Rent type</a></li>
						<li><a href="/admin/area">Area</a></li>
					</ul>
				</div>
			</div>
		</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<form:form class="form-horizontal" modelAttribute="renttype" action="/admin/renttype" method="POST">
					<div class="form-group">
						<form:errors path="name" element="div"></form:errors>
						<label for="name" class="control-label col-sm-2">Name:</label>
						<div class="col-sm-10">
							<form:input type="text" path="name" id="name" class="form-control"/> 
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-2">
							<button class="btn btn-success" type="submit">Create</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<table width="100%" class="table table-bordered">
					<tr>
						<th>Name</th>
						<th class="text-center">Update</th>
						<th class="text-center">Delete</th>
					</tr>
					<c:forEach var="rentType" items="${rentTypes}">
						<tr>
							<td>${rentType.name}</td>
							<td class="text-center"><a href="/admin/renttype/update/${rentType.id}" class="btn btn-warning">Update</a></td>
							<td class="text-center"><a href="/admin/renttype/delete/${rentType.id}" class="btn btn-danger">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>