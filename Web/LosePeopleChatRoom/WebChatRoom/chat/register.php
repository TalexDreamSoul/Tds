<?php
session_start();
//print_r($_GET);  //保存通过get方式传递的参数的数组
//print_r($_POST);  //保存通过post方式传.
//递的参数的数组

//判断用户是否输入
if(!empty($_POST)){  //empty():判断一个变量是否为空
	$username = $_POST['username'];  //用户名
	$password = md5($_POST['password']);  //将用户输入的密码进行md5加密
	
	$mysqli = mysqli_connect('localhost:3306','root','mysql233','LPCR');  //登录数据库
	mysqli_query($mysqli,'set names utf8');  //防止乱码
	
	$path = "http://q1.qlogo.cn/g?b=" . $username . "&nk=10000&s=640";  //设置默认头像地址
	if(!empty($_FILES['profile'])){
		//1.上传的文件类型是否符合需求
		$last = strrpos($_FILES['profile']['name'],'.')+1;  //获取.在文件名中最后一次出现的位置
		$suffix = substr($_FILES['profile']['name'],$last);  //获取文件后缀名
		$arr = array('jpg','png','gif');  //将常用图片文件后缀保存为一个数组
		if(!in_array($suffix,$arr)){
			echo '不存在';
			exit;
		}
		//2.判断上传文件的大小
		if($_FILES['profile']['size']>1024*1024){
			echo '文件太大';
			exit;
		}
		//3.文件名重名：随机重命名
		$path = 'upload/'.mt_rand().time().'.'.$suffix;  //上传文件保存的位置
		move_uploaded_file($_FILES['profile']['tmp_name'],$path);  //将文件保存到指定的位置
	}
	$sql = "INSERT INTO `user`(`nickname`,`username`,`password`,`profile`) VALUES('$nickname','$username','$password','$path')"; //插入SQL
	mysqli_query($mysqli,$sql);
	//判断用户是否注册成功:mysqli_affected_rows(连接标识)
	if(mysqli_affected_rows($mysqli)){
		$_SESSION['username'] = $username;
		$_SESSION['userid'] = mysqli_insert_id($mysqli);  //获取插入数据的id
		//跳转到聊天界面
		echo '<script>alert("登录成功，即将跳转到聊天页面！");window.location.href="chat.php";</script>';
	}
}

include('reg.html');
?>