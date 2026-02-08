package pjseti2.projetseti2;
import java.awt.Image;
import javax.swing.*;

/**
 * La classe Pion représente une pion.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */
public class Pion
{
    /**
     * Couleur du Pion.
     */
    private CouleurPion couleur;
    //La pokeball contenant le pokemon choisit à afficher
    private String pokeball;

    /**
     * Constructeur de la classe Pion.
     * @param couleur la CouleurPion du Pion crée.
     */

    public Pion(CouleurPion couleur) {
	//A completer
        this.couleur = couleur;
        if (couleur == CouleurPion.blanc)
            this.pokeball = "Dracaufeu";
        else if (couleur == CouleurPion.noir)
            this.pokeball = "Tortank";
    }

    /**
     * Renvoie la couleur du Pion
     * @return  la CouleurPion du Pion.
     */
    public CouleurPion getCouleur() {
	// A completer
        
        return this.couleur;
    }
    
    public ImageIcon getImage(){
        if(couleur == CouleurPion.blanc){
                return new ImageIcon(new ImageIcon("src//images//"+this.pokeball+".png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            }
            else// if(this.pion.getCouleur()== CouleurPion.noir)
                return new ImageIcon(new ImageIcon("src//images//"+this.pokeball+".png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    }
    
    public void choixPokeball(int i){
        if (i==1) this.pokeball = "Dracaufeu";
        else if (i==2) this.pokeball = "Tortank";
        else if (i==3) this.pokeball = "Florizarre";
        
    }
}
