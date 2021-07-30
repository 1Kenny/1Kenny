<?php
	if(isset($_SESSION['ID'])) {
		header('Location: index.php');
	}
	require('model/dbase.php');
	require('model/registerModel.php');

	$title = "S'enregistrer";

	global $error;

	$username="";
	$password="";
	$passwordBis="";

	if(isset($_POST['createSubmit'])) {	
		$username = $_POST['createUsername'];
		$password = $_POST['createPassword'];
		$passwordBis = $_POST['createPasswordBis'];
		
		if($password === $passwordBis) { 
			$database = dbConnect();
			if(createUser($database, $username, $password)) { 
				// If creation succeed
			        dbDisconnect($database);
				header('Location: index.php');
			}
			dbDisconnect($database);
		}
		else {
		$error="Les mots de passe sont différents";
		}	
	}
	include($pages[$askedPage][1]);
	include('template/default.php');
