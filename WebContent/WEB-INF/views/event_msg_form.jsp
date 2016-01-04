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
	<div class="input-group">
  		<span class="input-group-addon" id="sizing-addon1">活動名稱</span>
  		<input type="text" class="form-control" placeholder="Drinking happyday..." aria-describedby="sizing-addon1">
			</h1>
	</div>
	<br></br>
		
	<div class="input-group">
  		<span class="input-group-addon" id="sizing-addon1">活動描述</span>
  		<input type="text" class="form-control" placeholder="Let's party time!" aria-describedby="sizing-addon1">
			</h1>
	</div>
	<br></br>
		
	<div class="input-group">
  		<span class="input-group-addon" id="sizing-addon1">活動日期</span>
  		<input type="text" class="form-control" placeholder="2017/01/01 5:00p.m." aria-describedby="sizing-addon1">
			</h1>
	</div>
	<br></br>
	
	<div class="input-group">
  		<span class="input-group-addon" id="sizing-addon1">活動地點</span>
  		<input type="text" class="form-control" placeholder="永遠的313..." aria-describedby="sizing-addon1">
			</h1>
	</div>
	<br></br>
	
	<div class="input-group">
  		<span class="input-group-addon" id="sizing-addon1">活動種類</span>
  		<input type="text" class="form-control" placeholder="期末考大爆炸" aria-describedby="sizing-addon1">
			</h1>
	</div>
	<br></br>

<button type="button" class="btn btn-default btn-lg">
  <span class="glyphicon glyphicon-send" aria-hidden="true"></span> Go Party!
</button>

</body>
</html>