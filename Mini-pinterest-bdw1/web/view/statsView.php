<?php

	ob_start();
?>

<h2><?php echo $title; ?></h2>

<p>Cette page est là pour donner des statistiques</p>

<p>Les meilleurs par ordre croissant</p>
<table>
	<tr>
		<td>
			Rang
		</td>
		<td>
			Nom
		</td>
		<td>
			Score
		</td>
	</tr>
	

    
</table>
		

<?php
	$content = ob_get_clean();
?>
