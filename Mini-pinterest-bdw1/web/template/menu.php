<nav>
	<div id="menu">
		<ul>
			<li><a href="index.php">Home</a></li>
			<li><a href="index.php?page=Anim">Animal</a></li>
			<!-- <li><a href="index.php?page=Stats">Classement</a></li>  -->
			<?php 
				if(!isConnected()) { 
			?>
			<li><a href="index.php?page=Connection">Se Connecter</a></li>
			<li><a href="index.php?page=Register">Créer un compte</a></li>
			<li><a href="index.php?page=Stats">Classement</a></li> 
			<?php	 
				} else { 
			?>
			<li><a href="index.php?page=Profile">Mon Profil</a></li>
			<li><a href="index.php?page=Disconnection">Déconnexion</a></li>
			<?php
				}

			?>
		
		</ul>
	</div>
</nav>
