<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Street</title>
<style type="text/css">
	.form-horizontal .control-label{
		text-align: left;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<form class="form-horizontal" action="/admin/street" method="POST">
					<div class="form-group">
						<label for="name" class="control-label col-sm-2">Name:</label>
						<div class="col-sm-10">
							<input type="text" name="name" id="name" class="form-control"> 
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-2">
							<button class="btn btn-success" type="submit">Create</button>
						</div>
					</div>
				</form>
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
					<c:forEach var="street" items="${streets}">
						<tr>
							<td>${street.name}</td>
							<td class="text-center"><a href="/admin/street/update/${street.id}" class="btn btn-warning">Update</a></td>
							<td class="text-center"><a href="/admin/street/delete/${street.id}" class="btn btn-danger">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>