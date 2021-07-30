<?php
	$title = "Statistiques";

	require('model/dbase.php');
	require('model/statsModel.php');

	$database = dbConnect();
	
	require($pages[$askedPage][1]);
	require("template/default.php");
