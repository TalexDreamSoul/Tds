var name = "";

var systemBubbles = 0;
var defaultBubbles = 0;
var canSend = false;

var ws;
function install() {
	
	// $.ajax({
		// type: "POST",
		// url: "socket.php",
		// data: {"type": "getMembers"}, //参数
		// success: function(msg){
			
			
			// console.log("233");
			
				
		// }
    // });
	
	$.ajax({
		type: "POST",
		url: "title.php",
		data: {"type": "getLists"}, //参数
		success: function(msg){
			
			
				if(msg != "unlogin"){
				
				// alert(msg["username"]);
				addSystemBubble('<span style="color: rgb(0, 153, 221) ">' + msg["username"] + '</span>' + '加入了！',"white");
				
				name = msg["username"];
				// ToggleConnectionClicked();
				ws = new WebSocket("ws://df2020.top:8000");
				
				}

		}
    });
	
	
	

}


//握手监听函数

ws.onopen=function(){

     //状态为1证明握手成功，然后把client自定义的名字发送过去

    if(so.readyState==1){

         //握手成功后对服务器发送信息

     so.send('type=add&ming='+n);

    }

}

//错误返回信息函数

ws.onerror = function(){

    console.log("error");

};

//监听服务器端推送的消息

ws.onmessage = function (msg){

    console.log(msg);

}

 

//断开WebSocket连接

ws.onclose = function(){

    ws = false;

}

// function ToggleConnectionClicked() {          
	// try {
		// ws = new WebSocket("ws://df2020.top:8000/LPCR/frame/socket.php");//连接服务器        
		// ws.onopen = function(event){alert("已经与服务器建立了连接\r\n当前连接状态："+this.readyState);};
		// ws.onmessage = function(event){alert("接收到服务器发送的数据：\r\n"+event.data);};
		// ws.onclose = function(event){alert("已经与服务器断开连接\r\n当前连接状态："+this.readyState);};
		// ws.onerror = function(event){alert("WebSocket异常！");};
		// } catch (ex) {
			// alert(ex.message);      
		// }
// }
// function SendData() {
	// try{
		// var content = document.getElementById("content").value;
		// if(content){
		// ws.send(content);
		// }
	// }catch(ex){
		// alert(ex.message);
	// }
// }
// function seestate(){
	// alert(ws.readyState);
// }

function addSystemBubble(msg,color){
	
	var sourceNode = document.getElementById("systemBubble");
			
			systemBubbles++;
			
			var clonedNode = sourceNode.cloneNode(true); // 克隆节点 
			clonedNode.setAttribute("id", "sb-" + systemBubbles); // 修改一下id 值，避免id 重复 
			sourceNode.parentNode.appendChild(clonedNode); // 在父节点插入克隆的节点 
			
			var mt = 30 + systemBubbles * 40;
			
			clonedNode.style.top = mt + "px";
			clonedNode.style.marginTop = mt + "px auto";
			clonedNode.style.visibility = "visible";
			
			clonedNode.innerHTML = '<span style="color:' + color + '">' + msg + '</span>';
	
	
}

function addTargetBubble(qq,msg){
	// return;
	var sourceNode = document.getElementById("targetBubble");
			
			var clonedNode = sourceNode.cloneNode(true); // 克隆节点 
			// clonedNode.setAttribute("id", "targetBubble-" + obj["id"]); // 修改一下id 值，避免id 重复 
			sourceNode.parentNode.appendChild(clonedNode); // 在父节点插入克隆的节点 
			clonedNode.style.marginTop = "60px auto";
			clonedNode.style.visibility = "visible";
			
			clonedNode.innerHTML = msg;
	
	
}

function line2br(text){
	
	return text.split("\n").join("<br />");
	
}

function addSenderBubble(msg){
	
	var sourceNode = document.getElementById("sBubble");
			
			var clonedNode = sourceNode.cloneNode(true); // 克隆节点 
			// clonedNode.setAttribute("id", "targetBubble-" + obj["id"]); // 修改一下id 值，避免id 重复 
			sourceNode.parentNode.appendChild(clonedNode); // 在父节点插入克隆的节点 
			sourceNode.parentNode.scrollTop = sourceNode.parentNode.scrollHeight;
			defaultBubbles++;
			var mt = defaultBubbles * 120; 
			clonedNode.style.marginTop = mt + "px auto";
			clonedNode.style.top = mt + "px";
			
			clonedNode.style.visibility = "visible";
			clonedNode.style.position = "absolute";
			
			var html = "";
			
			msg = line2br(msg);
			
			//头像
			html = '<img class="imgBubble" id="imgBubble" src="http://q1.qlogo.cn/g?b=qq&nk=2418876761&s=640" height="100%" width="100%" style="float:right;border:orange solid 2px;display:block-inline;margin:34px auto;left:1050px;position:relative;width:85px;height:85px"/>';
			
			//文本
			html = html + '<div class="rightBubble" id="qp2" style="background:orange"><span style="color:white;margin: 10px auto">' + msg + '</span>';
			
			//头衔
			html = html + '<div style="left:0px;top: -45%;font-size:15px;color:grey;position:absolute"><span style="color:#FF9900;font-weight:bold">[本群群主]</span>' + name + '</div></div>'
			
			clonedNode.innerHTML = html;
	
	
}


var textarea={
  'shift': false,
  'enable' : false,
  'ctrl' : false
}

function bind(event){
	
	if(event.keyCode == 17){
	
		textarea.ctrl = true;
		
	}
	if(event.keyCode == 13 && textarea.ctrl == true){
		
		if(canSend == false){
			
		addSystemBubble("没有加入聊天室无法聊天!","red")
		return;
		}
		var send = document.getElementById("text");
		// alert("你发送了这样的消息:" + send.value);
		addSenderBubble(send.value);
		send.value = "";
		send.focus();
		
		textarea.ctrl = false;
		
		return;
	}
	
	// console.log(event.keyCode);
	
	return;
	// if(textarea.enable == true){
}			
		
	// }
	// if(event.keyCode == 32) {
		
		// var text = document.getElementById("text");
		// console.log(text.innerHTML);
		
		// var msg = text.value;
		
	
		// res=getValue( "@"," ", msg ); // return true
		// console.log(res);
		// var atText = getSub(msg,"@");
		// text.value = "";
		// text.innerHTML = msg + '<span style="color:blue">' + atText + '</span>';
		// console.log(atText);
		// text.value= msg + '<span style="color:blue">' + atText + '</span>';
		
		
		
		// textarea.shift=false; 
		// textarea.enable = false
	// }
	// if (event.keyCode == 16) {textarea.shift=true;}
	// else if (event.keyCode == 229 && textarea.shift){//TODO @
		
		// textarea.enable = true;
		// console.log("true");
		// textarea.shift = false;
	// }
	



function getSub (obj,str){
    let index = obj.lastIndexOf(str);
    obj = obj.substring(index+1, obj.length)
    return obj
}

function getValue( key1,key2, str ) { 
     
     var m = str.match( new RegExp(key1+'(.*?)'+key2) );
     
     return m ? m[ 1 ] : false;
 }


    // A.$('sd').onclick=function(){
        // if(!so){
             // return st();
        // }
        // var da=A.$('nrong').value.trim();
        // if(da==''){
            // alert('内容不能为空');
            // return false;  
        // }
        // A.$('nrong').value='';
        // so.send('nr='+esc(da)+'&key='+key);
    // }
    // A.$('nrong').onkeydown=function(e){
        // var e=e||event;
        // if(e.keyCode==13){
            // A.$('sd').onclick();
        // }
    // }
    // function esc(da){
        // da=da.replace(/</g,'<').replace(/>/g,'>').replace(/\"/g,'"');
        // return encodeURIComponent(da);
    // }
    // function cuser(t,code){
        // users[code]=t;
        // t.onclick=function(){
            // t.parentNode.children.rcss('ck','');
            // t.rcss('','ck');
            // key=code;
        // }
    // }
    
	// A.$('ltian').style.height=(document.documentElement.clientHeight - 70)+'px';
    // st();
     
 
    // var bq=A.$('imgbq'),ems=A.$('ems');
    // var l=80,r=4,c=5,s=0,p=Math.ceil(l/(r*c));
    // var pt='sk/';
    // bq.onclick=function(e){
        // var e=e||event;
        // if(!so){
             // return st();
        // }
        // ems.style.display='block';
        // document.onclick=function(){
            // gb();  
        // }
        // ct();
        // try{e.stopPropagation();}catch(o){}
    // }
     
    // for(var i=0;i<p;i++){
        // var a=A.$$('<a href="javascript:;">'+(i+1)+'</a>');
        // ems.children[1].appendChild(a);
        // ef(a,i);
    // }
    // ems.children[1].children[0].className='ck';
     
    // function ct(){
        // var wz=bq.weiz();
        // with(ems.style){
            // top=wz.y-242+'px';
            // left=wz.x+bq.offsetWidth-235+'px';
        // }
    // }
         
    // function ef(t,i){
        // t.onclick=function(e){
            // var e=e||event;
            // s=i*r*c;
            // ems.children[0].innerHTML='';
            // hh();
            // this.parentNode.children.rcss('ck','');
            // this.rcss('','ck');
            // try{e.stopPropagation();}catch(o){}
        // }
    // }
     
    // function hh(){
        // var z=Math.min(l,s+r*c);
        // for(var i=s;i<z;i++){
            // var a=A.$$('<img src="'+pt+i+'.gif">');
            // hh1(a,i);
            // ems.children[0].appendChild(a);
        // }
        // ct();
    // }
     
    // function hh1(t,i){
        // t.onclick=function(e){
            // var e=e||event;
            // A.$('nrong').value+='{\\'+i+'}';
            // if(!e.ctrlKey){
                // gb();
            // }
            // try{e.stopPropagation();}catch(o){}
        // }
    // }
     
    // function gb(){
        // ems.style.display='';
        // A.$('nrong').focus();
        // document.onclick='';
    // }
    // hh();
    // A.on(window,'resize',function(){
        // A.$('ltian').style.height=(document.documentElement.clientHeight - 70)+'px';
        // ct();
    // }) 
 
    // var fimg=A.$('upimg');
    // var img=new Image();
    // var dw=400,dh=300;
    // A.on(fimg,'change',function(ev){
        // if(!so){
            // st();
            // return false;
        // }
        // if(key=='all'){
            // alert('由于资源限制 发图只能私聊');
            // return false;  
        // }
        // var f=ev.target.files[0];
        // if(f.type.match('image.*')){
            // var r = new FileReader();
            // r.onload = function(e){
                // img.setAttribute('src',e.target.result);
            // };
            // r.readAsDataURL(f);
        // }
    // });
    // img.onload=function(){
        // ih=img.height,iw=img.width;
        // if(iw/ih > dw/dh && iw > dw){
            // ih=ih/iw*dw;
            // iw=dw;
        // }else if(ih > dh){
            // iw=iw/ih*dh;
            // ih=dh;
        // }
        // var rc = A.$$('canvas');
        // var ct = rc.getContext('2d');
        // rc.width=iw;
        // rc.height=ih;
        // ct.drawImage(img,0,0,iw,ih);
        // var da=rc.toDataURL();
        // so.send('nr='+esc(da)+'&key='+key);