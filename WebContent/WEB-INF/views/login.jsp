<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常嚐酒久 - 登入</title>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="<c:url value="/css/calendar.min.css" />">
<script src="http://connect.facebook.net/zh_TW/all.js"></script>
<script type="text/javascript">
	var baseUrl = "<c:url value="/" />";
</script>
<script type="text/javascript" src="<c:url value="/js/fblogin.js" />"></script>
</head>
<body>
<%@include file="user_area.jsp" %>
<div class="container">
<div class="jumbotron">

<h1>常嚐酒久 - 登入</h1>
	<tr><td align="center" valign="center">
	<img id="FBLogin" alt="" src="<c:url value="/img/fb-login.png" />" />
	</td></tr>
</div>
</div>
</body>
</html>