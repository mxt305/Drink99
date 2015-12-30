<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse navbar-static-top">
<div class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<c:url value="/" />">常嚐酒久</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<c:choose>
		<c:when test="${ isLogin }">
		user:${ user }
		</c:when>
		<c:otherwise>
		未登入 請先登入
		</c:otherwise>
		</c:choose>
	</div>
	<!--/.navbar-collapse -->
</div>
</nav>