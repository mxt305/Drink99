<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常嚐酒久</title>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="<c:url value="/css/calendar.min.css" />">
<script type="text/javascript">
	var baseurl = "<c:url value="/" />";
	var userid = ${ data.id };
</script>
<script src="<c:url value="/js/userevent.js" />"></script>
</head>
<body>
	<%@include file="user_area.jsp"%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">會員資料</div>
			<div class="panel-body">
				<dl>
					<dt>名稱</dt>
					<dd>${ data.name }</dd>
					<dt>ID</dt>
					<dd>${ data.id }</dd>
					<dt>最後登入</dt>
					<dd>0000-00-00</dd>
				</dl>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">參加活動</div>
			<div class="panel-body">
				<div>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#current"
							aria-controls="current" role="tab" data-toggle="tab">當前活動</a></li>
						<li role="presentation"><a href="#history"
							aria-controls="profile" role="tab" data-toggle="tab">歷史活動</a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="current">
							<p>當前活動列表...</p>
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>活動名稱</th>
										<th>主辦人</th>
										<th>時間</th>
									</tr>
								</thead>
								<tbody id=cur_ev>
								</tbody>
							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="history">
							<p>歷史活動列表...</p>
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>活動名稱</th>
										<th>主辦人</th>
										<th>時間</th>
									</tr>
								</thead>
								<tbody id=his_ev>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script id="user_event_tmpl" type="text/x-jquery-tmpl">
	<%@include file="user_event_tmpl.jsp"%>
	</script></body>
</html>