package modele.plateau;

public class Coffre extends EntiteStatique{
	public int cle;				//Nombre de cle dans le coffre
	public int capsule;			//Nombre de capsule dans le coffre

	public Coffre(Jeu _jeu, int cle,int capsule,int verou) {
		super(_jeu);
		this.cle=cle;						
		this.capsule=capsule;				
		coffreverrou=verou;					
	}
	
	 public boolean traversable() {								
		 if (coffreverrou==0 | ouvert==1 ) {							// Si le coffre n'est pas verrouille ou ouvert, il le vide et l'ouvre
		 	Heros.inventairecapsule=Heros.inventairecapsule+capsule;
		 	Heros.InventaireCle=Heros.InventaireCle+cle;
		 	cle=0;
		 	capsule=0;
		 	ouvert=1;
		 	return true;
		 }
		 else {												// Si le coffre est verouille, et si le joueur  une cle, le coffre peut etre ouvert mais sinon, non
			 if (Heros.InventaireCle>=1){
				 Heros.InventaireCle=Heros.InventaireCle-1;
				 Heros.inventairecapsule=Heros.inventairecapsule+capsule;
				 Heros.InventaireCle=Heros.InventaireCle+cle;
				 cle=0;
				 capsule=0;
				 ouvert=1;
				 return true;
			 }
			 else {
				 return false;
			 }
		 }
	 }
/**
	@Override
	public int estRamasse() {
		return 0;
	}
	*/

	@Override
	public boolean jumpable() {
		// TODO Auto-generated method stub
		return true;
	}

}
