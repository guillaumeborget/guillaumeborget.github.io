package pjseti2.projetseti2;
import javax.swing.*;
import java.awt.*;
import java.math.*;

import java.awt.event.*;
/**
 * La classe Plateau représente le plateau de jeu.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */
public class Plateau extends JPanel {
    
    /**
     * Tableau de Case qui représente le Plateau de jeu du Quoridor.
     */
    private Case[][] monPlateau;
    
    private String terrain;
    
    public CasePion casePionBlanc;
    public CasePion casePionNoir;
    
    private Case[] historiqueCoup;

    /**
     * Constructeur sans paramètre de la classe Plateau. Il initialise les Case (de types : CasePion, CaseBarriere et CaseNoClic alternativement) et positionne le Pion noir et le Pion blanc sur leurs CasePion repectives de départ.
     */
    public Plateau() {

        /* On ajoute un gridbagLauout au panel */
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        
        

        /* Le gridBagConstraints va définir la position et la taille des éléments */
        GridBagConstraints gc = new GridBagConstraints();

        /* le parametre fill sert à définir comment le composant sera rempli GridBagConstraints.BOTH permet d'occuper tout l'espace disponible
         * horizontalement et verticalement GridBagConstraints.HORIZONTAL maximise horizontalement GridBagConstraints.VERTICAL maximise verticalement
         */


        /* insets définir la marge entre les composant new Insets(margeSupérieure, margeGauche, margeInférieur, margeDroite) */
        gc.insets = new Insets(0, 0, 0, 0);

        /* ipady permet de savoir où on place le composant s'il n'occupe pas la totalité de l'espace disponnible */
        gc.ipady = gc.anchor = GridBagConstraints.CENTER;

        /* weightx définit le nombre de cases en abscisse */
        gc.weightx = 17;

        /* weightx définit le nombre de cases en ordonnée */
        gc.weighty = 17;

        gc.gridheight =1;
        gc.gridwidth=1;

        /* On ajoute le composant au panel en précisant le GridBagConstraints */
        monPlateau = new Case[17][17];
        for (int i = 0; i < 17; i++){
            for (int j = 0; j < 17; j++) {
                /* pour dire qu'on ajoute un composant en position (i, j), on définit gridx=i et gridy=j */
                gc.gridx = i;
                gc.gridy = j;

                if (i % 2 == 0 && j%2==0) {
                    monPlateau[i][j] = (CasePion) new CasePion(Color.lightGray, i, j, 0, 60, 50,0);
                    gc.fill = GridBagConstraints.BOTH;
                }
                if (i % 2 == 1 && j%2==0) {
                    monPlateau[i][j] = (CaseBarriere) new CaseBarriere(Color.gray, i, j, 1, 10, 50);
                    gc.fill = GridBagConstraints.HORIZONTAL;
                }
                if (j % 2 == 1 && i%2==0) {
                    monPlateau[i][j] = (CaseBarriere) new CaseBarriere(Color.gray, i, j, 1, 60, 5);
                    gc.fill = GridBagConstraints.VERTICAL;
                }
                if (i % 2 == 1 && j%2==1) {
                    monPlateau[i][j] = (CaseNoClic) new CaseNoClic(Color.gray, i, j, 2, 1, 1);
                    gc.fill = GridBagConstraints.BOTH;
                }

                this.add(monPlateau[i][j],gc);
            }
        }
       
        casePionBlanc = (CasePion) monPlateau[0][8];
        casePionNoir =  (CasePion) monPlateau[16][8];
        
        casePionBlanc.setPion(Quoridor.pionBlanc);
        casePionNoir.setPion(Quoridor.pionNoir);
        this.terrain = "terrain";
        
        historiqueCoup = new Case[2];//On créer un tableau 1 dimension pour avoir 
                                     //l'historique des cases depart et arrivée 
                                     //soit pour le pion soit pour la barriere
        
    }
    
    public void choixTerrain(int i){
        if(i==1) this.terrain = "terrain";
        else if (i==2) this.terrain = "terrain2";
        else if (i==3) this.terrain = "terrain3"  ;
        repaint();
    }
    //afficher un fond choisir entre terrain, terrain2 ou terrain3
    public void paintComponent(Graphics g){
        g.drawImage(new ImageIcon("src//images//"+this.terrain+".jpg").getImage(),0,0, 700, 700, null);
        
    }
    /**
     * Change le tour au prochain joueur.
     */
    public void changerJoueur(){
	// A completer
        JOptionPane jop1 = new JOptionPane();
        if(Quoridor.joueur == CouleurPion.noir){
            Quoridor.fenetrePrincipale.boutonBN.setEnabled(false);
            if(Quoridor.stockBlanc.getNbBarriere()>0)
                      
                Quoridor.fenetrePrincipale.boutonBB.setEnabled(true);
            
            Quoridor.joueur = CouleurPion.blanc;
        }
        else if(Quoridor.joueur == CouleurPion.blanc){
            
            Quoridor.fenetrePrincipale.boutonBB.setEnabled(false);
            if(Quoridor.stockNoir.getNbBarriere()>0)
                Quoridor.fenetrePrincipale.boutonBN.setEnabled(true);
            
            Quoridor.joueur = CouleurPion.noir;
        }
        
    }
    /**
     * @return vrai si la case est occupée, considère le bord du tableau comme une case occupée
     */
    public boolean isCaseOccupe(int x, int y){
          if (x<0 || y<0 || x>monPlateau.length || y>monPlateau[x].length) 
              return true;
          return monPlateau[x][y].isOccupe();
    }
    /**
     * Teste si un déplacement vertical de Pion est possible (règles 1 et 2).
     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     * @return vrai si le déplacement  vertical du Pion entre dep et arr est possible, faux sinon
     */
    public boolean verifieDeplacementVert(CasePion dep, CasePion arr){
	// A completer
        if((dep.getOrdonnee()+2==arr.getOrdonnee()&& !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()+1) 
                || dep.getOrdonnee()-2==arr.getOrdonnee() && !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()-1)) 
                && dep.getAbscisse()==arr.getAbscisse())
	    return true;
	return false;
        
    }
    
    /**
     * Teste si un déplacement vertical de Pion avec un saut de pièce est possible  (règle 3).
     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     * @return vrai si le déplacement  vertical du Pion avec un saut de pièce entre dep et arr est possible, faux sinon
     */
    public boolean verifieDeplacementVertSaut(CasePion dep, CasePion arr){
	// A completer
	if((dep.getOrdonnee()+4==arr.getOrdonnee() 
                && isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()+2) 
                && !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()+1) 
                && !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()+3) 
            || dep.getOrdonnee()-4==arr.getOrdonnee() 
                && isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()-2) 
                && !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()-1)
                && !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()-3))
            && dep.getAbscisse()==arr.getAbscisse())
            return true;
	return false;
    }

    /**
     * Teste si un déplacement vertical de Pion avec un saut de pièce  et barrière est possible  (règle 4).
     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     * @return vrai si le déplacement  vertical du Pion avec un saut de pièce  et barrière entre dep et arr est possible, faux sinon
     */
    public boolean verifieDeplacementVertSautBar(CasePion dep, CasePion arr){
        if((
                
                 !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()+1) 
                && isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()+3) 
            || 
            
                 !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()-1)
                && isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()-3)) 
            && isCaseOccupe(dep.getAbscisse(),arr.getOrdonnee())
            && (dep.getOrdonnee()==arr.getOrdonnee()-2 || dep.getOrdonnee()==arr.getOrdonnee()+2) 
            && (dep.getAbscisse()==arr.getAbscisse()-2 || dep.getAbscisse()==arr.getAbscisse()+2)
            && !isCaseOccupe(arr.getAbscisse()-((arr.getAbscisse()-dep.getAbscisse())/2), arr.getOrdonnee()))
            return true;
	return false;
    }

    /**
     * Teste si un déplacement horizontal de Pion est possible  (règles 1 et 2).            && (dep.getOrdonnee()==arr.getOrdonnee()-2 || dep.getOrdonnee()==arr.getOrdonnee()+2) 
            && (dep.getAbscisse()==arr.getAbscisse()-2 || dep.getAbscisse()==arr.getAbscisse()+2)

     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     * @return vrai si le déplacement  horizontal du Pion entre dep et arr est possible, faux sinon
     */
    public boolean verifieDeplacementHor(CasePion dep, CasePion arr){
	// A completer
        if((dep.getAbscisse()+2==arr.getAbscisse()&& !isCaseOccupe(dep.getAbscisse()+1,dep.getOrdonnee()) 
                || dep.getAbscisse()-2==arr.getAbscisse() && !isCaseOccupe(dep.getAbscisse()-1,dep.getOrdonnee())) 
                && dep.getOrdonnee()==arr.getOrdonnee())
	    return true;
	return false;
    }
    
    
    /**
     * Teste si un déplacement horizontal de Pion avec un saut de pièce est possible (règle 3).
     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     * @return vrai si le déplacement  horizontal de pion avec un saut de pièce est possible entre dep et arr est possible, faux sinon
     */
    public boolean verifieDeplacementHorSaut(CasePion dep, CasePion arr){
	// A completer
	if((dep.getAbscisse()+4==arr.getAbscisse() 
                && isCaseOccupe(dep.getAbscisse()+2,dep.getOrdonnee()) 
                && !isCaseOccupe(dep.getAbscisse()+1,dep.getOrdonnee()) 
                && !isCaseOccupe(dep.getAbscisse()+3,dep.getOrdonnee()) 
            || dep.getAbscisse()-4==arr.getAbscisse() 
                && isCaseOccupe(dep.getAbscisse()-2,dep.getOrdonnee()) 
                && !isCaseOccupe(dep.getAbscisse()-1,dep.getOrdonnee())
                && !isCaseOccupe(dep.getAbscisse()-3,dep.getOrdonnee()))
            && dep.getOrdonnee()==arr.getOrdonnee())
            return true;
	return false;
    }

    /**
     * Teste si un déplacement horizontal de Pion avec un saut de pièce et barrière est possible (règle 4).
     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     * @return vrai si le déplacement  horizontal de pion avec un saut de pièce et barrière entre dep et arr est possible (règle 4), faux sinon
     */
    public boolean verifieDeplacementHorSautBar(CasePion dep, CasePion arr){
        // A completer
        if((
                
                 !isCaseOccupe(dep.getAbscisse()+1,dep.getOrdonnee()) 
                && isCaseOccupe(dep.getAbscisse()+3,dep.getOrdonnee())
                     
            || 
                 
                 !isCaseOccupe(dep.getAbscisse()-1,dep.getOrdonnee())
                && isCaseOccupe(dep.getAbscisse()-3,dep.getOrdonnee()))
                
            && isCaseOccupe(arr.getAbscisse(),dep.getOrdonnee())    
            && (dep.getOrdonnee()==arr.getOrdonnee()-2 || dep.getOrdonnee()==arr.getOrdonnee()+2) 
            && (dep.getAbscisse()==arr.getAbscisse()-2 || dep.getAbscisse()==arr.getAbscisse()+2)
            && !isCaseOccupe(arr.getAbscisse(),arr.getOrdonnee()-((arr.getOrdonnee()-dep.getOrdonnee())/2)))
            return true;
	return false;
    }

    /**
     * Teste si un déplacement de Pion est possible.
     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     * @return vrai si le déplacement du Pion entre dep et arr est possible, faux sinon
     */
    public boolean coupValidePion(CasePion dep, CasePion arr) {//
	// A completer
	return 
           verifieDeplacementVert(dep,arr) 
        || verifieDeplacementHor(dep,arr) 
        || verifieDeplacementVertSaut(dep,arr) 
        || verifieDeplacementVertSautBar(dep,arr) 
        || verifieDeplacementHorSaut(dep,arr)
        || verifieDeplacementHorSautBar(dep,arr);
    }

    /**
     * Déplace le Pion de la  casePion de départ dep à la CasePion d'arrivée arr, puis change de joueur.
     * @param dep casePion de départ du Pion
     * @param arr casePion d'arrivée du Pion
     */
    public void jouerCoup(CasePion dep, CasePion arr) {
     	// A completer
        JOptionPane jop1;
        jop1 = new JOptionPane();
        if (coupValidePion(dep, arr)){
            historiqueCoup[0] = dep;
            historiqueCoup[1] = arr;
            if(Quoridor.joueur == CouleurPion.blanc){
                arr.setPion(Quoridor.pionBlanc); //on enregistre la case qui contient le pion blanc, comme ça on sait dans quelle case se trouve le pion blanc
                casePionBlanc = arr;
            }
            else if(Quoridor.joueur == CouleurPion.noir){
                arr.setPion(Quoridor.pionNoir);
                casePionNoir = arr;
            }
            dep.setPion(null);
            
        }
        else jop1.showMessageDialog(null, "Le coup n'est pas valide", "Attention", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Vérifie que la CaseBarriere passée en paramètre peut être une première CaseBarriere pour poser une Barriere (règle 5).
     * @param dep caseBarriere : première CaseBarriere potentielle pour poser une Barriere
     * @return vrai si la CaseBarriere dep peut être une première CaseBarriere pour poser  une Barriere, faux sinon
     */

    public boolean verifiePremiereCaseBarriere(CaseBarriere dep) {
	// A completer
        if(dep.isVertical() && !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()+2)
                    //si vertical ET pas barriere en bas
                && !(isCaseOccupe(dep.getAbscisse()+1,dep.getOrdonnee()+1)
                    // ET si pas barriere en bas à droite
                && isCaseOccupe(dep.getAbscisse()-1,dep.getOrdonnee()+1))
                    //ET si pas barriere en bas à gauche
                || dep.isVertical() && !isCaseOccupe(dep.getAbscisse(),dep.getOrdonnee()-2)  
                    // OU si vertical ET pas barriere en haut
                && !(isCaseOccupe(dep.getAbscisse()+1,dep.getOrdonnee()-1)
                    //ET si pas barriere en haut à droite
                && isCaseOccupe(dep.getAbscisse()-1,dep.getOrdonnee()-1))
                    //ET pas barriere en haut à gauche
                
          || //OU
                
                dep.isHorizontal() && !isCaseOccupe(dep.getAbscisse()+2,dep.getOrdonnee())  
                    //si horizontal et pas barriere à droite
                && !(isCaseOccupe(dep.getAbscisse()+1,dep.getOrdonnee()+1)
                    //ET si pas barriere en haut à droite
                && isCaseOccupe(dep.getAbscisse()+1,dep.getOrdonnee()-1))
                    //ET pas barriere en bas à droite
                || dep.isHorizontal() && !isCaseOccupe(dep.getAbscisse()-2,dep.getOrdonnee())  
                    //OU si horizontale ET pas barriere à gauche
                && !(isCaseOccupe(dep.getAbscisse()-1,dep.getOrdonnee()+1)
                    //ET si pas barriere en bas à gauche
                && isCaseOccupe(dep.getAbscisse()-1,dep.getOrdonnee()-1))
                    //ET pas barriere en haut à gauche
                        )
            return true; //alors return true
        return false;// sinon false
    }

    /**
     * Vérifie qu'il est possible de poser une Barriere entre les CaseBarriere dep et arr (règle 5).
     * @param dep CaseBarriere : première CaseBarriere potentielle pour poser une Barriere
     * @param arr CaseBarriere : deuxième  CaseBarriere potentielle pour poser une Barriere
     * @return vrai si il est possible de poser une Barriere sur les CaseBarriere dep et arr, faux sinon
     */

    public boolean verifiePoserBar(CaseBarriere dep, CaseBarriere arr){
        // A completer
        int Ordonnee = arr.getOrdonnee()-((arr.getOrdonnee()-dep.getOrdonnee())/2);
        int Abscisse = arr.getAbscisse()-((arr.getAbscisse()-dep.getAbscisse())/2);
	if(verifiePremiereCaseBarriere(dep) && 
                dep.isVertical() && !isCaseOccupe(arr.getAbscisse(),arr.getOrdonnee()) && 
                !(isCaseOccupe(dep.getAbscisse()+1,Ordonnee) && isCaseOccupe(dep.getAbscisse()-1,Ordonnee))
                && (arr.getOrdonnee()==dep.getOrdonnee()+2 || arr.getOrdonnee()==dep.getOrdonnee()-2)
                && dep.getAbscisse() == arr.getAbscisse()
          || 
                dep.isHorizontal() && !isCaseOccupe(arr.getAbscisse(),arr.getOrdonnee()) && 
                !(isCaseOccupe(Abscisse,dep.getOrdonnee()+1) && isCaseOccupe(Abscisse,dep.getOrdonnee()-1))
                && (arr.getAbscisse()==dep.getAbscisse()+2 || arr.getAbscisse()==dep.getAbscisse()-2)
                && dep.getOrdonnee()==arr.getOrdonnee()
                )
            return true;
        return false;
    }

    /**
     * Pose une Barriere sur les CaseBarriere dep et arr, puis change de joueur (règle 5).
     * @param dep première CaseBarriere pour poser une Barriere
     * @param arr  deuxième CaseBarriere pour poser une Barriere
     */
    public void poserBar(CaseBarriere dep, CaseBarriere arr) {
	// A completer
        JOptionPane jop1 = new JOptionPane();
        if(Quoridor.joueur == CouleurPion.noir){
            
            Quoridor.stockNoir.clickPoserUneBarriere();
            Quoridor.stockNoir.getCurrentBarriere();
            Quoridor.fenetrePrincipale.labelBN.setText("Joueur Noir \n Barrières en stock :" + Quoridor.stockNoir.getNbBarriere());
            if(Quoridor.stockNoir.getNbBarriere()==0){
                jop1.showMessageDialog(null, "Le stock de barrières "+ Quoridor.joueur +" est vide", "Attention", JOptionPane.INFORMATION_MESSAGE);
            }
            changerJoueur();
            
            
        }
        else if(Quoridor.joueur == CouleurPion.blanc){
            
            Quoridor.stockBlanc.clickPoserUneBarriere();
            Quoridor.stockBlanc.getCurrentBarriere();
            Quoridor.fenetrePrincipale.labelBB.setText("Joueur Blanc \n Barrières en stock :" + Quoridor.stockBlanc.getNbBarriere());
            if(Quoridor.stockBlanc.getNbBarriere()==0){
                jop1.showMessageDialog(null, "Le stock de barrières "+ Quoridor.joueur +" est vide", "Attention", JOptionPane.INFORMATION_MESSAGE);
            }
            changerJoueur();
            
        }
        dep.setBarriere(new Barriere());
        arr.setBarriere(new Barriere());
        historiqueCoup[0] = dep;
        historiqueCoup[1] = arr;
        //pour que la barrière soit complette : les case no click entre 
        //la barrière depart et arrivée sont colorrizé de la meme couleur que la barrière
        if(dep.isHorizontal()) monPlateau[Math.min(dep.getAbscisse(),arr.getAbscisse())+1][dep.getOrdonnee()].setBackground(Color.BLACK);
        else monPlateau[dep.getAbscisse()][Math.min(dep.getOrdonnee(),arr.getOrdonnee())+1].setBackground(Color.BLACK);
        
    }
    
    /**
     * Annule la dernière action :
     * - le dernier déplacement d'un Pion entre les CasePion caseDepPion et caseArrPion,
     * - supprime la dernière Barriere posée sur les CaseBarriere caseDepPosBar et caseArrPosBar.
     */
    public void annulerCoup() {
    	// A completer
        changerJoueur();
        if(historiqueCoup[0] instanceof CasePion){ //on vérifie si la case contenu est de type case pion
            jouerCoup((CasePion) historiqueCoup[1],(CasePion) historiqueCoup[0]); // on réutilise jouer coup mais l'arrivée devient le départ 
        }
        else {
            CaseBarriere dep = (CaseBarriere) historiqueCoup[0];
            CaseBarriere arr = (CaseBarriere) historiqueCoup[1];
            
            dep.setBarriere(null);
            arr.setBarriere(null);
            //on réinitialise la couleur de la case NoClic entre les 2 bouts de barrières retirés
            if(dep.isHorizontal()) monPlateau[Math.min(dep.getAbscisse(),arr.getAbscisse())+1][dep.getOrdonnee()].setBackground(Color.gray);
            else monPlateau[dep.getAbscisse()][Math.min(dep.getOrdonnee(),arr.getOrdonnee())+1].setBackground(Color.gray);
        }
        
        Quoridor.etat = 0;
        // desactiver le bouton annuler
        Quoridor.fenetrePrincipale.boutonAnnuler.setEnabled(false);    
    }
    
    
    /**
     * Reinitialise le plateau de jeu
     */
    public void reinitialiser() {
	// Réinitialisation des variables globales
        Quoridor.joueur = CouleurPion.blanc;
        Quoridor.fenetrePrincipale.boutonBB.setEnabled(true);
        Quoridor.fenetrePrincipale.boutonBN.setEnabled(false);
        Quoridor.etat = 0;
        Quoridor.unPlateau = new Plateau();
        Quoridor.stockBlanc = new StockBarriere(CouleurPion.blanc);
        Quoridor.stockNoir = new StockBarriere(CouleurPion.noir);
        Quoridor.pionBlanc = new Pion(CouleurPion.blanc);
        Quoridor.pionNoir = new Pion(CouleurPion.noir);
	// On efface le panneau contenant les élements du jeu
        Quoridor.fenetrePrincipale.remove(Quoridor.fenetrePrincipale.fondPlateau);
	// On réinstancie les panneaux pour poser des barrières et pour le fond du plateau de jeu
	Quoridor.fenetrePrincipale.fondPlateau = new JPanel();
	Quoridor.fenetrePrincipale.barriereB = new JPanel(new GridLayout(2,1));
	Quoridor.fenetrePrincipale.barriereN = new JPanel(new GridLayout(2,1));

	Quoridor.unPlateau = new Plateau();
	Quoridor.stockBlanc = new StockBarriere(CouleurPion.blanc);
	Quoridor.stockNoir = new StockBarriere(CouleurPion.noir);
	// On ajoute les labels associés à la pose de barrrière du joueur Blanc
	Quoridor.fenetrePrincipale.barriereB.add(Quoridor.fenetrePrincipale.boutonBB);
	Quoridor.fenetrePrincipale.labelBB = new JLabel("Joueur Blanc \n Barrières en stock :" + Quoridor.stockBlanc.getNbBarriere());
	Quoridor.fenetrePrincipale.barriereB.add( Quoridor.fenetrePrincipale.labelBB);
	// On ajoute les labels associés à la pose de barrrière du joueur Noir
	Quoridor.fenetrePrincipale.barriereN.add(Quoridor.fenetrePrincipale.boutonBN);
	Quoridor.fenetrePrincipale.labelBN = new JLabel("Joueur Noir \n Barrières en stock :" + Quoridor.stockNoir.getNbBarriere());
	Quoridor.fenetrePrincipale.barriereN.add( Quoridor.fenetrePrincipale.labelBN);

	// On organise le fond du plateau de jeu
	Quoridor.fenetrePrincipale.fondPlateau.add(Quoridor.fenetrePrincipale.barriereB);
	Quoridor.fenetrePrincipale.fondPlateau.add(Quoridor.unPlateau);
	Quoridor.fenetrePrincipale.fondPlateau.add(Quoridor.fenetrePrincipale.barriereN);

	//On ajoute le fond du plateau de jeu à la fenêtre
	Quoridor.fenetrePrincipale.add(Quoridor.fenetrePrincipale.fondPlateau, BorderLayout.CENTER);

	// On rafraichit la fenêtre
        Quoridor.fenetrePrincipale.repaint();
        Quoridor.fenetrePrincipale.validate();
    }
}
