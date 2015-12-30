<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header">

	<div class="pull-right form-inline">
		<div class="btn-group">
			<a id="btn_prev" class="btn btn-primary" href=""><<
				Prev</a>
			<a id="btn_today" class="btn btn-primary" href="<c:url value="/" />">Today</a>
			<a id="btn_next" class="btn btn-primary" href="">Next
				>></a>
		</div>
		<div class="btn-group">
			<button class="btn btn-warning" data-calendar-view="year">Year</button>
			<button class="btn btn-warning active" data-calendar-view="month">Month</button>
			<button class="btn btn-warning" data-calendar-view="week">Week</button>
			<button class="btn btn-warning" data-calendar-view="day">Day</button>
		</div>
	</div>
	<h3 id="cal_title"></h3>
</div>
<div id="calendar"></div>
<script type="text/javascript" src="<c:url value="/js/underscore-min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendar.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/language/zh-TW.js" />"></script>
<script type="text/javascript">
	var $_GET = {};
	document.location.search.replace(/\??(?:([^=]+)=([^&]*)&?)/g, function() {
		function decode(s) {
			return decodeURIComponent(s.split("+").join(" "));
		}
		$_GET[decode(arguments[1])] = decode(arguments[2]);
	});
	Date.prototype.yyyymmdd = function() {

		var yyyy = this.getFullYear().toString();
		var mm = (this.getMonth() + 1).toString(); // getMonth() is zero-based         
		var dd = this.getDate().toString();

		return yyyy + '-' + (mm[1] ? mm : "0" + mm[0]) + '-'
				+ (dd[1] ? dd : "0" + dd[0]);
	};
	var day = "now";
	var url_param = "";
	var d = new Date($_GET["date"]);
	if (Object.prototype.toString.call(d) === "[object Date]") {
		if (isNaN(d.getTime())) { // d.valueOf() could also work
		} else {
			day = d.yyyymmdd();
			url_param = "?y=" + d.getFullYear() + "&v=" + (d.getMonth() + 1);
		}
	}

	if (day == "now") {
		d = new Date();
	}

	$(function() {
		d.setDate(1);
		$("#cal_title").text(d.getFullYear() + "年" + (d.getMonth() + 1) + "月");
		d.setMonth(d.getMonth()-1);
		$("#btn_prev").attr("href","<c:url value="/" />?date=" + d.yyyymmdd());
		d.setMonth(d.getMonth()+2);
		$("#btn_next").attr("href","<c:url value="/" />?date=" + d.yyyymmdd());
	});

	var calendar = $("#calendar").calendar({
		tmpl_path : "<c:url value="/tmpls/" />",
		day : day,
		events_source : "<c:url value="/json/main" />" + url_param,
		language : 'zh-TW'
	});
</script>