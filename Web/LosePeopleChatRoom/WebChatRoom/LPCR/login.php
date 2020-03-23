
<?php
//print_r($_GET);  //保存通过get方式传递的参数的数组
//print_r($_POST);  //保存通过post方式传.
//递的参数的数组

//判断用户是否输入
if(!empty($_POST)){  //empty():判断一个变量是否为空

	$username = $_POST['username'];  //用户名
	$password = md5($_POST['password']);  //将用户输入的密码进行md5加密
	
	$mysqli = mysqli_connect('localhost:3306','root','','lpcr');  //登录数据库
	
	mysqli_query($mysqli,'set names utf8');  //防止乱码
	
	session_start();  //开启session
	
	
	//用户登录页面

	$sql = "SELECT * FROM `users` WHERE `userid`='$username' AND `password`='$password'";  //验证SQL
	$result = mysqli_query($mysqli,$sql);  //执行查询
	$r = mysqli_fetch_assoc($result);  //将结果集转换为数组
	
	// echo($r['username']);
	// echo($r['userid']);
	// echo($r['password']);
	// echo($r['level']);
	
	// echo "alert('我弹出来了');";
	
	if(!empty($r)){
		
		$_SESSION['username'] = $r['username'];  //将用户名保存到session，用作验证用户是否登录
		$_SESSION['userid'] = $r['userid'];
		$_SESSION['level'] = $r['level'];
		$_SESSION['permission'] = $r['permission'];
		$_SESSION['exp'] = $r['exp'];
		
		// session_
		// $_SESSION['back'] = $r['true'];
		
		echo '登录成功,欢迎 [Lv.' . $_SESSION['level'] . "]" . $_SESSION['username'] . "!";
		
		// exit();
		return;
	}else{
		
		$sql = "SELECT * FROM `users` WHERE `userid`='$username'";  //验证SQL
		$result = mysqli_query($mysqli,$sql);  //执行查询
		$r = mysqli_fetch_assoc($result);  //将结果集转换为数组
		
		if(!empty($r)){
		
			echo "错误的密码！";
			
		}else{
			echo "无法找到用户 " . $username;
		}	
			
		return;
		
	}
}

// include('index.html');

?>