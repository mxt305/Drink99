<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Default Page</title>
</head>
<body>
	<h1>測試頁面</h1>
	<c:choose>
	<c:when test="${ isLogin }">
	<!-- 已登入畫面 -->
	<p>已登入</p>
	<p>帳號：${ user }</p>
	<p><a href="<c:url value="/login?act=logout" />">登出</a></p>
	</c:when>
	<c:otherwise>
	<!-- 未登入畫面 -->
	<p>尚未登入，請先登入
	<form method="post" action="<c:url value="/login" />">
		<p>帳號：<input type="text" name="user" maxlength="10" /></p>
		<p><input type="submit" /></p>
	</form>
	</c:otherwise>
	</c:choose>
</body>
</html>