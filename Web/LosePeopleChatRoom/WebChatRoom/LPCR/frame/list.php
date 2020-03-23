<?php

$mysqli = mysqli_connect('localhost','root','','lpcr');
mysqli_query($mysqli,'set names utf8');
$sql = "SELECT * FROM `groups`";  //查询所有用户
$result = mysqli_query($mysqli,$sql);

$rows = array();  //定义一个空数组用来保存用户数据

while($r = mysqli_fetch_assoc($result)){
	
	// echo (string)$r;
	$rows[] = $r;  //将$r作为一个元素追加到$rows数组最后
	
}
echo json_encode($rows);


// for($rows as $key){
	// echo $key."</br>";
	// echo $key;
	// echo ArrayToString($rows);
// }




?>