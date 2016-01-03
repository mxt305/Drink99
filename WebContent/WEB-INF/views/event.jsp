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
<script type="text/javascript">
var baseurl = "<c:url value="/" />";
var evid = ${ data.id };
</script>
<script src="<c:url value="/js/eventmsg.js" />"></script>
</head>
<body>
	<%@include file="user_area.jsp"%>
	<div class="container">
		<div class="page-header">
			<h1>${ data.name }
				<small><a href="<c:url value="/user?id=" />${ data.enterpriser.id }">By ${ data.enterpriser.name }</a></small>
			</h1>
		</div>
		<h2>活動資訊</h2>
		<dl>
			<dt>介紹</dt>
			<dd>${ data.note }</dd>
			<dt>時間</dt>
			<dd>${ data.date }<br />${ data.startT }</dd>
			<dt>地點</dt>
			<dd>${ data.place }</dd>
		</dl>
		<h2>參加人員</h2>

		<h2>留言</h2>
		<div id="msg_block">
		</div>
		<div class="panel panel-default">
			<div class="panel-body">[表單]</div>
		</div>
	</div>
	<script id="event_msg_tmpl" type="text/x-jquery-tmpl">
	<%@include file="event_msg_tmpl.jsp"%>
	</script>
</body>
</html>
