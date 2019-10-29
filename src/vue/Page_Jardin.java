/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import src.controleur.MenuJardinListener;
import src.controleur.MouseJardinListener;

import src.vue.DessinerJardin;
import src.vue.Menu_principal;

import src.modele.DonneeJardin;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unchecked")

public class Page_Jardin extends JPanel 
{

	private CardLayout g;
	private JPanel pan;

	/**
	 * Constructeur pour Page_connexion correspondant à la vue de la page d'identification.
	 * @param gestionnaire
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param dimx
	 *			int correspondant à la dimension en longueur du jardin.
	 * @param dimy
	 *			int correspondant à la dimension en hauteur du jardin.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param nomJardin
	 *			String correspondant au nom du jardin.
	 */
	public Page_Jardin(CardLayout gestionnaire,JPanel pan, int dimx, int dimy, JFrame f, String nomJardin) 
	{
		this.g=gestionnaire;
		this.pan=pan;

		GridBagLayout gestion = new GridBagLayout();
		this.setLayout(gestion);

		GridBagConstraints contrainte = new GridBagConstraints();

		JButton quitter = new JButton("Quitter le jardin");
		JButton retour = new JButton("Annuler la sélection");
	    JButton fusioner = new JButton("Réunir");
	    JButton action = new JButton("Actions sur la parcelle");
	    JButton historique = new JButton("Accéder à l'historique");
	    JButton couper = new JButton("Séparer");
	    JPanel panneau = new JPanel();
	    JPanel panneau2 = new JPanel();
	    JPanel panneau3 = new JPanel();
	    
	    DonneeJardin requete = new DonneeJardin(nomJardin);
	    int nbr_parcelle = requete.getNbParcelle();
	    int[] tabId = new int[nbr_parcelle];
	    int[] tabdimX = new int[nbr_parcelle];  
	    int[] tabdimY = new int[nbr_parcelle];  
	    int[] tabposX = new int[nbr_parcelle];
	    int[] tabposY = new int[nbr_parcelle];

	    int[] tabmere = new int[nbr_parcelle];
	    boolean[] tabcouper = new boolean[nbr_parcelle];  
	    String[] taborientation = new String[nbr_parcelle];  
	    int[] tabfille1 = new int[nbr_parcelle];
	    int[] tabfille2 = new int[nbr_parcelle];
	    
	    tabId = requete.gettabId();
		tabdimX = requete.gettabdimX();
		tabdimY = requete.gettabdimY();
		tabposX = requete.gettabposX();
		tabposY = requete.gettabposY();

		tabmere = requete.gettabmere();
		tabcouper = requete.gettabcouper();
		taborientation = requete.gettaborientation();
		tabfille1 = requete.gettabfille1();
		tabfille2 = requete.gettabfille2();
		
		panneau.add(couper);
		panneau.add(action);
		panneau.add(historique);
		panneau.add(fusioner);
		panneau2.add(retour);
		panneau3.add(quitter);

	    contrainte.gridx = 2;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.CENTER; 
		contrainte.insets = new Insets(50, 160, 10, 10);
		this.add(panneau, contrainte);

		contrainte.gridx = 4;
		contrainte.insets = new Insets(50, 130, 10, 50);
		this.add(panneau2, contrainte);

		contrainte.gridx = 0;
		contrainte.insets = new Insets(50, 50, 10, 10);
		this.add(panneau3, contrainte);

	  	DessinerJardin dessin = new DessinerJardin(dimx,dimy,nbr_parcelle,tabdimX,tabdimY,tabposX,tabposY);

	    MouseJardinListener observateur2 = new MouseJardinListener(this.g,this.pan,dimx,dimy,dessin,nbr_parcelle,tabdimX,tabdimY,tabposX,tabposY,
	    	tabId,tabfille1,f,this,tabmere);
	    dessin.addMouseListener(observateur2);

		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.gridwidth = 5;
		contrainte.gridheight = 5;
		contrainte.weightx = 1.;
		contrainte.weighty = 1.;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.CENTER; 
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(dessin, contrainte);

		MenuJardinListener observateur = new MenuJardinListener(this.g,this.pan,this,f,nbr_parcelle);
		quitter.addActionListener(observateur);
		couper.addActionListener(observateur);
		retour.addActionListener(observateur);
		fusioner.addActionListener(observateur);
		action.addActionListener(observateur);
		historique.addActionListener(observateur);
	}
}
