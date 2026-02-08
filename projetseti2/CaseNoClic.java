package pjseti2.projetseti2;
import java.awt.*;
/**
 * La classe CaseNoClic est une spécialisation de la classe Case qui ne peut recevoir de clic souris.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */

public class CaseNoClic extends Case {
    /**
     * Constructeur de la classe CaseNoClic. Initialise les attributs de la classe, la couleur de fond et la taille du JButton.
     * @param couleur : la couleur de fond de la CaseNoClic
     * @param abs : l'abscisse de la CaseNoClic dans le plateau (entre 0 et 16)
     * @param ord : l'ordonnée de la CaseNoClic dans le plateau (entre 0 et 16)
     * @param typeCase le type de caseNoClic : CasePion, CaseBarriere ou CaseNoClic,
     * @param width : la largeur de la caseNoClic
     * @param height : la hauteur de la caseNoClic
     */
    public CaseNoClic(Color couleur, int abs, int ord, int typeCase, int width, int height) {
	super(couleur, abs, ord, typeCase, width, height);
	// A completer
    }
}













