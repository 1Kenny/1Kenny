<?php
	$title = "Accueil";
	if(isConnected()) {
		include($pages[$askedPage][2]);
	}
	else {
		include($pages[$askedPage][1]);
	}
	include('template/default.php');
?>

