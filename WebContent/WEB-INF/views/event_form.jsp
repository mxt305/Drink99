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
	<div class="container">
		<div class="page-header">
		<c:choose>
		<c:when test="${ mode == 0 }"><h2>新增活動</h2></c:when>
		<c:otherwise><h2>修改活動</h2></c:otherwise>
		</c:choose>
	</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-horizontal" method="post">
					<div class="form-group">
						<label for="ev_name" class="col-sm-2 control-label">活動類型</label>
						<div class="col-sm-10">
							<label class="radio-inline"> <input type="radio"
								name="cat" id="ev_cat1" value="0">
								1
							</label> <label class="radio-inline"> <input type="radio"
								name="cat" id="ev_cat2" value="1">
								2
							</label> <label class="radio-inline"> <input type="radio"
								name="cat" id="ev_cat3" value="2">
								3
							</label><label class="radio-inline"> <input type="radio"
								name="cat" id="ev_cat3" value="3">
								4
							</label><label class="radio-inline"> <input type="radio"
								name="cat" id="ev_cat3" value="4">
								5
							</label><label class="radio-inline"> <input type="radio"
								name="cat" id="ev_cat3" value="5">
								6
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="ev_name" class="col-sm-2 control-label">活動名稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="ev_name" name="name"
								placeholder="Drinking happyday..." maxlength="80" value="${ data.name }" required />
						</div>
					</div>
					<div class="form-group">
						<label for="ev_date" class="col-sm-2 control-label">活動日期</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="ev_date" name="date" min="${ today }" value="${ data.date }" required />
						</div>
					</div>
					<div class="form-group">
						<label for="ev_startT" class="col-sm-2 control-label">開始時間</label>
						<div class="col-sm-10">
							<input type="time" class="form-control" id="ev_startT"
								name="startT" value="${ data.startT }" required />
						</div>
					</div>
					<div class="form-group">
						<label for="ev_endT" class="col-sm-2 control-label">結束時間</label>
						<div class="col-sm-10">
							<input type="time" class="form-control" id="ev_endT" name="endT" value="${ data.endT }" required />
						</div>
					</div>
					<div class="form-group">
						<label for="ev_place" class="col-sm-2 control-label">活動地點</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="ev_place"
								name="place" maxlength="50" value="${ data.place }" required />
						</div>
					</div>
					<div class="form-group">
						<label for="ev_note" class="col-sm-2 control-label">活動描述</label>
						<div class="col-sm-10">
							<textarea id="ev_note" name="note" class="form-control" rows="5"
								placeholder="Let's party time!">${ data.note }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default btn-lg">
								<span class="glyphicon glyphicon-send" aria-hidden="true"></span>
								Go Party!
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>