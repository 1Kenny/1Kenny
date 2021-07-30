<?php
	ob_start();
?>
	<h2><?php echo $title ?></h2>
	<p>Bienvenue sur le site du zoo !</p>
	<p> Vous pourrez voir les images des animaux par catégorie dans la section Animal <p>
	<p> Pour commencer à jouer connectez-vous ou créez un compte ! <p>
	<p> Faites tout ce qu'on vous dira et tout se passera bien <p>
		
	<img src="public/images/zoo.gif">
<?php
	$content = ob_get_clean();	
?>
