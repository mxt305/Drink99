<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常嚐酒久</title>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="<c:url value="/css/calendar.min.css" />">
</head>
<body>
	<h2>${ data.name }</h2>
	<p>${ data.note }</p>
	<h3>參加者</h3>
	<h3>留言</h3>
</body>
</html>