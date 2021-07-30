package modele.plateau;

public class Case_vide extends EntiteStatique {

	public Case_vide(Jeu _jeu) {
		super(_jeu);
		// TODO Auto-generated constructor stub
	}

	public boolean traversable() {
        return false;
    }
	public boolean jumpable() {
		return true;
	}
}
