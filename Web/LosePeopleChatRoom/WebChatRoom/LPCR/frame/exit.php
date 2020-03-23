<?php

session_start();

if(!empty($_POST['type'])){
	
	session_destroy();
	echo "true";
	
}
// include('frame/send.html');
?>