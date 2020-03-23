<?php
session_start();
$mysqli = mysqli_connect('localhost','root','','webchat');	
mysqli_query($mysqli,'set names utf8');
$sql = "SELECT `user`.`nickname`,`user`.`profile`,`chat`.* FROM `chat` INNER JOIN `user` ON `chat`.`userid`=`user`.`id` ORDER BY `chat`.`systime` ASC";
$result = mysqli_query($mysqli,$sql);
$rows = array();
while($r = mysqli_fetch_assoc($result)){
	if($_SESSION['userid'] == $r['userid']){  //如果消息列表的userid与登录信息中的uesrid一致，则表示是当前用户自己发送的消息
		$r['is_mine'] = 1;
	}else{
		$r['is_mine'] = 0;
	}
	$rows[] = $r;
}
//判断是否是ajax请求
if(!empty($_SERVER['HTTP_X_REQUESTED_WITH'])){
	echo json_encode($rows);
}else{
	include('frame/content.html');
}
?>