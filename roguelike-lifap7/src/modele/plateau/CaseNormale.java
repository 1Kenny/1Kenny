package modele.plateau;

public class CaseNormale extends EntiteStatique {
	public static int objetsurcase ;   
    public CaseNormale(Jeu _jeu, int objetsurcase) {
    	super(_jeu); 
    	this.objetsurcase=objetsurcase;				// 0 : Aucun objet sur la case     1: Cle      2: Capsule
          
    }
   

    @Override
    public boolean traversable() {
        return true;
    }


	@Override
	public boolean jumpable() {
		return true;
	}
}
