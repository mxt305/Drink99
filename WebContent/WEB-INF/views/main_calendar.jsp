<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header">

	<div class="pull-right form-inline">
		<div class="btn-group">
			<div class="btn-group">
				<button class="btn btn-primary" data-calendar-nav="prev">&lt;&lt; Prev</button>
				<button class="btn" data-calendar-nav="today">Today</button>
				<button class="btn btn-primary" data-calendar-nav="next">Next &gt;&gt;</button>
			</div>
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
		}
	}

	if (day == "now") {
		d = new Date();
	}

	$(function() {
		/*d.setDate(1);
		$("#cal_title").text(d.getFullYear() + "年" + (d.getMonth() + 1) + "月");
		d.setMonth(d.getMonth()-1);
		$("#btn_prev").attr("href","<c:url value="/" />?date=" + d.yyyymmdd());
		d.setMonth(d.getMonth()+2);
		$("#btn_next").attr("href","<c:url value="/" />?date=" + d.yyyymmdd());*/
		$('.btn-group button[data-calendar-nav]').each(function() {
			var $this = $(this);
			$this.click(function() {
				calendar.navigate($this.data('calendar-nav'));
			});
		});

		$('.btn-group button[data-calendar-view]').each(function() {
			var $this = $(this);
			$this.click(function() {
				calendar.view($this.data('calendar-view'));
			});
		});
	});

	var calendar = $("#calendar").calendar({
		tmpl_path : "<c:url value="/tmpls/" />",
		day : day,
		events_source : "<c:url value="/json/main" />",
		language : 'zh-TW',
		onAfterViewLoad: function(view) {
			$('#cal_title').text(this.getTitle());
			$('.btn-group button').removeClass('active');
			$('button[data-calendar-view="' + view + '"]').addClass('active');
		},
	});
</script>