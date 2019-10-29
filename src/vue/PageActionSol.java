/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import src.controleur.MenuActionListener;

@SuppressWarnings("unchecked")

public class PageActionSol extends JPanel 
{

	private JComboBox liste1;

	/**
	 * Constructeur pour PageActionSol correspondant à la vue des actions sur le sol.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 */
	public PageActionSol(JFrame fenetre, CardLayout gestionnaire, JPanel pan, int id) 
	{
        /* Chargement de la premiere page qui correspond a la page de connexion */
        JLabel titre = new JLabel("Gestion des actions sur le sol", JLabel.CENTER);
         // new font size is 20
        titre.setFont(new Font(titre.getFont().getName(), titre.getFont().getStyle(), 20));
        // draw label border to verify the new label size
        titre.setBorder(new LineBorder(Color.BLACK));
        // change label size
        titre.setPreferredSize(new Dimension(350, 50));

        JButton actionsol = new JButton("Action sur le sol");
        JButton actionleg = new JButton("Action avec les légumes");
        JPanel panneau = new JPanel();

        JLabel titreaction = new JLabel("Action choisie :");
        Object[] elements = new Object[]{"AMENDER", "BECHER", "BINER", "BUTTER", "FUMER", "PAILLER"};
		liste1 = new JComboBox(elements);

		JButton valider = new JButton("Effectuer l'action");
		JButton annuler = new JButton("Quitter");

        panneau.add(actionleg);
        panneau.add(actionsol);

        LocalDate localDate = LocalDate.now();
		JLabel titredate = new JLabel("Date choisie : "+localDate);

		GridBagLayout gestion = new GridBagLayout();
	    this.setLayout(gestion);

	    GridBagConstraints contrainte = new GridBagConstraints();
  
  		/* placement du label titre */
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 0, 5, 0);
		this.add(titre, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.insets = new Insets(0, 0, 50, 0);
		this.add(panneau, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 3;
		contrainte.insets = new Insets(0, 10, 5, 0);
		this.add(titreaction, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 4;
		contrainte.insets = new Insets(0, 10, 50, 0);
		this.add(liste1, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 3;
		contrainte.insets = new Insets(0, 170, 5, 0);
		this.add(titredate, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 6;
		contrainte.insets = new Insets(0, 30, 30, 0);
		this.add(valider, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 6;
		contrainte.insets = new Insets(0, 220, 30, 0);
		this.add(annuler, contrainte);

		MenuActionListener observateur = new MenuActionListener(fenetre,gestionnaire,pan,id,localDate,liste1);
		actionsol.addActionListener(observateur);
		actionleg.addActionListener(observateur);
		valider.addActionListener(observateur);
		annuler.addActionListener(observateur);

	}
}
