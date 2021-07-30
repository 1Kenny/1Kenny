<?php
	/*
	 *	Variables needed in this file :
	 *		- $siteTitle 	: title of the website, supposed to be in include/constant.php
	 *		- $title 	: title of the page, supposed to be in controller/***Controller.php
	 *
	 *	Files used in this file :
	 *		- public/css/defaultStyle.css
	 */
?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title><?php echo $title . ' - ' . $siteTitle ?></title>
		<link rel="stylesheet" type="text/css" href="public/css/defaultStyle.css">
	</head>
	<body>
		<header>
			<div style="float:center;">
				<a href="index.php">
					<img src="public/images/logo.png" width="5%" height="5%">
				</a>
			</div>
		<?php //	<h1><?php echo $siteTitle; </h1> ?>
			<strong> Le ZOO ! </strong>
		</header>
