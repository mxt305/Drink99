$(function(){
	load_mem();
	function load_mem(){
		$("#b_mem0").text("");
		$("#b_mem1").text("");
		$.getJSON(baseurl + "/event/join/data?id=" + evid, function(data){
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
	
	
});