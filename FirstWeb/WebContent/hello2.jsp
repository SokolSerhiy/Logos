<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello EveryOne! I'm ANITHER Person</h1>
	<div>
		<%
			String s = "I am";
			ua.lviv.lgs.Person p = (ua.lviv.lgs.Person) request
					.getAttribute("key");
			s = s + p;
			String name = p.getName();
		%>
		<%=s%>
	</div>
</body>
</html>