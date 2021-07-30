<?php
/*
 * Fian NIAMKE 	p1715528
 * Terna KOUROUMA
 *
 */
session_start();
require('include/constant.php');
require('include/router.php');
require('model/base.php');

$connected = isConnected();
$error = "";

try {
	if(isset($_GET['page'])) {
		$askedPage = $_GET['page'];
		if(isset($pages[$askedPage])) {
			include($pages[$askedPage][0]);
		}
		else {
			throw new Exception("La page demandée n'existe pas");
		}
	}
	else {
		$askedPage = 'Home';
		include($pages['Home'][0]);
	}
}
catch(Exception $e) {
	$errorMessage = $e->getMessage();
	echo $errorMessage;
}




