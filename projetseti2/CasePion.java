package pjseti2.projetseti2;
import javax.swing.*;
import java.awt.*;

/**
 * La classe CasePion est une spécialisation de la classe Case qui peut recevoir un Pion.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */
public class CasePion extends Case{
    /**
     * Le Pion de la CasePion
     */
    private Pion pion;
    
    

    /**
     * L'image du Pion à afficher sur le Plateau de jeu.
     */

    private ImageIcon imagePion;
    /**
     * Constructeur de la classe CasePion. Initialise les attributs de la classe, la couleur de fond et la taille du JButton. Il initialise le Pion et son image associée à null.
     * @param couleur : la couleur de fond de la CasePion
     * @param abs : l'abscisse de la CasePion dans le Plateau (entre 0 et 16)
     * @param ord : l'ordonnée de la CasePion dans le Plateau (entre 0 et 16)
     * @param typeCase le type de case : CasePion, CaseBarriere ou CaseNoClic
     * @param width : la largeur de la CasePion
     * @param height : la hauteur de la CasePion
     */
    public CasePion(Color couleur, int abs, int ord, int typeCase, int width, int height, int isStock) {
	super(couleur, abs, ord, typeCase, width, height);
        setOpaque(false);
        setContentAreaFilled(false);
        
        //setBorderPainted(false);
	// A completer

    }

    /**
     * Retourne le Pion de la CasePion.
     * @return le Pion de la CasePion
     */
    public Pion getPion() {
	// A completer
        return this.pion;
    }

    /**
     * Modifie le Pion se trouvant sur la CasePion.
     * @param p Pion à affecter à la CasePion. Si p est null, la méthode vide la CasePion (Pion et ImagePion), sinon elle affecte p et son image associée à la CasePion.
     */
    public void setPion(Pion p) {
	// A completer
        this.pion=p;
        if (this.pion != null){
            this.imagePion = this.pion.getImage(); //si la case contient un pion, on recupere l'image du pion
            this.occupe = true;
        }
        else {
            this.occupe = false;
            this.imagePion = null;
        }
        setIcon(imagePion);
    }
    
    
    
}
