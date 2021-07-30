<?php
function connection($database, $username, $password) {
	global $error;

	if(strlen($username) > 256 || strlen($username) > 256) {
		$error = "Pseudo ou mot de passe trop grand";
		return false;
	}
	$password = sha1($password);

	$query = "SELECT userID FROM Users WHERE username=? AND password=?;";
	$answer = $database->prepare($query);
	$answer->execute(array($username, $password));
	$result = $answer->fetch();
	
	if(!isset($result['userID']) || $result['userID'] <= 0) {
		$error = "Mauvais pseudo ou mot de passe";
		return false;
	}
	else {
		$_SESSION['ID'] = $result['userID'];
		$_SESSION['username'] = $username;
		return true;
	}
}
