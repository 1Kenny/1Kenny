<?php
	ob_start();
	// var_dump($_FILES);
	if(!empty($_FILES))
	{
		$file_name = $_FILES['image']['name'];
		$file_extension = strrchr($file_name,".");
		$file_tmp_name = $_FILES['image']['tmp_name'];
		/* $file_dest = './images/'.$file_name;
		$schemin=getcwd();
		$chemin=$schemin."images/"; */
		$file_dest =  "images/".$file_name;	//getcwd.'../public/images/';

		$extensions_ok = array('.jpg','.jpeg','.png','.GIF');
		$file_extension1 = ".".strtolower(substr(strrchr($file_name,"."), 1));

		$unique_name = md5(uniqid(rand(),true));
		$file_name = "../public/images/".$unique_name.$file_extension1;

		$result = move_uploaded_file($file_tmp_name,$file_name);


		//TENTATIVE 2 pour Uploader 
		if (isset($_POST["upload"])) 
			{
				$categorie = $_POST['Categorie'];
				echo $categorie;
				$description = $_POST['description'];
				echo $description;
				$nomFich = $_FILES['image']['name'];
				echo $nomFich;
				$extension_acceptee = array(
					"png",
					"PNG",
					"gif",
					"jpeg"
				);
				
				// récuperer l'extension du fichier
				$file_extension = pathinfo($_FILES["image"]["name"], PATHINFO_EXTENSION);
				
				// Vérifier si le champ n'est pas vide,un + du required en html
				if (! file_exists($_FILES["image"]["tmp_name"])) {
					$response = array(
						"type" => "error",
						"message" => "Veuillez sélectionné une image."
					);
				}    // Tester si le format est accépté ou pas
				else if (! in_array($file_extension, $extension_acceptee)) {
					$response = array(
						"type" => "error",
						"message" => "Format invalide, Veuillez choisir les formats : jpeg, png, gif."
					);
					
				}    // teste de la taille en octet(100000) = 100 KO
				else if (($_FILES["image"]["size"] > 100000)) {
					$response = array(
						"type" => "error",
						"message" => "L'image dépasse la taille autorisé: 100 KO "
					);
				}    // Validate image file dimension
				
				else {
					$target = "../public/images/" . basename($_FILES["image"]["name"]);
					if (move_uploaded_file($_FILES["image"]["tmp_name"], $target)) {
					// vu que c'est en auto increment j'ai retiré celà
					$photoId_tab = execute_query($mysqli, "SELECT MAX(photoId) FROM Photo");
					$Id_suiv_photo = $photoId_tab[0][0] + 1;
					$categorie = $_POST['Categorie'];
					echo $categorie;
					$desc = $_POST['description'];
					$description = addslashes($desc);
					echo $description;
					$nomFich = $_FILES['image']['name'];
					echo $nomFich;
					$catId_tab = execute_query($mysqli,"SELECT catId FROM Categorie WHERE nomCat='$categorie'");
					$catId=$catId_tab[0][0];
					$compte = $_SESSION['id']; 
					//echo $compte;
					$userId_tab = execute_query($mysqli,"SELECT userID FROM Users WHERE username='$compte'"); 
					$userId=$userId_tab[0][0];
					//echo $userId;
					$query_insert_image = "INSERT INTO Photo (photoId, nomFich, description, catId, UserId, etat) VALUES 
						($Id_suiv_photo, '$nomFich', '$description', $catId, $userId, 'montre')";
					if($result = mysqli_query($mysqli,$query_insert_image)){
						$response = array(
						"type" => "success",
						"message" => "L'image a bien été téléchargé."
						);
					}
					}
					else { // retourne l'erreur en cas de problème de target, dossier inexistant par exemple
						$response = array(
							"type" => "error",
							"message" => "Problème durant le téléchargement de l'image."
						);
					}
				}
			}





/*	// TENTATIVE 1	Uploader
		if(isset($_POST['env']))
		{
			$max_size = 1000000;

			if($_FILES['image']['error'] > 0)
			{
				echo 'une erreur est survenue lors du trnasfert';
				die;
			}
			$file_size = $_FILES['image']['size'];
			echo $file_size;

			if($file_size > $max_size)
			{
				echo 'le fichier est trop volumineux, le maximum est de 100 Ko';
			}

			if(!in_array($file_extension1, $extensions_ok))
			{
				echo "le fichier n'est pas une image ";
				die;
			}
			
			if($result)
			{
				echo "Transfert terminé !";
			}



		}
*/
		/*
		if(move_uploaded_file($file_tmp_name, $file_dest))
			{
				echo 'Fichier envoyé avec succès';
			}
			*/
			

		/*

		if(in_array($file_extension, $extensions_ok))
		{
		if(move_uploaded_file($file_tmp_name, $file_dest))
			{
				echo 'Fichier envoyé avec succès';
			}
		}
		else
		{
			echo 'seuls les fichiers image JPEG, GIF ou PNG sont autorisés';
		}
		*/
	}
?>

<h2> Statistiques de <?php echo $_SESSION['username']; ?></h2>
<?php

/*
	// la toute première tentative pour l'upload
	if(isset($_POST["env"]))
	{
		$logo=$_FILES['photo']['name'];

		if($logo!="")
		{
			//require "uploadImage.php";
			if($sortie==false)
			{
				$logo=$dest_dossier . $dest_fichier;
			}
			else 
			{
				$logo="notdid";
			}
		}
		if($logo!="notdid")
		{
			echo "upload reussi!!!";
		}
		else
		{
			echo"recommence!!!";
		}
	}

	if($hasStartedGame) {
		echo '<p>Vous avez commencé une partie sans la finir</p>';
	} 
*/
?>
<p>Vous pouvez retrouver ici vos statistiques ainsi que diverses informations vous concernant.</p>
<ol>
	<p>
		<form  method="POST" name="formulaire" enctype="multipart/form-data">
		<div>
			<h5> inserer une image </h5>
			<input type="file" name="image" id="photo" /> 
		</div>
		<div>
			<input type="submit" class="btn" value="envoyer" />
		</div>
		<input type="hidden" value="b" name="env"/>
		</form>
	<!-- 
	<li>Vous avez joué <?php //echo $playedGames; ?> parties</li>
	<li>Vous avez gagné <?php //echo $wonGames; ?> partie</li>
	<li>Votre ratio de victoire est de <?php //echo $wonGameRatio; ?></li></br>
	-->
	</p>
</ol>
<p>Voici un résumé de vos dernières parties :</p></br>
<?php
/*
	$curGame = 0;
	echo '<table>
			<tr>
				<td>Partie</td>
				<td>Manche</td>
				<td>Victoire ? </td>
			<tr>
';
			
	foreach($games as $sleeve) {
		if($sleeve['gameNum'] != $curGame) {
			echo '<tr>';
			echo '<td rowspan="' . $sleeve['maxSleeves'] . '">' . $sleeve['gameNum'] .'</td>';
			$curGame = $sleeve['gameNum'];
		}
		else {
			echo '<tr>';
		}
		echo '<td>'. $sleeve['sleeveNum'] . '</td>';
		if($sleeve['Won'] == 1 ) {
 
			echo '<td class="positif">'. $sleeve['Won'] . '</td>';		
		}
		else if( $sleeve['Won'] == 2) {
			
			echo '<td class="negatif">' .$sleeve['Won'] .'</td>';
		}
		else {
			echo '<td class="egalite">' . $sleeve['Won'] . '</td>';	
		}
		echo '</tr>';
	}
	echo "</table>";
	*/
?>
<?php
	$content = ob_get_clean();
?>
