package pjseti2.projetseti2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * La classe Fenetre initialise le fenêtre du jeu.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */
public class Fenetre extends JFrame {
    
    /**
     * Le bouton Annuler.
     */
    public JButton boutonTerrain1 ;
    /**
     * Le bouton Annuler.
     */
    public JButton boutonTerrain2 ;
    /**
     * Le bouton Annuler.
     */
    public JButton boutonTerrain3 ;
    
    public JButton boutonDracaufeu;
    public JButton boutonTortank;
    public JButton boutonFlorizarre;
    
    /**
     *  Le panneau pour afficher les pokeball.
     */
    public JPanel panePokeball;

    /**
     * Le bouton Annuler.
     */
    public JButton boutonAnnuler ;

    /**
     * Le bouton Reinitialiser.
     */
    public JButton boutonReinit ;

    /**
     * Le bouton pour poser une Barriere pour le Pion blanc.
     */
    public JButton boutonBB ;

    /**
     *  Le bouton pour poser une Barriere pour le Pion  noir.
     */
    public JButton boutonBN ;

    /**
     * Le panneau pour afficher les boutons Annuler et Réinitialiser.
     */
    public JPanel fondBouton;

    /**
     * Le panneau pour afficher le Plateau.
     */
    public JPanel fondPlateau ;

    /**
     *  Le panneau pour afficher le nombre de Barriere restant dans son StockBarriere pour le joueur blanc.
     */
    public JPanel barriereB;

    /**
     * Le panneau pour afficher le nombre de Barriere restant dans son StockBarriere pour le joueur noir.
     */
    public JPanel barriereN;

    /**
     *  Le label pour afficher le nombre de Barriere restant dans son StockBarriere pour le joueur blanc
     */
    public JLabel labelBB;

    /**
     * label pour afficher le nombre de Barriere restant dans son StockBarriere pour le joueur noir.
     */
    public JLabel labelBN;

    /**
     *  Constructeur sans paramètre de la classe Fenetre. Il lui donne un nom et paramètre sa taille.
     */

    public Fenetre(String name) {
	super(name);
	// on choisit la  taille de la fenêtre
	this.setSize(2500, 2500);
    }

    /**
     *   Méthode pour  créer les composants de la Fenetre et en organiser l'affichage.
     * @param pane Container recevant les composants.
     */
 
    public void ajouterComposants(final Container pane) {
	// allouer la mémoire pour les boutons
        boutonTerrain1 = new JButton("Terrain falaise");
        boutonTerrain2 = new JButton("Terrain fossil");
        boutonTerrain3 = new JButton("Terrain cascade");
	boutonAnnuler = new JButton("Annuler");
	boutonReinit = new JButton("Reinitialiser");
	boutonBB = new JButton( "Blanc : Poser une barrière");
	boutonBN = new JButton( "Noir : Poser une barrière");
        //boutonDracaufeu = new JButton("Pokeball Dracaufeu");
        boutonDracaufeu = new JButton(new ImageIcon(new ImageIcon("src//images//dracaufeupokeball.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        //boutonTortank = new JButton("Pokeball Tortank"); tortankpokeball
        boutonTortank = new JButton(new ImageIcon(new ImageIcon("src//images//tortankpokeball.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        //boutonFlorizarre = new JButton("Pokeball Florizarre");
        boutonFlorizarre = new JButton(new ImageIcon(new ImageIcon("src//images//florizarrepokeball.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        
	// allouer la mémoire pour les Panneaux
	barriereB = new JPanel(new GridLayout(2,1));
	barriereN = new JPanel(new GridLayout(2,1));
	fondBouton = new JPanel();
	fondPlateau = new JPanel();
        panePokeball = new JPanel();
        
        // La panneau contenant les boutons annuler et réiniialiser sera centré
	fondBouton.setLayout(new FlowLayout(FlowLayout.CENTER));
	fondBouton.add(boutonAnnuler);
	fondBouton.add(boutonReinit);
        fondBouton.add(boutonTerrain1);
        fondBouton.add(boutonTerrain2);
        fondBouton.add(boutonTerrain3);
        
        
        
        
	//Définition de l'action associée au clic sur le bouton annuler
	boutonAnnuler.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Quoridor.unPlateau.annulerCoup();
		}
	    });

	//Définition de l'action associée au clic sur le bouton  réinitialiser
	boutonReinit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Quoridor.unPlateau.reinitialiser();
		}
	    });
        
        //Définition de l'action associée au clic sur le bouton  terrain n°1
	boutonTerrain1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Quoridor.unPlateau.choixTerrain(1);
		}
	    });
        
        //Définition de l'action associée au clic sur le bouton  terrain n°2
	boutonTerrain2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Quoridor.unPlateau.choixTerrain(2);
		}
	    });
        
        //Définition de l'action associée au clic sur le bouton  terrain n°3
	boutonTerrain3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Quoridor.unPlateau.choixTerrain(3);
		}
	    });
        
	// on ajoute le panneau des boutons annuler et réinitialiser à la fenêtre au nord.
	pane.add(fondBouton, BorderLayout.NORTH);


	// La panneau contenant le plateau sera centré
	fondPlateau.setLayout(new FlowLayout(FlowLayout.CENTER));
        
	Quoridor.unPlateau = new Plateau();
	Quoridor.stockBlanc = new StockBarriere(CouleurPion.blanc);
	Quoridor.stockNoir = new StockBarriere(CouleurPion.noir);
	Quoridor.pionBlanc = new Pion(CouleurPion.blanc);
	Quoridor.pionNoir = new Pion(CouleurPion.noir);

	barriereB.add(boutonBB);
	// On ajoute un label au bouton pour afficher le nombre de barrières restantes pour le joueur blanc
	labelBB = new JLabel("Joueur Blanc \n Barrières en stock :" + Quoridor.stockBlanc.getNbBarriere());
	barriereB.add(labelBB);
        
        
        
	//Définition de l'action associée au clic sur le bouton poser une barrière blanc
	boutonBB.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Quoridor.stockBlanc.clickPoserUneBarriere();
                    boutonBB.setEnabled(false);
		}
	    });

	barriereN.add(boutonBN);
	// On ajoute un label au bouton pour afficher le nombre de barrières restantes pour le joueur noir
	labelBN = new JLabel("Joueur Noir \n Barrières en stock :" + Quoridor.stockNoir.getNbBarriere());
	barriereN.add(labelBN);
        
       
	//Définition de l'action associée au clic sur le bouton poser une barrière noir
	boutonBN.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Quoridor.stockNoir.clickPoserUneBarriere();
                    boutonBN.setEnabled(false);
		}
	    });
        
        
        
        //Définition de l'action associée au clic sur le bouton  PokeballDracaufeu 
	boutonDracaufeu.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    if(Quoridor.joueur == CouleurPion.blanc){
                            Quoridor.pionBlanc.choixPokeball(1);
                            Quoridor.unPlateau.casePionBlanc.setPion(Quoridor.pionBlanc);
                    }
                    else if(Quoridor.joueur == CouleurPion.noir){
                            Quoridor.pionNoir.choixPokeball(1);
                            Quoridor.unPlateau.casePionNoir.setPion(Quoridor.pionNoir);
                    }
		}
	    });
        
        //Définition de l'action associée au clic sur le bouton  PokeballTortank 
	boutonTortank.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    if(Quoridor.joueur == CouleurPion.blanc){
                            Quoridor.pionBlanc.choixPokeball(2);
                            Quoridor.unPlateau.casePionBlanc.setPion(Quoridor.pionBlanc);
                    }
                    else if(Quoridor.joueur == CouleurPion.noir){
                            Quoridor.pionNoir.choixPokeball(2);
                            Quoridor.unPlateau.casePionNoir.setPion(Quoridor.pionNoir);
                    }
                    Quoridor.unPlateau.repaint();
		}
	    });
        
        //Définition de l'action associée au clic sur le bouton  PokeballFlorizare 
	boutonFlorizarre.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    if(Quoridor.joueur == CouleurPion.blanc){
                            Quoridor.pionBlanc.choixPokeball(3);
                            Quoridor.unPlateau.casePionBlanc.setPion(Quoridor.pionBlanc);
                    }
                    else if(Quoridor.joueur == CouleurPion.noir){
                            Quoridor.pionNoir.choixPokeball(3);
                            Quoridor.unPlateau.casePionNoir.setPion(Quoridor.pionNoir);
                    }
		}
	    });
        panePokeball.add(boutonDracaufeu);
        panePokeball.add(boutonTortank);
        panePokeball.add(boutonFlorizarre);

	// On organise le fond de la fenêtre
        
	fondPlateau.add(barriereB);
	fondPlateau.add(Quoridor.unPlateau);
	fondPlateau.add(barriereN);
	// On ajoute l'élément au container
	pane.add(fondPlateau, BorderLayout.CENTER);
        pane.add(panePokeball, BorderLayout.SOUTH);
    }
}

