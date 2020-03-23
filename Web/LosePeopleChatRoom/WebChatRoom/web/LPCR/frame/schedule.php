<?php

if(!empty($_POST)){

	
	$now = $_POST['now'];
	$max = $_POST['max'];
	$schedule = $now / $max;
	$schedule = $schedule * 200;
	// $textsch = (string)$schedule
	echo $schedule;
	
}

?>