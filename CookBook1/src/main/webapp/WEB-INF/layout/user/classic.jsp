<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<style type="text/css">
body {
	padding-bottom: 70px; 
	padding-top: 70px;
}
</style>
<!-- сюди буде підставлено атрибут з ім'ям title -->
<title><tiles:getAsString name="title" /></title>
</head>
<body>
<!-- 	а сюди jsp файл з ім'ям header -->
    <tiles:insertAttribute name="header" />
	<div class="container-fluid">
<!-- 	сюди jsp файл з ім'ям body -->
		<tiles:insertAttribute name="body" />
	</div>
<!-- 	сюди jsp файл з ім'ям footer -->
		<tiles:insertAttribute name="footer" />
<!-- 	зверніть увагу на те що в темплейт файлі (тут) -->
<!-- 	вже описано основний HTML код, в інших файлах -->
<!-- 	його писати не потрібно(я про <head><body><html>) -->
</body>
</html>