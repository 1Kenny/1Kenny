

<?php
  //ob_start();
  //include('template/header.php');
?>


<?php
  

  if(isset($_POST['ListeCat'])){
      echo $_POST['ListeCat'];
      $db=new PDO('mysql:host=localhost;dbname=BD','root','');
      $req = $db->prepare("SELECT nomFich ,description  FROM Photo JOIN Categorie ON Photo.catid=Categorie.catid WHERE Categorie.nomCat='".$_POST['ListeCat']."'");
      $req->execute();
      $dt= $req->fetchAll();
      foreach($dt as $data){ // Boucle pour l'affichage
          ?>
          <div> <a href='../view/v_uniqueImage.php?chemin=<?php echo $data['nomFich'];?>&amp;description=<?php echo $data['description'] ;?>'> <img src="<?php echo '../public/images/'.$data['nomFich'];?>"> </a></div>
          <?php

      }
  }else{

    echo 'aucune categorie selectionner';
  }

  include('../template/footer.php');

 // $content = ob_get_clean();

// require_once(PATH_VIEWS.'footer.php');
?>