package pjseti2.projetseti2;
/**
 * La classe Barriere représente une barriere.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */


public class Barriere {

    /**
     * Variable partagée entre toutes les instances de la classe Barriere. Elle compte le nombre total d'instances de la classe Barriere créées.
     */
    public static int nbTotBarriere = 0;
    /**
     * Identifiant unique de la Barriere. Lors de la pose d'une Barriere, il sert à vérifier qu'on ne pose pas deux Barriere qui se croisent. 
     */
    private int identifiant;

    /**
     * Constructeur de la classe Barriere. Incrémente le compteur de Barriere et affecte un identifiant unique à la Barriere créée.
     */
    public Barriere(){
	// A completer
        this.nbTotBarriere++;
        identifiant = nbTotBarriere;
    }

    /**
     * Renvoie l'identifiant de la Barriere.
     * @return l'identifiant de la Barriere
     */
    public int getIdentifiant(){
	// A completer
        return this.identifiant;
    }

}
