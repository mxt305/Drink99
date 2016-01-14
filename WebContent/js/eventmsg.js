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
			content: nl2br(msg.content)
		}
		$.tmpl( $("#event_msg_tmpl"), data ).appendTo( "#msg_block" );
	}
	
	function msg_add(){
		var data = {
			id: evid,
			title: $("#f_title").val(),
			content: $("#f_content").val()
		}
		$.ajax({
			  type: "POST",
			  url: baseurl + "/event/msg/add",
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
	
	function msg_del(){
		
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
	//test
//	(function(){
//		  
//		  $('.floatlabel').SmartPlaceholders();
//		  
//		  var $form = $('#form'),
//		      $success = $('.success'),
//		      $smart = $('.smart-placeholder-wrapper'),
//		      parsley = $form.parsley();
//		  
//		  $form.submit(function(e) {
//		    e.preventDefault();
//		    if (parsley.validationResult == true) {
//		      var name = $('#name').val();
//		      success(name);
//		      clearForm(this); 
//		    }
//		  });
//		  
//		  function success(name) {
//
//		    var text = $('#text');
//		    $('#msglist').append('<li>' + text + '</li>');
//
//		  }
//		  
//		  function clearForm(el) {
//		    el.reset();
//		    $smart.removeClass('focused, populated');
//		  }
//		  
//		})();
//
//
//
//	
});