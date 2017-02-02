<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/author">Author</a></li>
					<li class="active"><a href="/admin/book">Book</a></li>
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
				<form:form class="form-horizontal" action="/admin/book" method="POST" modelAttribute="book">
					<div class="form-group">
    					<label for="author" class="col-sm-2 control-label">Author</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="author" id="author" items="${authors}" itemLabel="name" itemValue="id"/>
    					</div>
  					</div>
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Name</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="name" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-xs-3"><h3>Book</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Author</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Country</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Options</h3></div>
		</div>
			<c:forEach items="${books}" var="book">
				<div class="row">
					<div class="col-md-3 col-xs-3">${book.name}</div>
					<div class="col-md-3 col-xs-3">${book.author.name}</div>
					<div class="col-md-3 col-xs-3">${book.author.country.name}</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-warning" href="/admin/book/update/${book.id}">update</a>
					<a class="btn btn-danger" href="/admin/book/delete/${book.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12"></div>
</div>