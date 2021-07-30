<?php
	ob_start();
?>
	<h2><?php echo $title; ?></h2>
	<p>
		 </br>
	<form action="index.php?page=Cate" method="post">
         <select id="selectCategorie" name="ListeCat">
                <option value="Carnivore">Carnivore</option>
                <option value="Herbivore">Herbivore</option>
        </select> <input type=submit value="Envoyer">
    </form>
     
		</br>
</p>
<?php
	$content = ob_get_clean();
?>
