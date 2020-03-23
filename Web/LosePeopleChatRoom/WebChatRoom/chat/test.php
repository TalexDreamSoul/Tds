<?php
echo 'Hello world!';  //输出字符串
$name = 'yss';  //变量名都必须使用$
echo $name;

echo 'Hello '.$name;
$a1 = 5;
//echo $A1;

//include('test3.php');  //将另一个文件加载到当前页面并执行
require('test3.php');

echo 'error';
?>