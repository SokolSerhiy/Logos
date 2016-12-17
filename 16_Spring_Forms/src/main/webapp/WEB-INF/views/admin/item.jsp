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
				<form class="form-horizontal" action="/admin/item" method="POST">
					<input type="hidden" value="${category.id}" name="categoryId">
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Name</label>
    					<div class="col-sm-10">
      						<input type="text" class="form-control" name="name" id="name">
    					</div>
  					</div>
  					<div class="form-group">
    					<label class="col-sm-2 control-label">Producer</label>
    					<div class="col-sm-10">
      						<select class="form-control" name="producerId">
      							<c:forEach items="${producers}" var="producer">
      								<option value="${producer.id}">${producer.name}</option>
      							</c:forEach>
      						</select>
    					</div>
  					</div>
  					<c:forEach items="${nosss}" var="noss">
	  					<div class="form-group">
	    					<label class="col-sm-2 control-label">${noss.name}</label>
	    					<div class="col-sm-10">
	      						<select class="form-control" name="ssIds">
	      							<c:forEach items="${noss.specificationStrings}" var="ss">
	      								<option value="${ss.id}">${ss.name}</option>
	      							</c:forEach>
	    						</select>
	    					</div>
	  					</div>
  					</c:forEach>
  					<c:forEach items="${nosds}" var="nosd">
  					<input type="hidden" name="nosdIds" value="${nosd.id}">
  						<div class="form-group">
  							<label class="col-sm-2 control-label">${nosd.name}</label>
  							<div class="col-sm-7">
  								<input class="form-control" name="sdValues">
  							</div>
  							<div class="col-sm-3">
  								<select class="form-control" name="mss">
  									<c:forEach items="${measuringSystems}" var="measuringSystem">
  										<option value="${measuringSystem.id}">${measuringSystem.name}</option>
  									</c:forEach>
  								</select>
  							</div>
  						</div>
  					</c:forEach>
  					<div class="form-group">
  						<label class="col-sm-2 control-label">Price</label>
  						<div class="col-sm-7">
  							<input class="form-control" name="price">
  						</div>
  						<div class="col-sm-3">
  							<select class="form-control" name="msId">
  								<c:forEach items="${measuringSystems}" var="measuringSystem">
  									<option value="${measuringSystem.id}">${measuringSystem.name}</option>
  								</c:forEach>
  							</select>
  						</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form>
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