<?php
function createUser($database, $username, $password) {
	global $error;

	if(strlen($username) < 3 || strlen($password) < 8) {
		$error = "Le pseudo et le mot de passe doivent faire au moins 8 caractères.";
		return false;
	}

	$request = "SELECT COUNT(*) AS occurences FROM Users WHERE username=?;";
	$count = $database->prepare($request);
	$count->execute(array($username));
	$password = sha1($password);
	$res = $count->fetch();
	print_r($res['occurences']);
	if($res['occurences'] != 0) {
		$error = "Ce pseudo n'est pas disponible";

		return false;
	}
	else {
		$request = "INSERT INTO Users (username, password) VALUES (?, ?);";
		$ret = $database->prepare($request);
		$ret->execute(array($username, $password));
		if(!$ret) {
			echo "Impossible d'insérer l'élement dans MySQL : ";
			print_r($database->errorInfo());
			return false;
		}
		else {
			$query = "SELECT userID FROM Users WHERE username=? AND password=?;";
			$answer = $database->prepare($query);
			$answer->execute(array($username, $password));
                	$result = $answer->fetch();
			$id = $result['userID'];
			$_SESSION['ID'] = $id;
			$_SESSION['username'] = $username;
			return true;
		}
	}
	return false;
}
