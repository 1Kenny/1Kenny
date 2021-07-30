package modele.plateau;

public class Fin extends EntiteStatique {				//Case qui permet de fermer la fenetre
	public Fin(Jeu _jeu) {
		super(_jeu);

	}

	@Override
	public boolean traversable() {

		return true;
	}

	@Override
	public boolean jumpable() {

		return false;
	}

}
