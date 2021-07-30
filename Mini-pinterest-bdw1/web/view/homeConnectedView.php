<?php
	ob_start();
?>
	<h2><?php echo $title ?></h2>
	<p>Bonjour <?php echo $_SESSION['username']; ?>! </br>
	Ravi de vous revoir parmis nous, vous nous avez manqué ;) ! 	
	</p>
	<img src="public/images/slt.gif">
<?php
	$content = ob_get_clean();	
?>
