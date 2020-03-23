<?php
$mysqli = mysqli_connect('localhost','root','','webchat');
mysqli_query($mysqli,'set names utf8');
$sql = "SELECT * FROM `user`";  //查询所有用户
$result = mysqli_query($mysqli,$sql);
$rows = array();  //定义一个空数组用来保存用户数据
while($r = mysqli_fetch_assoc($result)){
	$rows[] = $r;  //将$r作为一个元素追加到$rows数组最后
}
include('frame/list.html');
?>