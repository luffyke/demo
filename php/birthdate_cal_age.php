<?php
	$birthdate = date_create('1988-08-15');
	$todaydate = date_create('now');
	if($todaydate < $birthdate){
		$age = 'Not yet born';
	} else {
		$interval = date_diff($todaydate, $birthdate);
		$age = $interval->format('%y').' years old';
	}
	echo $age;
