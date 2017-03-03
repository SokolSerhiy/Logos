<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/ingredient">Ingredient</a></li>
					<li><a href="/admin/ms">Measuring system</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li class="active"><a href="/admin/amount">Amount</a></li>
					<li><a href="/admin/recipe">Recipe</a></li>
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
				<form:form class="form-horizontal" action="/admin/amount"
					method="POST" modelAttribute="amount">
					<custom:hiddenInputs excludeParams="ingredient, amount, system"/>
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label"
							for="ingredient" style="color: red; text-align: left;"><form:errors
								path="ingredient" /></label>
					</div>
					<div class="form-group">
						<label for="ingredient" class="col-sm-2 control-label">Ingredient</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="ingredient"
								id="ingredient" items="${ingredients}" itemValue="id"
								itemLabel="name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label"
							for="amount" style="color: red; text-align: left;"><form:errors
								path="amount" /></label>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Amount</label>
						<div class="col-sm-10">
							<form:input type="text" class="form-control" path="amount"
								id="name" />
						</div>
					</div>
					<div class="form-group">
						<label for="ms" class="col-sm-2 control-label">Measuring
							system</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="system" id="ms"
								items="${measuringSystems}" itemLabel="name" itemValue="id" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">Create</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-xs-3">
				<h3>Amount</h3>
			</div>
			<div class="col-md-3 col-xs-3">
				<h3>Measuring System</h3>
			</div>
			<div class="col-md-3 col-xs-3">
				<h3>Ingredient</h3>
			</div>
			<div class="col-md-3 col-xs-3">
				<h3>Options</h3>
			</div>
		</div>
		<c:forEach items="${page.content}" var="amount">
			<div class="row">
				<div class="col-md-3 col-xs-3">${amount.amount}</div>
				<div class="col-md-3 col-xs-3">${amount.system.name}</div>
				<div class="col-md-3 col-xs-3">${amount.ingredient.name}</div>
				<div class="col-md-3 col-xs-3">
					<a class="btn btn-warning" href="/admin/amount/update/${amount.id}<custom:allParams/>">update</a>
					<a class="btn btn-danger" href="/admin/amount/delete/${amount.id}<custom:allParams/>">delete</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
					<div class="col-md-6 col-xs-6 text-center">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Amount asc" paramValue="amount" />
								<custom:sort innerHtml="Amount desc" paramValue="amount,desc" />
								<custom:sort innerHtml="Ingredient name asc" paramValue="ingredient.name" />
								<custom:sort innerHtml="Ingredient name desc" paramValue="ingredient.name,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-6 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
				</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>