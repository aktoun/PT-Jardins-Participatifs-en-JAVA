/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import src.controleur.MenuJardinListener;

import src.vue.DessinerParcelle;

import javax.swing.*;
import java.awt.*;

public class PageParcelle extends JPanel 
{

	private CardLayout g;
	private JPanel pan;

	/**
	 * Constructeur pour MouseJardinListener qui repère la sous-parcelle sélectionnée grâce au clique de souris.
	 * @param gestionnaire
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param dimx
	 *			int dimension en longueur de la parcelle racine.
	 * @param dimy
	 *			int dimension en hauteur de la parcelle racine.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param nbrParcelle
	 *			int correspondant au nombre de parcelle total dans le jardin.
	 * @param tabnum
	 *			int correspondant à la position de la parcelle sélectionnée dans le tableau d'id.
	 * @param posx
	 *			int[] est un tableau contenant les positions X du coin supérieur gauche de toutes les parcelles d'un jardin.
	 * @param posy
	 *			int[] est un tableau contenant les positions Y du coin supérieur gauche de toutes les parcelles d'un jardin.
	 * @param dimx2
	 *			int[] est un tableau contenant les dimensions en longueur de toutes les parcelles d'un jardin.
	 * @param dimy2
	 *			int[] est un tableau contenant les dimensions en hauteur de toutes les parcelles d'un jardin.
	 * @param id
	 *			int est l'id de la parcelle sélectionnée.
	 * @param pagejardin
	 *			JPanel correspondant à la page de la parcelle racine.
	 * @param idmere
	 *			int est l'id mere de la parcelle sélectionnée.
	 * @param tabid
	 *			int[] est un tableau contenant les id de toutes les parcelles d'un jardin.
	 */
	public PageParcelle(CardLayout gestionnaire,JPanel pan, int dimx, int dimy, JFrame f, int nbr_parcelle,int tabnum, int[] posx, 
		int[] posy, int[] dimx2, int[] dimy2, int id, JPanel pagejardin, int idmere,int[] tabid) 
	{
		this.g=gestionnaire;
		this.pan=pan;

		GridBagLayout gestion = new GridBagLayout();
		this.setLayout(gestion);

		GridBagConstraints contrainte = new GridBagConstraints();

		JButton quitter = new JButton("Quitter le jardin");
		JButton retour = new JButton("Annuler la sélection");
		JButton fusioner = new JButton("Réunir");
	    JButton couper = new JButton("Séparer");
	    JButton action = new JButton("Actions sur la parcelle");
	    JButton historique = new JButton("Accéder à l'historique");
	    JPanel panneau = new JPanel();
	    JPanel panneau2 = new JPanel();
	    JPanel panneau3 = new JPanel();

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

	  	DessinerParcelle dessin = new DessinerParcelle(dimx,dimy,nbr_parcelle,tabnum,dimx2,dimy2,posx,posy,idmere,tabid);

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

		MenuJardinListener observateur = new MenuJardinListener(this.g,this.pan,this,f,nbr_parcelle,id,pagejardin,idmere,dimx,dimy);
		quitter.addActionListener(observateur);
		couper.addActionListener(observateur);
		retour.addActionListener(observateur);
		fusioner.addActionListener(observateur);
		action.addActionListener(observateur);
		historique.addActionListener(observateur);
	}
}
