<?php
session_start();
if(!empty($_POST['content'])){
	$userid = $_SESSION['userid'];
	$content = $_POST['content']; //获取用户输入内容
	$systime = time();  //时间戳
	$mysqli = mysqli_connect('localhost','root','','webchat');	
	mysqli_query($mysqli,'set names utf8');
	$sql = "INSERT INTO `chat`(`userid`,`content`,`systime`) VALUES('$userid','$content','$systime')";
	mysqli_query($mysqli,$sql);  //执行插入数据库操作
	if(mysqli_affected_rows($mysqli)  && !empty($_SERVER['HTTP_X_REQUESTED_WITH'])){
		echo 1;
		exit;
	}
}
include('frame/send.html');
?>