$(function(){
	var base_url = BASE_URL;
	var user_id = USER_ID;
	load_list();
	function load_list(){
		$("#cur_ev").text("");
		$("#his_ev").text("");
		$.getJSON(base_url + "/user/event?id=" + user_id + "&s=1", function(data){
			$.each(data,function(i, v){
				push_ev(i, v, "#cur_ev");
			});
		});
		$.getJSON(base_url + "/user/event?id=" + user_id + "&s=2", function(data){
			$.each(data,function(i, v){
				push_ev(i, v, "#his_ev");
			});
		});
	}
	function push_ev(index, ev, tbody){
		var data ={
			index: index + 1,
			id: ev.id,
			title: ev.name,
			mem: ev.enterpriser.name,
			memID: ev.enterpriser.id,
			date: ev.date,
		}
		$.tmpl( $("#user_event_tmpl"), data ).appendTo( tbody );
	}
});