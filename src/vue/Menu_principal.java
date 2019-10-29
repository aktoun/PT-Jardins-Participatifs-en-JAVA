/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import javax.swing.*;
import java.awt.*;

import src.controleur.MenuListener;
import src.controleur.CreationJardinListener;
import javax.swing.border.LineBorder;

import src.modele.TrouverJardin;
import src.modele.JardinFactory;

@SuppressWarnings("unchecked")

public class Menu_principal 
{
	private JPanel menu;
	private JPanel creationJardin;

	/**
	 * Constructeur pour Menu_principal correspondant à la vue du menu principal.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 */
	public Menu_principal(JFrame fenetre, CardLayout gestionnaire, JPanel pan) 
	{

	    GridBagConstraints contrainte = new GridBagConstraints();

		/* Chargement de la deuxieme page qui correspond au menu principal */
		JPanel page2 = new JPanel();
		JButton creer = new JButton("Créer un jardin");
        JButton supprimer = new JButton("Supprimer un jardin");

        /* Affichage des jardin dispo sur la page principal*/
        JardinFactory jardinAffichage = new JardinFactory();
        JList<String> afficherNomJardin = jardinAffichage.getAllJardin();

        JScrollPane scrollPane = new JScrollPane(afficherNomJardin, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        afficherNomJardin.setPreferredSize(new Dimension(200, 200));

        /* Ajout des boutons d'intéraction sur les jardins */
        JButton modifier = new JButton("Voir Jardin");

        JLabel titre = new JLabel("Menu principal", JLabel.CENTER);
         // new font size is 20
        titre.setFont(new Font(titre.getFont().getName(), titre.getFont().getStyle(), 30));
        // draw label border to verify the new label size
        titre.setBorder(new LineBorder(Color.BLACK));
        // change label size
        titre.setPreferredSize(new Dimension(440, 50));

        JLabel titre2 = new JLabel("Création d'un jardin", JLabel.CENTER);
         // new font size is 20
        titre2.setFont(new Font(titre2.getFont().getName(), titre2.getFont().getStyle(), 30));
        // draw label border to verify the new label size
        titre2.setBorder(new LineBorder(Color.BLACK));
        // change label size
        titre2.setPreferredSize(new Dimension(440, 50));

        GridBagLayout gestion3 = new GridBagLayout();
	    page2.setLayout(gestion3);

	    contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 230, 60, 0);
		page2.add(titre, contrainte);

        /* placement de la liste à scroller */
		contrainte.gridx = 0;
		contrainte.gridy = 4;
		contrainte.insets = new Insets(0, 40, 0, 0);
		page2.add(scrollPane, contrainte);

		/* placement du bouton creer */
		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.insets = new Insets(0, 210, 40, 0);
		page2.add(creer, contrainte);


		/* placement du bouton supprimer */
		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.insets = new Insets(0, 380, 40, 20);
		page2.add(supprimer, contrainte);

		/* placement du bouton pour voir le jardin */
		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.insets = new Insets(0, 580, 40, 20);
		page2.add(modifier, contrainte);

		this.menu=page2;

		/* Chargement de la troisieme page qui correspond au formulaire de creation jardin */
		JPanel page3 = new JPanel();

		JLabel nom_jardin = new JLabel("Nom du jardin");
        JTextField nom = new JTextField();
        JLabel dim_x = new JLabel("Dimension du jardin en longueur (entre 600-1200)");
        JLabel dim_y = new JLabel("Dimension du jardin en largeur (entre 300-600)");
	    JTextField taille1 = new JTextField();
	    JTextField taille2 = new JTextField();
        JButton valider2 = new JButton("Valider");
        JButton annuler2 = new JButton("Annuler");

        GridBagLayout gestion2 = new GridBagLayout();
	    page3.setLayout(gestion2);

	    contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 430, 60, 0);
		page3.add(titre2, contrainte);

	    /* placement du label nom de jardin */
		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 260, 15, 0);
		page3.add(nom_jardin, contrainte);

		/* placement de la zone de texte pour le nom de jardin */
		contrainte.insets=new Insets(10,630,0,280);
		contrainte.gridx=0;
		contrainte.gridy=2;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.anchor=GridBagConstraints.BASELINE;
		contrainte.fill=GridBagConstraints.HORIZONTAL;
		page3.add(nom, contrainte);

		/*placement du label dimension en longueur */
		contrainte.gridx = 0;
		contrainte.gridy = 4;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 260, 15, 0);
		page3.add(dim_x, contrainte);

		/* placement de la zone de texte pour la dimension X */
		contrainte.insets=new Insets(10,630,0,280);
		contrainte.gridx=0;
		contrainte.gridy=4;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.anchor=GridBagConstraints.BASELINE;
		contrainte.fill=GridBagConstraints.HORIZONTAL;
		page3.add(taille1, contrainte);

		/*placement du label dimension en hauteur */
		contrainte.gridx = 0;
		contrainte.gridy = 6;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 260, 0, 0);
		page3.add(dim_y, contrainte);

		/* placement de la zone de texte pour la dimension Y */
		contrainte.insets=new Insets(10,630,0,280);
		contrainte.gridx=0;
		contrainte.gridy=6;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.anchor=GridBagConstraints.BASELINE;
		contrainte.fill=GridBagConstraints.HORIZONTAL;
		page3.add(taille2, contrainte);

		/* placement du bouton de validation */
		contrainte.gridx = 0; 
		contrainte.gridy = 8;
		contrainte.gridheight = 1; 
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.gridx = 0; 
		contrainte.gridwidth = GridBagConstraints.RELATIVE; 
		contrainte.anchor = GridBagConstraints.BASELINE_TRAILING;
		contrainte.weightx = 1.;
		contrainte.insets = new Insets(30, 0, 0, 670);
		page3.add(valider2, contrainte);

		/* placement du bouton annuler */
		contrainte.gridx = 0; 
		contrainte.gridy = 8;
		contrainte.weightx = 0.; 
		contrainte.insets = new Insets(0, 50, 0, 530);
		page3.add(annuler2, contrainte);

		this.creationJardin=page3;

		MenuListener observateur2 = new MenuListener(fenetre,gestionnaire,pan);
		creer.addActionListener(observateur2);

		MenuListener observateur2Special = new MenuListener(fenetre,gestionnaire,pan,afficherNomJardin,scrollPane,jardinAffichage);
		modifier.addActionListener(observateur2Special);
		supprimer.addActionListener(observateur2Special);
		
		CreationJardinListener observateur3 = new CreationJardinListener(fenetre,nom,taille1,taille2,gestionnaire,pan,scrollPane,jardinAffichage);
		valider2.addActionListener(observateur3);
		annuler2.addActionListener(observateur3);
	}

	/**
	 * Méthode pour récupérer le page Menu_principal.
	 * @return this.menu
	 * 			JPanel correspondant au menu principal
	 */
	public JPanel getMenu()
	{
		return this.menu;
	}

	/**
	 * Méthode pour récupérer le page de création de jardin.
	 * @return this.creationmenu
	 * 			JPanel correspondant à la page de création de jardin
	 */
	public JPanel getMenuCreationJardin()
	{
		return this.creationJardin;
	}
}
