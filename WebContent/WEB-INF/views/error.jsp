<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>錯誤頁面</title>
<%@include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<h3>錯誤</h3>
		<p>${msg}</p>
		<a href="<c:url value="/" />" target="_blank" title="back">回首頁</a>
	</div>
</body>
</html>