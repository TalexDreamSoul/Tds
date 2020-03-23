	var a=380;//最大距离
	var b;//定时器变量
	var c=1;//下次点击运动方向 -1负向运动 1正向运动
	var d=5; //反弹常量 数值越大弹性越小 取值d>1
	var e=-1; //当前运动方向
	var f=a; //当前位置
	var g=0; //已单向运动时间
	var h; //弹性体
	var i=20;//运动速度 数值越大，运动越慢
	
function bounce(msg){
  

  h=document.getElementById("message");
  
  h.innerHTML = "";
  
  
  h.style.visibility = "visible";
  
  
  
  //终止未完成的运动
  if(b)clearInterval(b);
  //重置时间
  
  g=0;
  
  c=-1*c; //下次点击运动方向改变
  
  b=setInterval('move()',i);
  
    h.innerHTML = msg;
  
  setTimeout( function(){
		
	if(b)clearInterval(b);
	  
	g=0;
	
	c=-1*c; //下次点击运动方向改变
  
	b=setInterval('move()',i);
	
	// h.style.visibility = "hidden";
			
	}, 5 * 1000 );
	
  
}

function move(){
  if(c==1){
    if(e==-1){
     if(f-(2*g-1)>0){
       f=f-(2*g-1);
       g++;
     }else{
       e=1;
       f=1;
       g++;
       g=parseInt(g/d);
       g=g%2==0?(g+1):g;
       if(g==3)clearInterval(b);
     }
    }else{
      if(g>0){
        g--;
        f=f+2*g-1;
      }else{
        e=-1;
        g=0;
      }
    }
    h.style.width=f.toString()+"px";
	h.style.visibility = "hidden";
  }else{
    if(e==1){
     if(f+(2*g-1)<a){
       f=f+(2*g-1);
       g++;
     }else{
       e=-1;
       f=a;
       g++;
       g=parseInt(g/d);
       g=g%2==0?(g+1):g;
       if(g==1)clearInterval(b);
     }
    }else{
      if(g>0){
        g--;
        f=f-(2*g-1);
      }else{
        e=1;
        g=0;
      }
    }
    h.style.width=f.toString()+"px";
	// h.style.visibility = "visible";
  }
}


function run() {
	
	var userInput = document.getElementById("login-userid");
	var user = userInput.value;
    var passInput = document.getElementById("login-passwd");
	var pass = passInput.value;
	
	var popUp = document.getElementById("loader-16");

    disableButton(true);

    var run = document.getElementById("form");
    var m = run.getBoundingClientRect().top;
    m = m - 350;
    run.style.marginTop = '30px';

    popUp.style.visibility = "visible";

    setTimeout( function(){


		if(user == "" || user.length > 10 || user.length < 5){
			tip("验证失败，无法匹配到账户！");
			return;
		}else if(pass == "" || pass.length < 6 || pass.length > 20){
			tip("验证失败，无法匹配到密码！");
			return;
		}
	
	//var regForm = document.getElementById("reg-form");
	
    
		// var url = "http://tds.com/LPCR/login.php"
		// var request = new XMLHttpRequest();
		// request.open("POST", url);
		// request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
		// request.send("username=" + user + "&password=" + pass);
	
		$.ajax({
		type: "POST",
		url: "login.php",
		data: {"username":user , "password":pass}, //参数
		success: function(msg){
			
			
				// console.log(msg);

				tip(msg);
				
				if(msg.includes("Lv")){
				
					setTimeout( function(){
						
						window.location.href="/LPCR/grop.html"
					
					}, 3 * 1000 );//延迟2000毫秒
				}
				// document.location.href = "http://tds.com/LPCR";
				
			}
        });
		
		
		
		// setTimeout( function(){
	
			// $(function(){
				// var  h1 = document.getElementById("h1");
				
				
				
				
				// $.ajax({
				// 'url':'login.php',
				// 'dataType':'json',
				// 'success':function(data){
				// console.log("666");
				// alert(1); 
				// document.write("233");
				
				// console.log(typeof(data));
				// var data2=JSON.parse(data);
				// console.log(typeof(data2));
				// document.write(data);
				// document.write(data2);
				
				//  h1.innerHTML=data2.msg[0].value;
					// }
				// })
			// })
			
			
			// getReturn();
			
		// }, 3 * 1000 );//延迟2000毫秒



    }, 3 * 800 );//延迟2000毫秒


    // if(a === 0){
    //
    //   moveForm(true);
    //     popUp.style.visibility = "visible";
    //
    // }else{
    //
    //     moveForm(false);
    //     popUp.style.visibility = "hidden";
    //
    // }
	

	
}

function tip(msg){

    var run = document.getElementById("form");
    var m = run.getBoundingClientRect().top;
    m = m - 350;
    run.style.marginTop = '-30px';
    var popUp = document.getElementById("loader-16");
    popUp.style.visibility = "hidden";

    bounce(msg);
    disableButton(false);

}


function disableButton(enabled){

	
	var btn = document.getElementById("loginButton");

    if(enabled){
		//$("loginButton").attr({"disabled":"disabled"});
		btn.setAttribute("disabled","true");
   }else{
		//$("#loginButton").removeAttr({"disabled":"disabled"});
		btn.removeAttribute("disabled");
	}
}