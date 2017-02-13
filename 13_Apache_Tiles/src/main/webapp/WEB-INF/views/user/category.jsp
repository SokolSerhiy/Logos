<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Category: ${category.name}</h2>
<c:forEach items="${items}" var="item">
	<div>${item.name} ${item.price} $</div>
</c:forEach>
<c:if test="${empty items}">
	<h3>Category is empty</h3>
</c:if>