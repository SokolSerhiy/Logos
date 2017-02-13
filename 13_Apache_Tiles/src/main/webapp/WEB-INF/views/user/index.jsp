<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Hello</h2>
<ul>
	<c:forEach items="${categories}" var="category">
		<li><a href="/category/${category.id}">${category.name}</a></li>
	</c:forEach>
</ul>
<a href="/admin">admin</a>