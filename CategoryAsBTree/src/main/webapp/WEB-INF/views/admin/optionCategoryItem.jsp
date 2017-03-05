<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="categories" items="${categories}">
	<c:set var="categories" value="${categories.childs}" scope="request" />
	<c:choose>
		<c:when test="${categories.id eq selectedId}">
			<option selected
				style="padding: 0 0 0 ${categories.level * 20}px"
				value="${categories.id}">${categories.name}</option>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${categories.haveChilds}">
					<option style="padding: 0 0 0 ${categories.level * 20}px" disabled="disabled"
					value="${categories.id}">${categories.name}</option>
				</c:when>
				<c:otherwise>
					<option style="padding: 0 0 0 ${categories.level * 20}px"
					value="${categories.id}">${categories.name}</option>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<jsp:include page="optionCategoryItem.jsp" />
</c:forEach>