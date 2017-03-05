<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-sm-12 col-xs-12 parent">
		<c:forEach items="${items}" var="item">
			<div>
				<img src="/images/item/${item.id}.jpg?version=${item.version}" width="100%">
				<p>${item.name}</p>
				<p>${item.price} грн</p>
				<div>
					<a href="/del/${item.id}" class="btn btn-primary">З корзини</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>