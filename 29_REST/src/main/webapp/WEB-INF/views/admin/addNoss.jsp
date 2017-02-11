<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
					<li class="active"><a href="/admin/category">Category</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/ms">Measuring system</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li><a href="/admin/nosd">Name of specification digital</a></li>
					<li><a href="/admin/noss">Name of specification string</a></li>
					<li><a href="/admin/ss">Specification string</a></li>
					<li><a href="/admin/item">Item</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form modelAttribute="filter" action="/admin/category/add/noss/${category.id}" method="get" class="form-inline">
			<div class="form-group">
				<form:input path="search" placeholder="search" class="form-control" />
				<custom:hiddenInputs excludeParams="search"/>
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="col-md-12 col-xs-12 text-center"><h3>${category.name}</h3></div>
		<div class="row"><h4>Already added:</h4></div>
		<c:forEach items="${category.nameOfSpecificationStrings}" var="noss">
			<div class="row">
				<div class="col-md-6 col-xs-6">${noss.name}</div>
				<div class="col-md-6 col-xs-6"><a class="btn btn-danger" href="/admin/category/delete/noss/${category.id}/${noss.id}<custom:allParams/>">Remove</a></div>
			</div>
		</c:forEach>
		<div class="row"><h4>Can add:</h4></div>
		<c:forEach items="${page.content}" var="noss">
			<div class="row">
				<div class="col-md-6 col-xs-6">${noss.name}</div>
				<div class="col-md-6 col-xs-6"><a class="btn btn-success" href="/admin/category/add/noss/${category.id}/${noss.id}<custom:allParams/>">Add</a></div>
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
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
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
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>