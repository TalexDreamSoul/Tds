<?php
//聊天页面
session_start();
if(empty($_SESSION['username'])){
	echo '<script>alert("请登录！");window.location.href="login.php"</script>';
}
include('grop.html');
?>