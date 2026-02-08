package pjseti2.projetseti2;


/**
 * La classe StockBarriere représente le stock de barrières d'un joueur.
 *
 * @author Guillaume BORGET et Patrick NGOUALA
 * @version 1.0
 */
public class StockBarriere {
    /**
     * Tableau de Barriere qui représente le stock.
     */
    private Barriere[] monStockBarriere;
    /**
     * Couleur du stock de Barriere.
     */
    private CouleurPion couleur;
    /**
     * Nombre de Barriere restant au stock.
     */
    private int nbBarriere;

    /**
     * Constructeur de la classe StockBarriere. Alloue le tableau de Barriere et initialise le nombre initial de Barriere.
     * @param couleur : la couleur du stock
     */
    public StockBarriere(CouleurPion couleur) {
        this.nbBarriere = 10;
        //this.nbBarriere = 3;
        this.monStockBarriere = new Barriere[nbBarriere];
        this.couleur = couleur;
        for(int i=0;i<nbBarriere;i++)
            monStockBarriere[i]=new Barriere();
      	//A completer
    }

    /**
     * Récupère la Barriere à poser sur le jeu, et décrémente le nombre de Barriere restantes.
     * @return la dernière Barriere du tableau monStockBarriere
     */
    public Barriere getCurrentBarriere()
    {
      	//A completer
        if(this.nbBarriere>0){
            this.nbBarriere--;
        }
	return this.monStockBarriere[this.nbBarriere];
    }

    /**
     * Ajoute une Barriere au stock (en cas d'annulation de coup). Il suffit d'incrémenter le nombre de Barriere restantes du joueur.
     */
    public void addBarriere()
    {
    	//A completer
        this.monStockBarriere[this.nbBarriere]=new Barriere();
            this.nbBarriere++;
    }


    /**
     * Renvoie le nombre de Barriere restantes.
     * @return le nombre de Barriere restantes dans le stock
     */
    public int getNbBarriere(){
	//A completer
	return  this.nbBarriere;
    }


    /**
     * Actions à effectuer lorsqu'un joueur clique sur le bouton "Poser une barrière".
     */
    public void clickPoserUneBarriere(){
	//A completer
        Quoridor.etat = 2;
    }


}

