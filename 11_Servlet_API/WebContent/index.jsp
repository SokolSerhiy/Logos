<!-- звичайна собі сторінка якщо не цей тег в якому  -->
<!-- вказано кодування і мова в яку потрібно компілювати -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<%-- ${user.login} такий запис вказує на те що ми  --%>
<!-- доступаємося до атрибуту user і до його поля -->
<!-- login, якщо користувач нуль тоді весь вираз не  -->
<!-- спрацює, якщо ні тоді буде викликаний геттер -->
<!-- цього поля і вираз набуде значення яке записано -->
<!-- в полі об'єкту, а також буде виведено на HTML -->
<h2>Hello ${user.login}</h2>
<h2><a href="/register">Registration</a></h2>
</body>
</html>