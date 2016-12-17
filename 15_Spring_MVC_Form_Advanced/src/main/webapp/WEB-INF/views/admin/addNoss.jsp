<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="col-md-12 col-xs-12 text-center"><h3>${category.name}</h3></div>
		<div class="row"><h4>Already added:</h4></div>
		<c:forEach items="${category.nameOfSpecificationStrings}" var="noss">
			<div class="row">
				<div class="col-md-6 col-xs-6">${noss.name}</div>
				<div class="col-md-6 col-xs-6"><a class="btn btn-danger" href="/admin/category/delete/noss/${category.id}/${noss.id}">Remove</a></div>
			</div>
		</c:forEach>
		<div class="row"><h4>Can add:</h4></div>
		<c:forEach items="${nosss}" var="noss">
			<div class="row">
				<div class="col-md-6 col-xs-6">${noss.name}</div>
				<div class="col-md-6 col-xs-6"><a class="btn btn-success" href="/admin/category/add/noss/${category.id}/${noss.id}">Add</a></div>
			</div>
		</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12"></div>
</div>