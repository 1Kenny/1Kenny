package modele.plateau;

/**
 * Ne bouge pas (murs...)
 */
public abstract class EntiteStatique {
    protected Jeu jeu;
    public int objetsurcase=0;		// 0 : Aucun objet sur la case     1: Cle      2: Capsule
    public int coffreverrou;		// 0 : Coffre non verrouille      1 : Coffre verrouille
    public int ouvert;				//0: Coffre ferme       1 : coffre ouvert
	public boolean utilisable;		// Pour savoir si une dalle a usage unique est utilisable

    
    public EntiteStatique(Jeu _jeu) {
        jeu = _jeu;
    }

    public abstract boolean traversable();

    public int pickup() {			//permet de savoir le pickup sur une case
    	return objetsurcase;
    }
    public abstract boolean jumpable();			//permet de savoir si on peut sauter au dessus


}