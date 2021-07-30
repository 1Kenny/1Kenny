
<?php
	// ob_start();
?>

<div id="menu">
	
		<h2>Menu</h2>
		
		<ul>
			<li><a href="../index.php">Accueil</a></li>
		</ul>
		
		(rien pour le moment)
		
	</div>




<?php
 
  if(isset($_GET['chemin']) AND isset($_GET['description']) ){
          ?>
          <div> <?php echo $_GET['description'] ;?> <img src="<?php echo '../public/images/'.$_GET['chemin'];?>"> </div>
<?php
  }else{

    echo 'Erreur Survenu';
  }

?>


<?php
	//$content = ob_get_clean();
?>