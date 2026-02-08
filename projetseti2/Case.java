package pjseti2.projetseti2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;
/**
 * La classe Case représente une case du plateau, elle étend la classe Jbutton et implémente la classe ActionListener.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */
public class Case extends JButton implements ActionListener {

    /**
     * Indique si une case est de type CasePion (si 0),  CaseBarriere (si 1), ou CaseNoClic (si 2).
     */
    protected int typeCase;

    /**
     * Indique la couleur de fond de la Case.
     */
    protected Color couleurFond;
    /**
     * Indique si la Case est occupée par un Pion ou une Barriere.
     */

    protected boolean occupe;
    /**
     * Indique l'abscisse de la Case dans le Plateau (entre 0 et 16).
     */

    protected int abscisse;
    /**
     *  Indique l'ordonnée de la Case dans le Plateau (entre 0 et 16).
     */
    protected int ordonnee;

    /**
     * Constructeur de la classe Case. Initialise les attributs de la classe, la couleur de fond et la taille du JButton, et ajoute un ActionListener au JButton.
     * @param couleur : la couleur de fond de la Case
     * @param abs : l'abscisse de la Case dans le plateau (entre 0 et 16)
     * @param ord : l'ordonnée de la Case dans le plateau (entre 0 et 16)
     * @param typeCase le type de case : CasePion,  CaseBarriere ou CaseNoClic
     * @param width : la largeur de la Case
     * @param height : la hauteur de la Case
     */
    public Case(Color couleur, int abs, int ord, int typeCase, int width, int height) {
        // A completer
        this.abscisse = abs;
        this.ordonnee = ord;
        this.typeCase = typeCase;
        this.setBackground(couleur);
        this.setPreferredSize(new Dimension(width,height));
        addActionListener(this);
        
    }


    /**
     * Renvoie l'abscisse de la Case.
     * @return l'abscisse de la Case
     */
    public int getAbscisse() {
	// A completer
	return this.abscisse;
    }

    /**
     * renvoie l'ordonnée de la Case.
     * @return l'ordonnée de la Case
     */
    public int getOrdonnee() {
	// A completer
        return this.ordonnee;
    }

    /**
     * Renvoie si la Case est occupée par un Pion ou une Barriere
     * @return vrai si la Case est occupée, faux sinon
     */
    public boolean isOccupe() {
	// A completer
        return this.occupe;
    }


    /**
     * Actions a effectuer si on clique sur une Case du Plateau (implémente le graphe d'état).
     * @param e : Evenement qui est le dernier clic souris
     */
    public void actionPerformed(ActionEvent e) {
	
        Case caseDep;
        Case caseArr;
        /*******************************************************************************************************************/
        /**************************************  Déplacer un pion **********************************************************/
        /*******************************************************************************************************************/
        if (Quoridor.etat == 0) {
	    /** Récupération de la Case de départ du Pion à déplacer */
            
	    // identification de la case possible de depart d'un déplacement du pion du joueur courant
            caseDep = (Case) e.getSource();
	    // si la case cliquée est de type CasePion
	    if (caseDep.typeCase == 0) {
		// on caste la Case en CasePion
                Quoridor.caseDepPion = (CasePion) caseDep;
		// si la case sélectionée est occupée par un Pion de la couleur du joueur courant
                if (Quoridor.caseDepPion.isOccupe())
                    if (Quoridor.caseDepPion.getPion().getCouleur() == Quoridor.joueur) {
			// On entoure la CasePion de départ en rouge et on passe à l'état 1 
			Quoridor.caseDepPion.setBorder(Quoridor.redline);
			Quoridor.etat = 1;
                        
                    }
            }
	}
        
        if (Quoridor.etat == 1) {
	    /** Récupération de la Case d'arrivée du pion à déplacer */
            // identification de la case possible d'arrivée d'un déplacement du pion du joueur courant
            caseArr = (Case) e.getSource();
	    // si la case cliquée est de type CasePion
            if (caseArr.typeCase == 0) {
		// on caste la Case en CasePion
                Quoridor.caseArrPion = (CasePion) caseArr;
		// si la case sélectionnée est différente de la case de depart
                if (Quoridor.caseDepPion != Quoridor.caseArrPion) {
		    // si la case sélectionée est est libre
                    if (!Quoridor.caseArrPion.isOccupe())
			// si le déplacement du Pion est valide
                        if (Quoridor.unPlateau.coupValidePion(Quoridor.caseDepPion, Quoridor.caseArrPion)) {
			    // On teste si le coup valide entraîne la victoire du joueur courant, si oui on passe à l'état 4 
                            if (Quoridor.joueur == CouleurPion.blanc && Quoridor.caseArrPion.getAbscisse()==16 || Quoridor.joueur == CouleurPion.noir && Quoridor.caseArrPion.getAbscisse()==0)
                                Quoridor.etat = 4;
                            else {
				// sinon on joue le coup et on retourne à l'état 0
                                Quoridor.unPlateau.jouerCoup(Quoridor.caseDepPion, Quoridor.caseArrPion);
                                // reactiver le bouton annuler
                                Quoridor.fenetrePrincipale.boutonAnnuler.setEnabled(true);
                                Quoridor.unPlateau.changerJoueur();
                                Quoridor.etat = 0;
                                
                            }
                            Quoridor.caseDepPion.setBorder(Quoridor.empty);
                        }
                }
            }
        }


	/*******************************************************************************************************************/
	/**************************************Poser une barriere **********************************************************/
	/*******************************************************************************************************************/


       
        if (Quoridor.etat == 2) {
	    /** Récupération de la première Case de la Barriere à poser, en cas de clic sur le bouton "Poser une barrière"*/
	    // identification de la première case possible pour poser une barrière
            caseDep = (Case) e.getSource();
	    // si la case cliquée est de type CaseBarriere
	    if (caseDep.typeCase == 1) {
		// on caste la Case en CaseBarriere
                Quoridor.caseDepPosBar = (CaseBarriere) caseDep;
		// si la case sélectionée est est libre
                if (!Quoridor.caseDepPosBar.isOccupe()) {
		    // si une deuxième case est  possible pour poser une barriere on entoure la case en vert et on passe à l'état 3
                    if (Quoridor.unPlateau.verifiePremiereCaseBarriere(Quoridor.caseDepPosBar)) {
			Quoridor.caseDepPosBar.setColor(Color.green);
                        Quoridor.etat = 3;
                    }
                }
            }
        }
      
        if (Quoridor.etat == 3) {
	    /** Récupération de la deuxième Case de la Barrière à poser*/
	    // identification de la deuxième case possible pour poser une barrière
	    caseArr = (Case) e.getSource();
	    // si la case cliquée est de type CaseBarriere
	    if (caseArr.typeCase == 1) {
		// on caste la Case en CaseBarriere
                Quoridor.caseArrPosBar = (CaseBarriere) caseArr;
		// si la case sélectionnée est différente de la case de depart
                if (Quoridor.caseDepPosBar != Quoridor.caseArrPosBar) {
		    // si la case sélectionée est est libre
                    if (!Quoridor.caseArrPosBar.isOccupe()) {
			// si la case selectionnée est une deuxième case possible pour poser une barriere
			if (Quoridor.unPlateau.verifiePoserBar(Quoridor.caseDepPosBar, Quoridor.caseArrPosBar)) {
			    // on pose la barrière et on retourne à l'état 0
			    Quoridor.unPlateau.poserBar(Quoridor.caseDepPosBar, Quoridor.caseArrPosBar);
                            // reactiver le bouton annuler
                                Quoridor.fenetrePrincipale.boutonAnnuler.setEnabled(true);
                            Quoridor.etat = 0;
                        }

                    }
                }
            }
        }

	/*******************************************************************************************************************/
	/********************************************* Fin du jeu *********************************************************/
	/*******************************************************************************************************************/
        if (Quoridor.etat == 4) {
	    // On déselectionne la case de départ
	    Quoridor.caseDepPion.setBorder(Quoridor.empty);
	    // On déplace le Pion dans la case d'arrivée
	    Quoridor.caseArrPion.setPion(Quoridor.caseDepPion.getPion());
	    Quoridor.caseDepPion.setPion(null);

            //on ouvre une boite de dialogue pour signifier que le partie est finie : 2 choix fermer le jeu ou recommencer
            JOptionPane d = new JOptionPane(); // les textes figurant // sur les boutons
            String lesTextes[] = {"Recommencer", "Fermer le jeu"}; // indice du bouton qui a été cliqué ou CLOSED_OPTION
            int retour = d.showOptionDialog(this, "Partie terminée. Le joueur " + Quoridor.joueur + " a gagné !", "Fin de jeu", 1, 1, new ImageIcon(), lesTextes, lesTextes[0]);
            if (retour == 0) {
		// lorqu'on a cliqué sur "Recommencer", on réinitialise le jeu
                Quoridor.unPlateau.reinitialiser();
	    } else {
		// lorqu'on a cliqué sur "Fermer le jeu", on ferme la fenêtre
                Quoridor.fenetrePrincipale.dispose();
            }
	}
    }
}












