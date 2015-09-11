<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- You must use form if you want to send some data to server. Action - URL which will be created when the button in pressed -->
	<!-- Ви повинні використовувати форму, якщо необхідно передати дані на сервер. Action - URL, яка створиться при натисканні кнопки підтвердження -->

	<form action="showAllAuthors" method="POST">
		<h2>Input Data</h2>
		<p>
			Name <input type="text" name="name" value=""> Age <input
				type="text" name="age" value="">
			<button type="submit">Submit</button>
	</form>
</body>
</html>