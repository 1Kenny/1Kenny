<?php
require('include/constant.php');

function dbConnect() {
	global $dbHost, $dbUsername, $dbPassword, $dbDatabase;
	try {
		$database = new PDO("mysql:host=$dbHost;dbname=$dbDatabase;charset=utf8", $dbUsername, $dbPassword );
	} 
	catch (Exception $e) {
		die('Erreur : ' . $e->getMessage());
	}
	return $database;
}

function dbDisconnect($db) {
	unset($db);
}
