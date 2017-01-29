<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
					<custom:hiddenInputs excludeParams="title, time, country, amounts"/>
					<div class="form-group">
						<label for="name" class="col-sm-10 col-sm-offset-2">
							<form:errors path="title"/>
						</label>
					</div>
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Title</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="title" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="time" class="col-sm-10 col-sm-offset-2">
							<form:errors path="time"/>
						</label>
					</div>
  					<div class="form-group">
    					<label for="time" class="col-sm-2 control-label">Time</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="time" id="time"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="country" class="col-sm-10 col-sm-offset-2">
							<form:errors path="country"/>
						</label>
					</div>
  					<div class="form-group">
    					<label for="country" class="col-sm-2 control-label">Country</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="country" id="country" >
      							<option value="0">Select country</option>
      							<form:options items="${countries}" itemLabel="name" itemValue="id"/>
      						</form:select>
    					</div>
  					</div>
  					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2">
							<form:errors path="amounts"/>
						</label>
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
      						<a class="btn btn-primary" href="/admin/recipe/cancel<custom:allParams/>">Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-xs-3"><h3>Recipe title</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Ingredients</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Update</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${recipes.content}" var="recipe">
				<div class="row">
					<div class="col-md-3 col-xs-3">${recipe.title}</div>
					<div class="col-md-3 col-xs-3">
						<c:forEach items="${recipe.amounts}" var="amount">
						<div>
							Amount: ${amount.amount} System: ${amount.system.name} Ingredient: ${amount.ingredient.name} 
						</div>
						</c:forEach>
					</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-warning" href="/admin/recipe/update/${recipe.id}<custom:allParams/>">update</a></div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-danger" href="/admin/recipe/delete/${recipe.id}<custom:allParams/>">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Title asc" paramValue="title"/>
						<custom:sort innerHtml="Title desc" paramValue="title,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${recipes.size}" />
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${recipes}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>
</div>
</body>
</html>