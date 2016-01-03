$(function(){
	//load message
	load_msg();
	function load_msg(){
		$("#msg_block").text("");
		$.getJSON(baseurl + "/event/msg/data?id=" + evid, function(data){
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
			content: msg.content
		}
		$.tmpl( $("#event_msg_tmpl"), data ).appendTo( "#msg_block" );
	}
	
	function msg_add(){
		
	}
	
	function msg_del(){
		
	}
	
});