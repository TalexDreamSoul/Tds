<?php
//检查

session_start();

if(empty($_POST)){

	echo "233";
	// return;
}

if(empty($_SESSION['username']) /*|| empty($_SESSION['level']) || empty($_SESSION['username'])*/){
	
	// console.log($_SESSION);
	echo 'unlogin';
	
}else{
	
	header('Content-Type:text/json;charset=utf-8');
	
	// include("refreshSession.html");
	
	$username = $_SESSION['username'];  //用户名
	$userid = $_SESSION['userid'];  //将用户输入的密码进行md5加密
	
	$mysqli = mysqli_connect('localhost:3306','root','','lpcr');  //登录数据库
	
	mysqli_query($mysqli,'set names utf8');  //防止乱码
		
	
	//用户登录页面

	$sql = 'SELECT * FROM `users` WHERE `userid`="' . $userid . '" AND `username`="' . $username . '"';  //验证SQL
	
	$result = mysqli_query($mysqli,$sql);  //执行查询
	
	
	if(empty($result)){
		
		echo false;
		return;
	}
	
		$r = mysqli_fetch_assoc($result);  //将结果集转换为数组
	
		
	$_SESSION['username'] = $r['username'];  //将用户名保存到session，用作验证用户是否登录
	$_SESSION['userid'] = $r['userid'];
	$_SESSION['level'] = $r['level'];
	$_SESSION['permission'] = $r['permission'];
	$_SESSION['exp'] = $r['exp'];
	
	$raw_return = array('userid' => $_SESSION['userid'] , 'username' => $_SESSION['username'] , 'level' => $_SESSION['level'] , 'permission' => $_SESSION['permission'] , 'exp' => $_SESSION['exp']);
	
	$res_return = json_encode($raw_return);
	
	
	echo $res_return;
	
}
// session_destroy();
// include('index.html');
?>