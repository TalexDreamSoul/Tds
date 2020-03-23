<?php
/*PHP连接数据库*/
//1.连接到服务器，并选择数据库:mysqli_connect('服务器地址','用户名','密码','数据库名称')
$mysqli = mysqli_connect('localhost','root','','webchat');
//2.设置传输编码：防止页面乱码 mysqli_query()
mysqli_query($mysqli,'set names utf8');
//3.写SQL语句
$sql = "SELECT * FROM `user`";  //查询user表的所有内容
//4.执行SQL语句:mysqli_query(连接标识,SQL)
$result = mysqli_query($mysqli,$sql); //返回一个结果集
//5.将结果集转换数组
$r = mysqli_fetch_assoc($result);

print_r($r);
?>