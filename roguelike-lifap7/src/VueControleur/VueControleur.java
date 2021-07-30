package VueControleur;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


import modele.plateau.*;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une representation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : ecouter les evènements clavier et declencher le traitement adapte sur le modèle (flèches direction, etc.))
 *
 */
public class VueControleur extends JFrame implements Observer {
    private Jeu jeu; // reference sur une classe de modèle : permet d'acceder aux donnees du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int sizeX; // taille de la grille affichee
    private int sizeY;
    private JLabel texte =new JLabel("Nombre de cle  : "+ Heros.InventaireCle);
    private JLabel texte2 =new JLabel("Nombre de cle  : "+ Heros.inventairecapsule);
    Box bv = Box.createVerticalBox();


    // icones affichees dans la grille
    private ImageIcon icoHeroBas;
    private ImageIcon icoHeroHaut;
    private ImageIcon icoHeroGauche;
    private ImageIcon icoHeroDroite;
    private ImageIcon icoHero;
    private ImageIcon icoCaseNormale;
    private ImageIcon icoMur;
   // private ImageIcon icoColonne;
    private ImageIcon icoCle;
    private ImageIcon icoCapsule;
    private ImageIcon icoPorte;
    private ImageIcon icoPorteverrou;
    private ImageIcon icoCoffre;
    private ImageIcon icoCoffreouvert;
    private ImageIcon icoCoffreverrou;
    private ImageIcon icofeu;
    private ImageIcon icovert;
    private ImageIcon icotrou;
    private ImageIcon icoFin;

    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associee à une icône, suivant ce qui est present dans le modèle)


    public VueControleur(Jeu _jeu) {
        sizeX = _jeu.SIZE_X;
        sizeY = _jeu.SIZE_Y;
        jeu = _jeu;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        ajouterEcouteurClavier();
    }

    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                EntiteStatique sur = jeu.getEntite(jeu.getHeros().getX(), jeu.getHeros().getY());
            	switch(e.getKeyCode()) {  // on regarde quelle touche a ete pressee
                case KeyEvent.VK_LEFT : jeu.getHeros().gauche(); icoHero = icoHeroGauche; break;
                case KeyEvent.VK_RIGHT : jeu.getHeros().droite(); icoHero=icoHeroDroite; break;
                case KeyEvent.VK_DOWN : jeu.getHeros().bas(); icoHero=icoHeroBas; break;
                case KeyEvent.VK_UP : jeu.getHeros().haut(); icoHero=icoHeroHaut; break;
                case KeyEvent.VK_SPACE : jeu.interactionElementEspace() ;break;
                case KeyEvent.VK_A :jeu.getHeros().lancecapsule();break;
                case KeyEvent.VK_E :jeu.getHeros().jumpable();break;

                }
            }
        });
    }


    private void chargerLesIcones() {
        icoHero = chargerIcone("Images/Pacman.png");
        icoHeroBas = chargerIcone("Images/PacmanBas.png");
        icoHeroHaut = chargerIcone("Images/PacmanHaut.png");
        icoHeroGauche = chargerIcone("Images/PacmanGauche.png");
        icoHeroDroite= chargerIcone("Images/PacmanDroite.png");
        icoCaseNormale = chargerIcone("Images/Vide.png");
        icoMur = chargerIcone("Images/Mur.png");
        icoCle=chargerIcone("Images/Cle.png");
        icoCapsule=chargerIcone("Images/Capsule.png");
        icoPorte=chargerIcone("Images/Porte.png");
        icoPorteverrou=chargerIcone("Images/Porte_Verrou.png");
        icoCoffre=chargerIcone("Images/Coffre.png");
        icoCoffreouvert=chargerIcone("Images/Coffre_Ouvert.png");
        icoCoffreverrou=chargerIcone("Images/Coffre_Verrou.png");
        icofeu=chargerIcone("Images/feu.png");
        icovert=chargerIcone("Images/Vide.png");
        icotrou=chargerIcone("Images/Trou.png");
        icoFin=chargerIcone("Images/Fin.png");
    }

    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

    private void placerLesComposantsGraphiques() {
        setTitle("Roguelike");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        bv.add(grilleJLabels);
        bv.add(texte);
        bv.add(texte2);
        add(bv);						//Permet d'afficher l'inventaire et la grille
    }

    
    private void mettreAJourAffichage() {
    	if(jeu.getHeros().getOrientation() == 1){
            icoHero = icoHeroHaut;
        }
        if(jeu.getHeros().getOrientation() == 2){
            icoHero = icoHeroDroite;
        }
        if(jeu.getHeros().getOrientation() == 3){
            icoHero = icoHeroBas;
        }
        if(jeu.getHeros().getOrientation() == 4){
            icoHero = icoHeroGauche;
        }
    	
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
				EntiteStatique e = jeu.getEntite(x, y);
                if (e instanceof Mur) {
                    tabJLabel[x][y].setIcon(icoMur);
                } else if (e instanceof CaseNormale){				// Verifie ce qu'il y a sur la case
                	if (e.pickup()==0) {
                		tabJLabel[x][y].setIcon(icoCaseNormale);
                	}
                	else if (e.pickup()==1) {
                		
                		tabJLabel[x][y].setIcon(icoCle);
                	}
                	else if (e.pickup()==2) {
                		tabJLabel[x][y].setIcon(icoCapsule);
                	}
                }
                else if (e instanceof Porte){						// Verifie si la porte est verrouille ou non
                	if (((Porte) e).estverrouille==0) {
                		tabJLabel[x][y].setIcon(icoPorte);
                	}
                	else {
                		tabJLabel[x][y].setIcon(icoPorteverrou);
                	}
                }
                else if (e instanceof Coffre) {						//gere les coffres ouverts, ferme, et non verrouille et non verrouille.
                	if (e.coffreverrou==0 & e.ouvert==0) {
                		tabJLabel[x][y].setIcon(icoCoffre);
                	}
                	else if (e.coffreverrou==1 & e.ouvert==0) {
                		tabJLabel[x][y].setIcon(icoCoffreverrou);
                	}
                	else if (e.ouvert==1) {
                		
                		tabJLabel[x][y].setIcon(icoCoffreouvert);
                	}
                }

                 else if (e instanceof Dalle)				// v�rifie si la dalle est encore utilisable ou non 
                  {
                      if(((Dalle) e).isUtilisable())
                      {
                        tabJLabel[x][y].setIcon(icovert);
                      }
                      else
                      {
                         tabJLabel[x][y].setIcon(icofeu);
                      }
                  }
                 else if (e instanceof Case_vide) {			//Verifie si la case est un trou
                	 tabJLabel[x][y].setIcon(icotrou);
                 }
                 else if (e instanceof Fin) {				//Fin du jeu
                	 this.dispose();
                 }
            }
        }


        

        texte.setText("Nombre de cle  : "+ Heros.InventaireCle);					//Change le contenu de l'invntaire pour qu'il reste a jour
        texte2.setText("Nombre de capsules : "+Heros.inventairecapsule);

        
        tabJLabel[jeu.getHeros().getX()][jeu.getHeros().getY()].setIcon(icoHero);
    }

    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        
    }
}
