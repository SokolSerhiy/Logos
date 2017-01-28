<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ingredient</title>
<style>
	.form-group>span{
		display: block;
		margin-left: 16.67%;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/ingredient">Ingredient</a></li>
					<li><a href="/admin/ms">Measuring system</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/amount">Amount</a></li>
					<li class="active"><a href="/admin/recipe">Recipe</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/recipe" method="POST" modelAttribute="recipe">
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Title</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="title" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="time" class="col-sm-2 control-label">Time</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="time" id="time"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="country" class="col-sm-2 control-label">Country</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="country" id="country" items="${countries}" itemLabel="name" itemValue="id"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label class="col-sm-2 control-label">Amounts</label>
    				</div>
    				<div class="form-group">
      					<form:checkboxes path="amounts" items="${amounts}" itemLabel="presentation" itemValue="id"/>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Save</button>
      						<a class="btn btn-primary" href="/admin/recipe/cancel">Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>Country name</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Update</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${recipes}" var="recipe">
				<div class="row">
					<div class="col-md-4 col-xs-4">${recipe.title}</div>
					<div class="col-md-4 col-xs-4">
						<c:forEach items="${recipe.amounts}" var="amount">
							${amount.amount} ${amount.system.name} ${amount.ingredient.name} 
						</c:forEach>
					</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-warning" href="/admin/recipe/update/${recipe.id}">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/recipe/delete/${recipe.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12"></div>
</div>
</body>
</html>