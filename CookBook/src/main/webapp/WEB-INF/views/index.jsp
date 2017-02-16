<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CookBook</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Hello</h2>
<form action="/" method="post" id="form" enctype="multipart/form-data">
	<input name="name" id="name">
	<input name="amount" id="amount">
	<input name="file" type="file" id="file">
</form>
	<button type="button" id="button">Ok</button>
<script type="text/javascript">
	document.getElementById('button').addEventListener('click', function(event) {
		event.preventDefault();
		var name = document.getElementById('name').value;
		var amount = document.getElementById('amount').value;
		var file = document.getElementById('file').files[0];
		console.log(file);
		var xhr = new XMLHttpRequest();
		var data = new FormData();
		data.append('ingredient.name', name);
		data.append('file', file);
		data.append('amount', amount);
		xhr.open('POST', '/', true);
		xhr.send(data);
	});
</script>

<a href="/admin">admin</a>
</body>
</html>