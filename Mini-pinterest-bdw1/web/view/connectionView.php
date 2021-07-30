<?php
	ob_start();
?>

<div>
	<h2><?php echo $title; ?></h2>
</div>


<div>
	<p>
		<?php echo $error; ?>
	</p>
</div>
<form method="post" action="index.php?page=Connection">
	<label for="usernameID">Nom d'utilisateur</label>
	<input type="text" name="connectionUsername" id="usernameID" value="<?php if(isset($_POST['connectionUsername'])) { echo $_POST['connectionUsername']; } ?>"/>

	<label for="passwordID">Mot de passe</label>
	<input type="password" name="connectionPassword" id="passwordID"/>

	<input type="submit" name="connectionSubmit" value="Se Connecter"/>

</form>
<?php
	$content = ob_get_clean();
?>



