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
var user_status = ${ status };
</script>
<script src="<c:url value="/js/eventmsg.js" />"></script>
<script src="<c:url value="/js/eventmem.js" />"></script>
</head>
<body>
	<%@include file="user_area.jsp"%>
	<div class="container">
		<div class="page-header">
			<div id="alert_area">
			</div>
			<h1>${ data.name }
				<small><a href="<c:url value="/user?id=" />${ data.enterpriser.id }">By ${ data.enterpriser.name }</a></small>
				<c:if test="${ !outOfDate }">
				<c:if test="${ data.enterpriser.id == user.id }">
				<a class="btn btn-default" href="<c:url value="/event/edit?id=" />${ data.id }" role="button">編輯</a>
				</c:if>
				</c:if>
			</h1>
		</div>
			
		<div>
		<c:if test="${ !outOfDate }">
			<div class="btn-group" role="group" aria-label="ev_join">
			  <button id="btn_join" type="button" class="btn btn-default" disabled>參加</button>
			  <button id="btn_optout" type="button" class="btn btn-default" disabled>不參加</button>
			  <button id="btn_cancel" type="button" class="btn btn-default" disabled>取消活動</button>
			</div>
		</c:if>
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
		<div class="panel panel-default">
			<div class="panel-heading">受邀成員</div>
			<div class="panel-body">
				<div id="b_mem0"></div>
				<div style="clear: both;"></div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">確定參加</div>
			<div class="panel-body">
				<div id="b_mem1"></div>
				<div style="clear: both;"></div>
			</div>
		</div>
		<h2>留言</h2>
		<div id="msg_block">
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<%@include file="event_msg_form.jsp"%>
			</div>
		</div>
	</div>
	<script id="event_msg_tmpl" type="text/x-jquery-tmpl">
	<%@include file="event_msg_tmpl.jsp"%>
	</script>
	<script id="event_join_tmpl" type="text/x-jquery-tmpl">
	<%@include file="event_join_tmpl.jsp"%>
	</script>
	<script id="alert_tmpl" type="text/x-jquery-tmpl">
	<%@include file="alert_tmpl.jsp"%>
	</script>
</body>
</html>
