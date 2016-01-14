$(function(){
	var ev_id = EV_ID;
	var base_url = BASE_URL;
	var user_id = USER_ID;
	//load message
	load_msg();
	function load_msg(){
		$("#msg_block").text("");
		$.getJSON(base_url + "/event/msg/data?id=" + ev_id, function(data){
			$.each(data,function(i, v){
				push_msg(i, v);
			});
		});
	}
	function push_msg(index, msg){
		var data ={
			index: index + 1,
			title: msg.title,
			mem: msg.author.name,
			time: msg.time,
			content: nl2br(msg.content),
			isAuth: (msg.author.id == user_id),
			msgID: msg.id
		}
		$.tmpl( $("#event_msg_tmpl"), data ).appendTo( "#msg_block" );
		$("#btn_del" + msg.id).click(function(){
			msg_del(msg.id);
		});
	}
	
	function msg_add(){
		var data = {
			id: ev_id,
			title: $("#f_title").val(),
			content: $("#f_content").val()
		}
		$.ajax({
			  type: "POST",
			  url: base_url + "/event/msg/add",
			  data: data,
			  dataType: "json"
		}).done(function(data){
			  if(data.success == 1){		  
				  $.tmpl( $("#alert_tmpl"),{title:"訊息", type:"success", msg:data.msg}).appendTo( "#alert_area" );
				  reset_form();
			  } else {
				  $.tmpl( $("#alert_tmpl"),{title:"錯誤", type:"danger", msg:data.error}).appendTo( "#alert_area" );
			  }
			  load_msg()
		});
	}
	
	function reset_form(){
		$("#f_title").val("");
		$("#f_content").val("");
	}
	
	function msg_del(id){
		if (confirm('確定刪除此留言，一旦刪除無法復原')) {
			$.ajax({
				  type: "POST",
				  url: base_url + "/event/msg/delete",
				  data: {msgid:id, id: ev_id},
				  dataType: "json"
			}).done(function(data){
				  if(data.success == 1){		  
					  $.tmpl( $("#alert_tmpl"),{title:"訊息", type:"success", msg:data.msg}).appendTo( "#alert_area" );
					  reset_form();
				  } else {
					  $.tmpl( $("#alert_tmpl"),{title:"錯誤", type:"danger", msg:data.error}).appendTo( "#alert_area" );
				  }
				  load_msg()
			});
		} else {
			
		}
	}
	
	$("#btn_submit").click(function(){
		msg_add();
	});
	
	$("#btn_msgcln").click(function(){
		reset_form();
	});
	
	function nl2br( str ) {
		return str.replace(/([^>])\n/g, '$1<br/>\n');
	} 
});