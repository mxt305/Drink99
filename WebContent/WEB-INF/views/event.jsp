<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常嚐酒久</title>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="<c:url value="/css/calendar.min.css" />">
</head>
<body>
	<%@include file="user_area.jsp"%>
	<div class="container">
		<div class="page-header">
			<h1>${ data.name }
				<small>By ${ data.enterpriser.name }</small>
			</h1>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">活動簡介</h3>
			</div>
			<div class="panel-body">${ data.note }</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">參加人員</h3>
			</div>
			<div class="panel-body">
			</div>
		</div>
		<h3>參加者</h3>
		<h3>留言</h3>
	</div>
</body>
</html>