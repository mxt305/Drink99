<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常嚐酒久</title>
</head>
<body>
<h1>常嚐酒久</h1>
<p>
<c:choose>
<c:when test="${ isLogin }">
user:${ user }
</c:when>
<c:otherwise>
未登入 請先登入
</c:otherwise>
</c:choose>
</p>
<p>123456789</p>
<p>${ today }</p>
</body>
</html>