$(function(){
	load_mem();
	load_btn(user_status);
	function load_mem(){
		$("#b_mem0").text("");
		$("#b_mem1").text("");
		$.getJSON(baseurl + "/event/join/data?ev=" + evid, function(data){
			var inv = data.invitees;
			$.each(inv,function(i, v){
				$.tmpl( $("#event_join_tmpl"), v ).appendTo( "#b_mem0" );
			});
			var part = data.participants;
			$.each(part,function(i, v){
				$.tmpl( $("#event_join_tmpl"), v ).appendTo( "#b_mem1" );
			});
		});
	}
	function join_event(act, status){
		$.ajax({
			  type: "POST",
			  url: baseurl + "/event/join/" + act,
			  data: {ev: evid},
			  dataType: "json"
		}).done(function(data){
			  if(data.success == 1){		  
				  $.tmpl( $("#alert_tmpl"),{title:"訊息", type:"success", msg:data.msg}).appendTo( "#alert_area" );
			  } else {
				  $.tmpl( $("#alert_tmpl"),{title:"錯誤", type:"danger", msg:data.error}).appendTo( "#alert_area" );
			  }
			  load_mem();
			  load_btn(status);
		});
	}
	function load_btn(status){
		switch(status){
		case 0:
			$("#btn_optout").prop( "disabled", false );
			$("#btn_cancel").prop( "disabled", false );
			$("#btn_join").prop( "disabled", false );
			break;
		case 1:
			$("#btn_optout").prop( "disabled", false );
			$("#btn_cancel").prop( "disabled", false );
			$("#btn_join").prop( "disabled", true );
			break;
		case 2:
			$("#btn_optout").prop( "disabled", true );
			$("#btn_cancel").prop( "disabled", false );
			$("#btn_join").prop( "disabled", false );
			break;
		default:
			$("#btn_join").removeAttr('disabled');
			$("#btn_optout").prop( "disabled", true );
			$("#btn_cancel").prop( "disabled", true );
			break;
		}
	}
	$("#btn_join").click(function(){
		join_event("join", 1);
	});
	$("#btn_optout").click(function(){
		join_event("opt_out", 2);
	});
	$("#btn_cancel").click(function(){
		join_event("cancel", -1);
	});
});