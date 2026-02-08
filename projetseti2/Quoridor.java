package pjseti2.projetseti2;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Cette classe est la classe principale du jeu du Quoridor.  Elle a pour rôle d'initialiser les variables globales du jeu et d'instancier la fenêtre principale qui sera un objet de type \texttt{Fenetre}.
 * 
 * @author Guillaume BORGET et Patrick NGOUALA 
 * @version 1.0
 */
public class Quoridor
{

    /**
     * Etat courant du jeu dans le graphe d'état, initialisé à 0.
     */
    public static int etat;

    /**
     * Indique la couleur du joueur courant.
     */
    public static CouleurPion joueur ;

    /**
     * Le plateau de jeu.
     */
    public static Plateau unPlateau;

    /**
     * Stock de Barriere pour le Pion blanc.
     */
    public static StockBarriere stockBlanc;

    /**
     *Stock de Barrière pour le Pion noir.
     */
    public static StockBarriere stockNoir;

    /**
     *  Le Pion blanc.
     */
    public static Pion pionBlanc;

    /**
     *  Le Pion noir.
     */
    public static Pion pionNoir;

    /**
     *  CasePion de départ pour le déplacement d'un Pion, elle permet de stocker le retour de l'évènement déclenché par un clic souris sur une CasePion du Plateau.
     */
    public static CasePion caseDepPion;

    /**
     *  CasePion d'arrivée pour le déplacement d'un Pion, elle permet de stocker le retour de l'évènement déclenché par un clic souris sur une CasePion du Plateau.
     */
    public static CasePion caseArrPion;

    /**
     * Première CaseBarriere pour le pose d'une Barriere, elle permet de stocker le retour de l'évènement déclenché par un clic souris sur une CaseBarriere du Plateau.
     */
    public static CaseBarriere caseDepPosBar;

    /**
     *  Deuxième CaseBarriere pour le pose d'une Barriere, elle permet de stocker le retour de l'évènement déclenché par un clic souris sur une CaseBarriere du Plateau.
     */
    public static CaseBarriere caseArrPosBar;

    /**
     * La Fenêtre du jeu
     */
    public static Fenetre fenetrePrincipale;

    /**
     * Variable servant à modifier la couleur de bordure d'une Case en rouge.
     */
    public static Border redline = BorderFactory.createLineBorder(Color.red, 10, false);

    /**
     * Variable  servant à supprimer la couleur de bordure d'une Case.
     */
    public static Border empty = BorderFactory.createEmptyBorder();

    /**
     * Variable indiquant si le dernier coup était le déplacement d'un Pion (=0) ou la pose d'une Barriere (=1). Cette variable est utile pour l'annulation de la dernière action.
     */
    public static int dernierCoup;

    /**
     * Calcul de la valeur absolue d'un entier.
     * @param x un entier
     * @return la valeur absolue de x
     */
    public static int abs(int x) {//Retourne la valeur absolue de x
        return (x<0 ? -x : x);
    }

    /**
     * Méthode principale :
     *  - instancie les Pion des joueurs,
     * - initialise le joueur courant au joueur Couleur.blanc,
     * - initialise l'état du jeu,
     * - instancie la Fenetre du jeu.
     * @param args
     */
    public static void main(String[] args) {
	// Initialisation des variables globales
	dernierCoup = 0;
        joueur= CouleurPion.blanc;
        etat =0;

	// Instanciation de la fenetre
	fenetrePrincipale = new Fenetre("Jeu du Quoridor");
	// Instanciation des Pions
	pionBlanc = new Pion(CouleurPion.blanc);
        pionNoir = new Pion(CouleurPion.noir);
        // Instanciation des Stocks
        stockBlanc = new StockBarriere(CouleurPion.blanc);
        stockNoir = new StockBarriere(CouleurPion.noir);
        
        

	// On donne la possibilite de fermer la fenetre
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // On ajoute les composants de la fenetre
        fenetrePrincipale.ajouterComposants(fenetrePrincipale.getContentPane());

        // On affiche  la fenetre.
        fenetrePrincipale.pack();
        fenetrePrincipale.setVisible(true);
        
        fenetrePrincipale.boutonBN.setEnabled(false);
        
    }
}
