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
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/ms">Measuring system</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li><a href="/admin/nosd">Name of specification digital</a></li>
					<li><a href="/admin/noss">Name of specification string</a></li>
					<li><a href="/admin/ss">Specification string</a></li>
					<li class="active"><a href="/admin/item">Item</a><span
						class="sr-only">(current)</span></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
	<c:if test="${category ne null}">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item">
					<form:hidden value="${category.id}" path="category"/>
					<div class="form-group">
						<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
					</div>
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Name</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="name" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label class="col-sm-2 control-label">Producer</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="producer" itemLabel="name" itemValue="id" items="${producers}"/>
    					</div>
  					</div>
  					<c:forEach items="${nosss}" var="noss">
	  					<div class="form-group">
	    					<label class="col-sm-2 control-label">${noss.name}</label>
	    					<div class="col-sm-10">
	      						<form:select path="specificationStrings" items="${noss.specificationStrings}" itemLabel="name" itemValue="id" class="form-control" multiple="false"/>
	    					</div>
	  					</div>
  					</c:forEach>
  					<c:forEach items="${nosds}" var="nosd" varStatus="vs">
  					<form:hidden path="specificationDigitals[${vs.index}].nameOfSpecificationDigital" value="${nosd.id}"/>
  						<div class="form-group">
							<label for="value${vs.index}" class="col-sm-offset-2 col-sm-10"><form:errors path="specificationDigitals[${vs.index}].value"/></label>
						</div>
  						<div class="form-group">
  							<label class="col-sm-2 control-label">${nosd.name}</label>
  							<div class="col-sm-7">
  								<form:input id="value${vs.index}" path="specificationDigitals[${vs.index}].value" class="form-control"/>
  							</div>
  							<div class="col-sm-3">
  								<form:select itemLabel="name" itemValue="id" items="${measuringSystems}" path="specificationDigitals[${vs.index}].measuringSystems" class="form-control" multiple="false"/>
  							</div>
  						</div>
  					</c:forEach>
  					<div class="form-group">
						<label for="price" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>
					</div>
  					<div class="form-group">
  						<label class="col-sm-2 control-label">Price</label>
  						<div class="col-sm-7">
  							<form:input id="price" path="price" class="form-control"/>
  						</div>
  						<div class="col-sm-3">
  							<form:select itemLabel="name" itemValue="id" items="${measuringSystems}" path="measuringSystems" class="form-control" multiple="false"/>
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
	</c:if>
		<div class="row">
			<div class="col-md-2 col-xs-2"><h3>Item name</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Item price</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Category</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Producer</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Update</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${items}" var="item">
				<div class="row">
					<div class="col-md-2 col-xs-2">${item.name}</div>
					<div class="col-md-2 col-xs-2">${item.price}</div>
					<div class="col-md-2 col-xs-2">${item.category.name}</div>
					<div class="col-md-2 col-xs-2">${item.producer.name}</div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/item/update/${item.id}">update</a></div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/item/delete/${item.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12"></div>
</div>