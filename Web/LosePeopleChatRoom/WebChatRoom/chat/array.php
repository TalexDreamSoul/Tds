<?php
$arr = array(
	'name' => 'yss',
	'sex' => '女',
	'age' => 18
);
$arr = 1;
print_r($arr);

$arr2 = array(0=>1,1=>2,2=>3,3=>4);
print_r($arr2);

$arr3 = array('color'=>'red',2,6=>7);
print_r($arr3);

//数组元素访问：下标（键）
echo $arr['name'];

if(is_array($arr)){  //判断变量是否是数组
	foreach($arr as $v){
		//echo $arr[$k];//通过下标访问元素
		echo $v;
	}
}

if(in_array(7,$arr2)){
	echo '存在';
}else{
	echo '不存在';
}
?>