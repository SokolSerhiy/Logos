<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="parents" items="${parents}">
	<c:set var="parents" value="${parents.childs}" scope="request" />
	<c:choose>
		<c:when test="${parents.id eq selectedId}">
			<option selected
				style="padding: 0 0 0 ${parents.level * 20}px"
				value="${parents.id}">${parents.name}</option>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${category.id eq parents.id}">
					<option style="padding: 0 0 0 ${parents.level * 20}px" disabled="disabled"
					value="${parents.id}">${parents.name}</option>
				</c:when>
				<c:otherwise>
					<option style="padding: 0 0 0 ${parents.level * 20}px"
					value="${parents.id}">${parents.name}</option>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<jsp:include page="optionCategory.jsp" />
</c:forEach>