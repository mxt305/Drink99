<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常嚐酒久 - 登入</title>
<%@include file="header.jsp"%>
<script src="http://connect.facebook.net/zh_TW/all.js"></script>
<script type="text/javascript">
	var baseUrl = "<c:url value="/" />";
</script>
<script type="text/javascript" src="<c:url value="/js/fblogin.js" />"></script>
</head>
<body>
	<input id="FBLogin" type="button" value="UID" />
	<div class="fb-login-button" data-max-rows="1" data-size="xlarge"
		data-show-faces="false" data-auto-logout-link="true"></div>
</body>
</html>