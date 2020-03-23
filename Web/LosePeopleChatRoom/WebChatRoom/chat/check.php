<?php
/*验证用户名是否重复*/
$username = $_POST['username'];  //获取传递的参数
$mysqli = mysqli_connect('localhost','root','','webchat');  //登录数据库
mysqli_query($mysqli,'set names utf8');  //防止乱码
$sql = "SELECT * FROM `user` WHERE `username`='$username'";  //SQL
$result = mysqli_query($mysqli,$sql);  //执行SQL
$r = mysqli_fetch_assoc($result);  //将结果转换为数组
if(!empty($_SERVER['HTTP_X_REQUESTED_WITH']) /*&& empty($r)*/){
	echo 1;
}else{
	echo 0;
}
?>