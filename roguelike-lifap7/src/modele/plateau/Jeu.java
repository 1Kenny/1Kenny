/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import java.util.Observable;
import java.util.Random;


public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 6;
    public static final int SIZE_Y = 6;
    int[] compteur =new int [3];		//Stock les salles étant deja apparu. Permet d'augmenter ou de diminuer le nombre de salle par niveau
    int k=0;							//compteur de salle
    private int pause = 200; // periode de rafraichissement

    private Heros heros;

    EntiteStatique[][] grilleEntitesStatiques = new EntiteStatique[SIZE_X][SIZE_Y];

    public Jeu() {
        initialisationDesEntites();
    }

    public Heros getHeros() {
        return heros;
    }

    public EntiteStatique[][] getGrille() {
        return grilleEntitesStatiques;
    }

	public EntiteStatique getEntite(int x, int y) {
		if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) {
			// L'entite demandee est en-dehors de la grille
			return null;
		}
		return grilleEntitesStatiques[x][y];
	}

    private void initialisationDesEntites() {


        heros = new Heros(this, 1, 1);
        // murs exterieurs horizontaux
        for (int x = 0; x < 6; x++) {
            addEntiteStatique(new Mur(this), x, 0);
            addEntiteStatique(new Mur(this), x, SIZE_Y-1);
        }
        // murs exterieurs verticaux
        for (int y = 1; y < 5; y++) {
            addEntiteStatique(new Mur(this), 0, y);
            addEntiteStatique(new Mur(this), SIZE_X-1, y);
        }

        
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                if (grilleEntitesStatiques[x][y] == null) {
                    grilleEntitesStatiques[x][y] = new CaseNormale(this,0);
                }

            }
        }
        

        addEntiteStatique(new Porte(this,1), 5, 3);
        randompickup( 1);

    }


    public void interactionElementEspace(){				//L'action de changement de salle lorsqu'on presse espace
        EntiteStatique sur = getEntite(getHeros().getX(), getHeros().getY());
        if(sur instanceof Porte && sur.traversable()) {
            changementDeSalle(getHeros().getX(),getHeros().getY());
        }

    }


    private void resetSalle() {		//A chaque changement de salle efface l'ancienne
        int y = 0;
        int x = 0;

        for(x = 0; x < SIZE_X; x++){
            addEntiteStatique(new Mur(this), x, y);
        }

        y = SIZE_Y-1;
        for(x = 0; x < SIZE_X; x++){
            addEntiteStatique(new Mur(this), x, y);
        }

        x = 0;
        for(y = 0; y < SIZE_Y; y++){
            addEntiteStatique(new Mur(this), x, y);
        }

        x = SIZE_X-1;
        for(y = 0; y < SIZE_Y; y++){
            addEntiteStatique(new Mur(this), x, y);
        }

        for(x = 1; x < SIZE_X-1; x++){
            for(y = 1; y < SIZE_Y-1; y++){
                addEntiteStatique(new CaseNormale(this, 0), x, y);
            }
        }
    }

    private void initialisationNiveau() { 			// Génére la piece
    	
    	
    	
        Random r = new Random();
        int nb_Porte = r.nextInt(6); 
        while (compteur[0]==nb_Porte | compteur[1]==nb_Porte | compteur[2]==nb_Porte) {				//On verifie si on a deja affiche cette salle
        	nb_Porte = r.nextInt(6);
        }
        if (k!=3) {
        	compteur[k]=nb_Porte;																	//on stock la salle affiché
        	k=k+1;
        }
        else {
        	nb_Porte=6;
        }

        switch (nb_Porte) {									//Les différentes pièces
            case 0 :
                resetSalle();
                addEntiteStatique(new Porte(this,1), SIZE_X-1, SIZE_Y/2);
                addEntiteStatique(new CaseNormale(this,0),4,4);
                addEntiteStatique(new Mur(this), 2, 2);
                addEntiteStatique(new Mur(this),1,2);
                addEntiteStatique(new Mur(this),4,2);
                randompickup( 1);
                adddalle(3,2);
                break;

            case 1 :
                resetSalle();
                addEntiteStatique(new CaseNormale(this,0),4,4);
                addEntiteStatique(new CaseNormale(this,0),3,4);
                addEntiteStatique(new CaseNormale(this,0),4,3);
                addEntiteStatique(new Porte(this,1),3,5);
                randompickup( 1);
                break;

            case 2 :
                resetSalle();
                addEntiteStatique(new CaseNormale(this,0),4,4);
                addEntiteStatique(new CaseNormale(this,0),3,4);
                addEntiteStatique(new CaseNormale(this,0),4,3);
                addEntiteStatique(new Porte(this, 0), 5, 3);
                addEntiteStatique(new Mur(this), 3, 3);
                adddalle(4,4);
                randompickup(0);
                break;
            case 3:
            	resetSalle();
            	addEntiteStatique(new CaseNormale(this,0),4,4);
                addEntiteStatique(new CaseNormale(this,0),3,4);
                addEntiteStatique(new CaseNormale(this,0),4,3);
            	addEntiteStatique(new Porte(this, 1), 1, 5);
            	addEntiteStatique(new Case_vide(this), 4, 4);
            	addEntiteStatique(new Mur(this), 2, 4);
            	randompickup( 1);
            	break;
            case 4:
            	resetSalle();

            	addEntiteStatique(new CaseNormale(this,0),4,4);
                addEntiteStatique(new CaseNormale(this,0),3,4);
                addEntiteStatique(new CaseNormale(this,0),4,3);
            	addEntiteStatique(new Porte(this, 0), 5, 4);
            	adddalle(3,4);
            	addEntiteStatique(new Case_vide(this), 4, 3);
            	addEntiteStatique(new Mur(this), 2, 4);
            	randompickup(0);
            	break;
            case 5:
            	resetSalle();

            	addEntiteStatique(new CaseNormale(this,0),4,4);
                addEntiteStatique(new CaseNormale(this,0),3,4);
                addEntiteStatique(new CaseNormale(this,0),4,3);
            	addEntiteStatique(new Porte(this, 1), 4, 5);
            	adddalle(4,3);
            	addEntiteStatique(new Case_vide(this), 2, 4);
            	addEntiteStatique(new Mur(this), 2, 4);
            	randompickup( 1);
            	break;
            case 6:
            	resetSalle();
            	addEntiteStatique(new CaseNormale(this,0),4,4);
                addEntiteStatique(new CaseNormale(this,0),3,4);
                addEntiteStatique(new CaseNormale(this,0),4,3);
                addEntiteStatique(new Fin(this),2,2);
                break;
        }

    }


    public void changementDeSalle(int x, int y) {


        //pour deplacer le hero et mieux montrer que l'on a changer de salle
        if(x == SIZE_X-1) { //porte e droite
            getHeros().setXY(0, y);
        }
        else if(y == SIZE_Y-1) { //porte en bas
            getHeros().setXY(x, 0);
        }
        else if(x == 0) { //porte a gauche
            getHeros().setXY(SIZE_X-1, y);
        }
        else if(y == 0) { //porte en haut
            getHeros().setXY(x, SIZE_Y-1);
        }

        initialisationNiveau();
    }


    public void start() {
        new Thread(this).start();
    }

    public void run() {

        while(true) {

            setChanged();
            notifyObservers();

            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    private void addEntiteStatique(EntiteStatique e, int x, int y) {			//Ajoute une entité statique sur une case
        grilleEntitesStatiques[x][y] = e;

    };
    // fonction ajout pickup et coffre
     private void addpickup(int objet, int x, int y) {
    	 grilleEntitesStatiques[x][y].objetsurcase = objet;

     }

    private void addcoffre (int cle, int capsule,int verou, int x, int y) {		//fonction d'ajout de coffre, avec son nombre de clés, de capsules et si il est verrouillé ou non
    	grilleEntitesStatiques[x][y]=new Coffre(this,cle,capsule,verou);
    }

    private void adddalle (int x, int y){										//Fonction d'ajout d'une dalle a usage unique
         grilleEntitesStatiques[x][y]=new Dalle(this);
    }

    public void randompickup(int ferme) {										//ajoute des pickups de façon aléatoire             Si ferme=1, la slle a une porte ferme a cle, si ferme=0, la porte n'est pas verrouillee 
    	Random b = new Random();
    	
    	int cmpt=getHeros().cles()-ferme;										//Compteur verifiant si la salle est finissable
        do{
        	
            for (int x = 0; x < SIZE_X-1; x++) {
                for (int y = 0; y < SIZE_Y-1; y++) {
                    EntiteStatique sur = getEntite(x,y);
                    int nb3 = b.nextInt(3) ; // Nombre de clé dans un coffre potentiel
                    int nb2 = b.nextInt(20) + 1;// Permet de rarifier l'apparition de coffre par rapport aux clés et capsules
                    int nb4 = b.nextInt(2);		// Représente si le coffre sera verrouillé ou non
                    int nb1 = b.nextInt(100) + 1;	//Probabilité d'apparition de pickup 
                    int xx = b.nextInt(2);			//Nombre de capsule dans un coffre potentiel


                    if (sur instanceof CaseNormale) {
                        if (sur.pickup() == 0) { // il n'ya rien sur la case
                            if (nb1 < 5) {		// chance d'apparition de clé :1/25
                            	addpickup(1,x,y);	//ajout de clé
                            	cmpt++;				
                            }
                            else if (nb1<7) {
                            	addpickup(2,x,y);	//ajout de capsule
                            	
                            }
                            else if (nb1 < 15) {
                                if (nb2 == 1) {
                                	addcoffre(nb3, xx, nb4, x, y);		
                                    cmpt = cmpt+nb3-nb4;		//On ajoute le nombre de cle au compteur et on soustrait 1 si le coffre est ferme a cle
                                    }
                                }
                            }

                        }
                    }

                }
            
        }while(cmpt<0);
    }
};
