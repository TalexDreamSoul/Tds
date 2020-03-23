var name = "";

var systemBubbles = 0;
var defaultBubbles = 0;
var canSend = false;

var key='all',mkey;
var users={};
var url='ws://tds.com:8000';
var so=false,n=false;
    // var lus=A.$('us'),lct=A.$('ct');
function st(){
        n=name;
        n=n.substr(0,16);
        if(!n){
            return ;   
        }
        //创建socket，注意URL的格式：ws://ip:端口
        so=new WebSocket(url);
        //握手监听函数
        so.onopen=function(){
            //状态为1证明握手成功，然后把client自定义的名字发送过去
            if(so.readyState==1){
                so.send('type=add&ming='+n);
            }
        }
         
        //握手失败或者其他原因连接socket失败，则清除so对象并做相应提示操作
        so.onclose=function(){
            so=false;
            // lct.appendChild(A.$$('<p class="c2">退出聊天室</p>'));
			
			addSystemBubble("你已退出聊天室","red");
			canSend = false;
			return;
        }
         
		 
		canSend = true;
		
        //数据接收监听，接收服务器推送过来的信息，返回的数据给msg，然后进行显示
        so.onmessage=function(msg){
            eval('var da='+msg.data);
            var obj=false,c=false;
            if(da.type=='add'){
                addSystemBubble('</span style="color:blue">' + da.name + '</span>' + '加入了！',"grey");
                // obj=A.$$('<p><span>['+da.time+']</span>欢迎<a>'+da.name+'</a>加入</p>');
                
             }//else if(da.type=='madd'){
                // mkey=da.code;
                // da.users.unshift({'code':'all','name':'大家'});
                // for(var i=0;i<da.users.length;i++){
                    // var obj=A.$$('<p>'+da.users[i].name+'</p>');
                    // lus.appendChild(obj);
                    // if(mkey!=da.users[i].code){
                        // cuser(obj,da.users[i].code);
                    // }else{
                        // obj.className='my';
                        // document.title=da.users[i].name;
                    // }
                // }
                // obj=A.$$('<p><span>['+da.time+']</span>欢迎'+da.name+'加入</p>');
                // users.all.className='ck';
            // }
             
            if(obj==false){
                if(da.type=='rmove'){
					addSystemBubble('</span style="color:blue">' + da.nrong + '</span>' + '退出了！',"grey");
                    // var obj=A.$$('<p class="c2"><span>['+da.time+']</span>'+users[da.nrong].innerHTML+'退出聊天室</p>');
                    // lct.appendChild(obj);
                    // users[da.nrong].del();
                    // delete users[da.nrong];
                }else{
                    // da.nrong=da.nrong.replace(/{\\(\d+)}/g,function(a,b){
                        // return '<img src="sk/'+b+'.gif">';
                    // }).replace(/^data\:image\/png;base64\,.{50,}$/i,function(a){
                        // return '<img src="'+a+'">';
                    // });
                    //da.code 发信息人的code
                    if(da.code1==mkey){
                        obj=A.$$('<p class="c3"><span>['+da.time+']</span><a>'+users[da.code].innerHTML+'</a>对我说：'+da.nrong+'</p>');
                        c=da.code;
                    }else if(da.code==mkey){
                        if(da.code1!='all')
                        obj=A.$$('<p class="c3"><span>['+da.time+']</span>我对<a>'+users[da.code1].innerHTML+'</a>说：'+da.nrong+'</p>');
                        else
                        obj=A.$$('<p><span>['+da.time+']</span>我对<a>'+users[da.code1].innerHTML+'</a>说：'+da.nrong+'</p>');
                        c=da.code1;
                    }else if(da.code==false){
                        obj=A.$$('<p><span>['+da.time+']</span>'+da.nrong+'</p>');
                    }else if(da.code1){
                        obj=A.$$('<p><span>['+da.time+']</span><a>'+users[da.code].innerHTML+'</a>对'+users[da.code1].innerHTML+'说：'+da.nrong+'</p>');
                        c=da.code;
                    }
                }
            }
            if(c){
                    obj.children[1].onclick=function(){
                        users[c].onclick();
                    }
                }
            lct.appendChild(obj);
            lct.scrollTop=Math.max(0,lct.scrollHeight-lct.offsetHeight);
             
        }
}

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

function install() {
	
	$.ajax({
		type: "POST",
		url: "title.php",
		data: {"type": "getLists"}, //参数
		success: function(msg){
			
			
				if(msg != "unlogin"){
				
				// alert(msg["username"]);
				addSystemBubble('<span style="color:blue">' + msg["username"] + '</span>' + '加入了！',"white");
				
				name = msg["username"];
				st();
				
				
				}

		}
    });
	
	
	$.ajax({
		type: "POST",
		url: "socket.php",
		data: {"type": "getMembers"}, //参数
		success: function(msg){
			
			
			console.log("233");
			
				
		}
    });
	
	

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