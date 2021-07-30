<?php
	ob_start();
?>
	<h2>Créer un Compte</h2>
	<?php if(!empty($error)) { ?>
	<div>
		<p>Erreur : <?php echo $error; ?></p>
	</div>
	<?php } ?>
	<form method="post" action="index.php?page=Register">
		<label>Pseudo :
		<input type="text" name="createUsername" id="usernameID" value="<?php echo $username; ?>"/>
		</label>
		<label>Mot de Passe :
		<input type="password" name="createPassword" id="passwordID" value="<?php echo $password; ?>"/>
		</label>
		<label>Validation MdP :
		<input type="password" name="createPasswordBis" id="passwordBisID" value="<?php echo $passwordBis; ?>"/>
		</label>
		<input type="submit" name="createSubmit" value="Créer un Compte"/>
	</form>
<?php
	$content = ob_get_clean();
?>
