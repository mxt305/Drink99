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
<script>(function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.5&appId=1016184181753221";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>
<%@include file="user_area.jsp" %>
<div class="container">
	<div class="jumbotron">
	  <h1>常嚐酒久!</h1>
	  <p>常嚐酒久是....</p>
	  <p><a class="btn btn-primary btn-lg" href="#" role="button">新增活動</a></p>
</div>

	<p>
	</p>
	<%@include file="main_calendar.jsp" %>
	<%@include file="footer.jsp" %>
</div>
</body>
</html>
