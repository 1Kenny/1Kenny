<?php
	/*	
	 *	Variables needed :
	 *		- $content : come from view/***View.php
	 *	
	 *	Files required :
	 *		- template/header.php
	 *		- template/menu.php
	 *		- template/footer.php
	 *
	 */

	// We include the header and menu for all pages
	include('template/header.php');

	// We show the content of the page, from view/***View.php stored in a variable
	echo '<main>';
	include('template/menu.php'); ?>
		<section id="contenu">
			<?php echo $content; ?>
		</section>
	<?php echo '</main>';	

	// We include the footer 
	include('template/footer.php');

