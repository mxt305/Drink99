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
	  <c:if test="${ isLogin }">
	  <p><a class="btn btn-primary btn-lg" href="<c:url value="/event/add" />" role="button">新增活動</a></p>
	  </c:if>
</div>

	<p>
	</p>
	<%@include file="main_calendar.jsp" %>
	<%@include file="footer.jsp" %>
</div>
</body>
</html>
