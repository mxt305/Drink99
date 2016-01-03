<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常嚐酒久</title>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="<c:url value="/css/calendar.min.css" />">
</head>
<body>
<%@include file="user_area.jsp" %>
<div class="container">
	<div class="jumbotron">
	  <h1>常嚐酒久!</h1>
	  <p>常嚐酒久是....</p>
	  <p><a class="btn btn-primary btn-lg" href="#" role="button">新增活動</a></p>
	</div>
	<div
    class="fb-login-button"
    data-max-rows="1"
    data-size="xlarge"
    data-show-faces="false"
    data-auto-logout-link="true"></div>
	<p>
	</p>
	<%@include file="main_calendar.jsp" %>
	<p>123456789</p>
	<p>${ today }</p>
</div>
</body>
</html>