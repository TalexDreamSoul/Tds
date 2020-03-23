function tc(){
	if(!confirm("退出后你必须要重新登录,确定吗？")){
			return;
	}
	$.ajax({
			type: "POST",
			url: "exit.php",
			data: {"type" : "exit"}, //参数
			success: function(msg){
				
				window.location.href="/LPCR/login.html";
				
			}
	});
  }