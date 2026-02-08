package pjseti2.projetseti2;
import java.awt.*;
/**
 * La classe CaseBarriere est une spécialisation de la classe Case qui peut recevoir une Barriere.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */
public class CaseBarriere extends Case {

    /**
     * La Barriere de la CaseBarriere.
     */
    private Barriere barriere;

    /**
     * Constructeur de la classe CaseBarriere. Initialise les attributs de la classe, la couleur de fond et la taille du JButton. Il initialise la Barriere de la case à null.
     * @param couleur : la couleur de fond de la CaseBarriere,
     * @param abs : l'abscisse de la CaseBarriere dans le Plateau (entre 0 et 16),
     * @param ord : l'ordonnée de la CaseBarriere dans le Plateau (entre 0 et 16),
     * @param typeCase le type de Case : CasePion, CaseBarriere ou CaseNoClic,
     * @param width : la largeur de la CaseBarriere,
     * @param height : la hauteur de la CaseBarriere.
     */
    public CaseBarriere(Color couleur, int abs, int ord, int typeCase, int width, int height) {
	 super(couleur, abs, ord, typeCase, width, height);
	// A completer
        this.setBackground(couleur);
        this.setPreferredSize(new Dimension(width,height));
        addActionListener(this);
    }

    /**
     * Modifie la Barriere se trouvant sur la CaseBarriere.
     * @param b Barriere à affecter à la CaseBarriere
     */
    public void setBarriere(Barriere b) {
	// A completer
        this.barriere = b;
        if (b!=null){
            this.occupe = true;
            setBackground(Color.BLACK);
        }
        else {
            this.occupe = false;
            setBackground(Color.gray);
        }
    }


    /**
     * Retourne la Barriere de la CaseBarriere.
     * @return la Barriere de la CaseBarriere
     */
    public Barriere getBarriere() {
	// A completer
        return this.barriere;
    }


    /**
     * Modifie la couleur de fond de la CaseBarriere.
     * @param nouvCouleur la nouvelle couleur de fond de la CaseBarriere
     */
    public void setColor(Color nouvCouleur) {
	// A completer
        this.setBackground(nouvCouleur);
    }

    /**
     * Teste si une caseBarriere est verticale.
     * @return  vrai si la CaseBarriere est verticale, faux sinon
     */
    public boolean isVertical(){
	// A completer
        if(this.getOrdonnee()%2==0)
            return true;
	return false;
    }

    /**
     * Teste si la caseBarriere est horizontale.
     * @return  vrai si la CaseBarriere est horizontale, faux sinon
     */
    public boolean isHorizontal(){
	// A completer
        if(this.getOrdonnee()%2==0)
            return false;
	return true;
    }
    
}
