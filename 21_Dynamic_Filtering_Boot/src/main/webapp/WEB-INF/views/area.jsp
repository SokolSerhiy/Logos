<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="custom"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>Area</title>
<style type="text/css">
.form-horizontal .control-label {
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
					<li><a href="/admin/renttype">Rent type</a></li>
					<li class="active"><a href="/admin/area">Area</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<form:form class="form-horizontal" modelAttribute="filter"
					action="/admin/area" method="GET">
					<custom:hiddenInputs excludeParams="search"/>
					<div class="form-group">
						<div class="col-sm-12">
							<form:input placeholder="Search" path="search"
								class="form-control" />
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-sm-8">
				<form:form class="form-horizontal" modelAttribute="area"
					action="/admin/area" method="POST">
					<custom:hiddenInputs excludeParams="name"/>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-2" style="color: red;">
							<form:errors path="name" />
						</div>
						<label for="name" class="control-label col-sm-2">Name:</label>
						<div class="col-sm-10">
							<form:input type="text" path="name" id="name"
								class="form-control" />
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
			<div class="col-sm-10">
				<table class="table table-bordered">
					<tr>
						<th>Name</th>
						<th class="text-center">Update</th>
						<th class="text-center">Delete</th>
					</tr>
					<c:forEach var="area" items="${areas.content}">
						<tr>
							<td>${area.name}</td>
							<td class="text-center"><a
								href="/admin/area/update/${area.id}" class="btn btn-warning">Update</a></td>
							<td class="text-center"><a
								href="/admin/area/delete/${area.id}" class="btn btn-danger">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-sm-2">
				<div class="row">
					<div class="col-xs-12">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name" />
								<custom:sort innerHtml="Name desc" paramValue="name,desc" />
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${areas.size}" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 text-center">
				<custom:pageable page="${areas}" cell="<li></li>"
					container="<ul class='pagination'></ul>" />
			</div>
		</div>
	</div>
</body>
</html>