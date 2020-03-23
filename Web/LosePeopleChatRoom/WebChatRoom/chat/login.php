<?php
session_start();  //开启session
//用户登录页面
if(!empty($_POST)){
	$username = $_POST['username'];  //用户名
	$password = md5($_POST['password']);  //密码
	$mysqli = mysqli_connect('localhost','root','','webchat');  //连接数据库
	mysqli_query($mysqli,'set names utf8');  //设置传输编码
	$sql = "SELECT * FROM `user` WHERE `username`='$username' AND `password`='$password'";  //验证SQL
	$result = mysqli_query($mysqli,$sql);  //执行查询
	$r = mysqli_fetch_assoc($result);  //将结果集转换为数组
	if(!empty($r)){
		$_SESSION['username'] = $r['username'];  //将用户名保存到session，用作验证用户是否登录
		$_SESSION['userid'] = $r['id'];
		echo '<script>alert("登录成功，即将跳转！");window.location.href="chat.php";</script>';
	}
}
include('login.html'); //加载用户登录视图
?>