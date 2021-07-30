/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;
import java.awt.*;

/**
 * Heros du jeu
 */
public class Heros {
    private int x;
    private int y;
    
    public static int InventaireCle=0;
    public static int inventairecapsule=1;
    
    private Jeu jeu;
    public static int orientation;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setXY(int _x, int _y) { // Fonction pour update la position du Heros dans cette classe
        x = _x;
        y = _y;
    }
    

    public Heros(Jeu _jeu, int _x, int _y) {
        jeu = _jeu;
        x = _x;
        y = _y;
    }

    public void droite() {						// Permet de verifier si la case ou le heros veut aller est traversable et si un pickup est present, le rammasse
    	orientation = 2;
    	if (traversable(x+1, y)) {
            x ++;
        }
        if (jeu.grilleEntitesStatiques[x][y].objetsurcase==1) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	InventaireCle=InventaireCle+1;
        }
        else if (jeu.grilleEntitesStatiques[x][y].objetsurcase==2) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	inventairecapsule=inventairecapsule+1;
        }
    }

    public void gauche() {
    	orientation = 4;
        if (traversable(x-1, y)) {
            x --;
        }
        if (jeu.grilleEntitesStatiques[x][y].objetsurcase==1) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	InventaireCle=InventaireCle+1;
        }
        else if (jeu.grilleEntitesStatiques[x][y].objetsurcase==2) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	inventairecapsule=inventairecapsule+1;
        }
    }

    public void bas() {
    	orientation = 3;
        if (traversable(x, y+1)) {
            y ++;
        }
        if (jeu.grilleEntitesStatiques[x][y].objetsurcase==1) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	InventaireCle=InventaireCle+1;
        }
        else if (jeu.grilleEntitesStatiques[x][y].objetsurcase==2) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	inventairecapsule=inventairecapsule+1;
        }
    }

    public void haut() {
    	orientation =1;
        if (traversable(x, y-1)) {
            y --;
        }
        if (jeu.grilleEntitesStatiques[x][y].objetsurcase==1) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	InventaireCle=InventaireCle+1;
        }
        else if (jeu.grilleEntitesStatiques[x][y].objetsurcase==2) {
        	jeu.grilleEntitesStatiques[x][y].objetsurcase=0;
        	inventairecapsule=inventairecapsule+1;
        }
    }

    private boolean traversable(int x, int y) {

        if (x >0 && x < jeu.SIZE_X && y > 0 && y < jeu.SIZE_Y) {
            return jeu.getEntite(x, y).traversable();
        } else {
            return false;
        }
    };
    
    public boolean jumpable() {								//Verifie si le heros peut sauter au dessus de la case devant lui et peut atterrir sur la case +2
    	EntiteStatique a;
    	a=jeu.grilleEntitesStatiques[getDevantX()][getDevantY()];
    	if (a.jumpable()) {
    		int x2=x;										//Stock les coordonnées du heros avant le saut en cas d'echec
    		int y2=y;
    		x=getDevantX();
    		y=getDevantY();
    		a=jeu.grilleEntitesStatiques[getDevantX()][getDevantY()];
    		if (a.traversable()) {
    			a.traversable();
    			if (a.objetsurcase==1) {
    	        	a.objetsurcase=0;
    	        	InventaireCle=InventaireCle+1;
    	        }
    	        else if (a.objetsurcase==2) {
    	        	a.objetsurcase=0;
    	        	inventairecapsule=inventairecapsule+1;
    	        }
    			x=getDevantX();
        		y=getDevantY();
        		return true;
    		}
    		else {
    			x=x2;
    			y=y2;
    		}
    	}
    	return false;
    }
   
    public int cles() {
    	return (InventaireCle);
    }
    public int getOrientation(){// recupere l'orientation du joueur
        return orientation;
    }





    public int getDevantX() //Retourne la case devant le heros selon l'orientation
    {
        if(orientation == 1 || orientation == 3){
            return x;
        }
        else if(orientation == 2){
            return x+1;
        }
        else if(orientation == 4){
            return x-1;
        }
        System.out.println("ERREUR : Aucune orientation trouvee");
        return -1;
    }
    public int getDevantY(){
        if(orientation == 2 || orientation == 4){
            return y;
        }
        else if(orientation == 1){
            return y-1;
        }
        else if(orientation == 3){
            return y+1;
        }
        System.out.println("ERREUR : Aucune orientation trouvee");
        return -1;
    }

    public void lancecapsule() {			//Vérifie si la cse devnt le heros est une dalle en feu et l'éteins si le héros a assez de capsule
    	EntiteStatique a;
    	a=jeu.grilleEntitesStatiques[getDevantX()][getDevantY()];
    	if (a instanceof Dalle) {
    		if (a.utilisable==false) {
    			if (inventairecapsule>=1) {
    				inventairecapsule=inventairecapsule-1;
    				Dalle.passentrue();
    			}
    		}
    	}
    }

    


}



