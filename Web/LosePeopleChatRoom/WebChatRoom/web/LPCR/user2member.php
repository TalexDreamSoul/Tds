<?php
//检查

echo "准备开始转存...\n";

header('Content-Type:text/json;charset=utf-8');
	
	echo "连接Mysql数据库 ...\n";
	
	$mysqli = mysqli_connect('localhost:3306','root','','lpcr');  //登录数据库
	
	echo "正在设置编码...\n";
	
	mysqli_query($mysqli,'set names utf8');  //防止乱码
		
	echo "取出所有数据 ...\n";

	$sql = 'SELECT * FROM users; ';
	
	$result = mysqli_query($mysqli,$sql);  //执行查询
	
	echo "查询完毕！";
	
	if(empty($result)){
		
		echo "错误,没有任何数据！";
		return;
	}
	
	$r = mysqli_fetch_assoc($result);  //将结果集转换为数组
	
	echo "准备转存数据至 lqcr/managers (001 默认聊天室)";	
	
	// $raw_json = array();
	$raw_array = array();
	
	print_r($result);
	// echo count($result);
	echo "\n";
	
	print_r($r);
	echo count($r);
	echo "\n";
	
	for($i = 0;$i < count($r);$i++){
	
		echo "正在转存 " . $i . "...\n";
		
		echo "正在生成json数据";
		$raw_json = array($r["userid"]=>array(
			"name"=>$r["username"],
			"identity"=>"member",
			"laastmsg"=>time(),
			"msgs"=>0,
			"jointime"=>time(),
			"online"=>false
		
		));
		echo "Json创建完毕";
		echo "正在加入全局 ...\n";
		// $s = array(",");
		array_push($raw_array,$raw_json);
		// $raw_array = array($raw_array . $raw_json);
		
	}
	echo "生成全局Array结束,生成Json";
	
	$res_return = json_encode($raw_array);
	
	echo "正在生成插入命令 ...\n";
	$cmd = "INSERT INTO `members`(`groupid`, `members`) VALUES (1," . $res_return . ")";
	echo "生成完毕,即将执行！";
	
	$res_result = mysqli_query($mysqli,$cmd);  //执行插入
	
	echo "插入完毕: " . $res_result . "!";

?>