<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="categories" items="${categories}">
	<c:set var="categories" value="${categories.childs}" scope="request"/>
	<div class="row">
	    <div class="col-md-4 col-xs-4" style="padding: 0 0 0 ${categories.level * 50}px" >${categories.name}</div>
	    <c:choose>
	    	<c:when test="${categories.haveChilds}">
	    		<div class="col-md-2 col-xs-2"></div>
	    		<div class="col-md-2 col-xs-2"></div>
	    	</c:when>
	    	<c:otherwise>
				<div class="col-md-2 col-xs-2"><a class="btn btn-success" href="/admin/category/add/noss/${categories.id}">Add</a></div>
				<div class="col-md-2 col-xs-2"><a class="btn btn-success" href="/admin/category/add/nosd/${categories.id}">Add</a></div>
	    	</c:otherwise>
	    </c:choose>
		<div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/category/update/${categories.id}">update</a></div>
		<div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/category/delete/${categories.id}">delete</a></div>
    </div>
	<jsp:include page="divCategory.jsp"/>
</c:forEach>