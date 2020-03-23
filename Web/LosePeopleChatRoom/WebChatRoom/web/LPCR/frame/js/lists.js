function getLists() {
	
	$.ajax({
		type: "POST",
		url: "list.php",
		data: {"type": "getLists"}, //参数
		success: function(msg){
			
			// console.log(msg);
			var json = eval('(' + msg + ')');
			
			var groups = document.getElementById("groups");
			var html = '&nbsp;可用的聊天小组(' + json.length + '个)';
			groups.innerHTML = html;
			for(var i in json){
				
				var obj = json[i];
				var name = obj["name"];
				var marginTop = 20 + i * 100;

				var sourceNode = document.getElementById("group"); // 获得被克隆的节点对象 

				var clonedNode = sourceNode.cloneNode(true); // 克隆节点 
				clonedNode.setAttribute("id", "group-" + obj["id"]); // 修改一下id 值，避免id 重复 
				sourceNode.parentNode.appendChild(clonedNode); // 在父节点插入克隆的节点 
				clonedNode.style.top = marginTop + "px";
				clonedNode.style.visibility = "visible";
				
				// var gt = document.getElementById("group-" + obj["id"]).getElementsByTagName("h2");
				var gts = document.getElementsByName("gt");
				for(var j = 0;j < gts.length;j++){
					var gt = gts[j];
					// if(gt.parent == clonedNode){
					gt.setAttribute("name","gt-" + obj["id"]);
					gt.innerHTML = name;
					// console.log(gt.innerHTML);
					
					
					// }
				}	


				var avatars = document.getElementsByName("avatar");
				for(var j = 0;j < avatars.length;j++){
					var avatar = avatars[j];
					// if(gt.parent == clonedNode){
					avatar.setAttribute("name","avatar-" + obj["id"]);
					avatar.src = "img/" + obj["id"] + ".jpg";
					// console.log(avatar.src);
					
					
					// }
				}	
				// var gt1 = document.getElementById("gt");
				// var gt2 = document.getElementsByName('***');

				
				
				// for(var i = 0; i < gt1.length; i++){
					// for(var j = 0; j < gt2.length; j++){
						// if(gt1[i].parent == gt2[j].parent){
								// var gt = gt1[i];
								// name = "&nbsp;" + name;
								// gt.innerHTML = "qwq";
								// console.log(gt.innerHTML);
						// }
					// }
				// }
				
				// groupTitle.setAttribute("id","groupTitle-" + obj["id"]);
				
				// var nodeul = document.getElementsByTagName('group')[0];//获取需要复制的UL节点
				// var newGroup = nodeul.cloneNode(true);//true表示深度复制，即边下边的li和文本也一起；如果是false，则只复制ul
				
				// var node_copy = document.getElementById('group');
				// node_copy.appendChild(newGroup);
				
				// var groupObj = new Object();
				// groupObj = document.getElementById("group");
				// newGroup.style.top = marginTop + "px";
				// newGroup.style.visibility = "visible";
				
				// console.log(name + "创建完毕！(" + marginTop + ")" + i);
				// console.log(newGroup.style.top);
				
			}
		}
    });
}