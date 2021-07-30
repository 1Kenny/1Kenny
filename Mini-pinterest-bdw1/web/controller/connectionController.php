<?php
	if(isset($_SESSION['ID'])) {
		header('Location: index.php');
	}
	require('model/dbase.php');
	require('model/connectionModel.php');
	
	$title = "Se Connecter";

	global $error;

	$username = "";
	$password = "";

	if(isset($_POST['connectionSubmit'])) {
		$username = $_POST['connectionUsername'];
		$password = $_POST['connectionPassword'];
		$database = dbConnect();
		if(connection($database, $username, $password)) {
			dbDisconnect($database);
			header('Location: index.php');
		}
		dbDisconnect($database);

	}
	include($pages[$askedPage][1]);
	include('template/default.php');
