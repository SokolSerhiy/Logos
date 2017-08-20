<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="custom"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/bootstrap.min.css">.
<link rel="stylesheet" href="/css/chosen.min.css">
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/chosen.jquery.min.js"></script>
<script>
	$(function(){
		$('select').chosen({width:'100%'});
	})
</script>
<title>Apartments</title>
</head>
<body>
<div class="container">
		<sec:authorize access="hasRole('ROLE_OWNER')">
			<div class="row">
				<div class="col-sm-12">
					<form:form class="form-horizontal" modelAttribute="apartment" action="/apartment" method="POST" enctype="multipart/form-data">
						<sec:authentication var="principal" property="principal" />
						<form:hidden path="owner" value="${principal.username}"/>
						<div class="form-group">
							<label for="price" class="control-label col-sm-2">Price:</label>
							<div class="col-sm-10">
								<form:input type="text" path="price" id="price" class="form-control"/> 
							</div>
						</div>
						<div class="form-group">
							<label for="rt" class="control-label col-sm-2">Rent type:</label>
							<div class="col-sm-10">
								<form:select path="rentType" items="${rentTypes}" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="rooms" class="control-label col-sm-2">Rooms:</label>
							<div class="col-sm-10">
								<form:input type="text" path="rooms" id="rooms" class="form-control"/> 
							</div>
						</div>
						<div class="form-group">
							<label for="area" class="control-label col-sm-2">Area:</label>
							<div class="col-sm-10">
								<form:select path="area" items="${areas}" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="street" class="control-label col-sm-2">Street:</label>
							<div class="col-sm-10">
								<form:select path="street" items="${streets}" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="number" class="control-label col-sm-2">Number:</label>
							<div class="col-sm-10">
								<form:input type="text" path="number" id="number" class="form-control"/> 
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="control-label col-sm-2">Description:</label>
							<div class="col-sm-10">
								<form:textarea path="description" id="description" class="form-control" rows="5"/>
							</div>
						</div>
						<div class="form-group">
							<label for="file" class="control-label col-sm-2">Select img:</label>
							<div class="col-sm-10">
								<input name="file" id="file" type="file">
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
		</sec:authorize>
		<div class="row">
			<div class="col-sm-12">
				<table class="table table-bordered">
					<tr>
						<th>Photo</th>
						<th>Price</th>
						<th>Rate</th>
						<th>Rooms</th>
						<th>Area</th>
					</tr>
					<c:forEach var="apartment" items="${apartments.content}">
						<tr>
							<td><img width="100%" alt="Apartment image" src="/images/${apartment.photoUrl}?version=${apartment.version}"></td>
							<td>${apartment.price} uah ${apartment.rentType}</td>
							<td>${apartment.rate}</td>
							<td>${apartment.rooms}</td>
							<td>${apartment.area}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 text-center">
				<custom:pageable page="${apartments}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
	</div>
</body>
</html>