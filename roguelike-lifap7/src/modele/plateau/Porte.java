package modele.plateau;

public class Porte extends EntiteStatique{

	public int estverrouille;               // 0 : La porte n'est pas verouille  1 : La porte est verrouille
	public Porte(Jeu _jeu,int verrou) {
		super(_jeu);
		estverrouille=verrou;
		// TODO Auto-generated constructor stub
	}
	
	public boolean traversable() {
		if (estverrouille==0) {
			Heros.inventairecapsule=1;
			return true;
		}
		else {
			if (Heros.InventaireCle>=1){
				Heros.InventaireCle=Heros.InventaireCle-1;
				estverrouille=0;
				Heros.inventairecapsule=1;
				return true;
			}
			else {
				return false;
			}
			
		}
    }

	@Override
	public boolean jumpable() {
		// TODO Auto-generated method stub
		return false;
	}
}
