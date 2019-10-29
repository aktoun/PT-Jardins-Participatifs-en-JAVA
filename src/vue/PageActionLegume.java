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

import src.modele.LegumeBase;

@SuppressWarnings("unchecked")

public class PageActionLegume extends JPanel 
{

	private JComboBox liste1;

	/**
	 * Constructeur pour PageActionLegume correspondant à la vue des actions aves les légumes.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 */
	public PageActionLegume(JFrame fenetre, CardLayout gestionnaire, JPanel pan, int id) 
	{
        /* Chargement de la premiere page qui correspond a la page de connexion */
        JLabel titre = new JLabel("Gestion des actions avec les légumes", JLabel.CENTER);
         // new font size is 20
        titre.setFont(new Font(titre.getFont().getName(), titre.getFont().getStyle(), 20));
        // draw label border to verify the new label size
        titre.setBorder(new LineBorder(Color.BLACK));
        // change label size
        titre.setPreferredSize(new Dimension(440, 50));

        JButton actionsol = new JButton("Action sur le sol");
        JButton actionleg = new JButton("Action avec les légumes");
        JPanel panneau = new JPanel();

        panneau.add(actionleg);
        panneau.add(actionsol);

        JLabel titrelegume = new JLabel("Ajout de nouveau légume :");
        JButton ajouter = new JButton("Ajouter un légume");
        JButton supprimer = new JButton("Supprimer un légume");
        JTextField champ = new JTextField();

        JLabel titreaction = new JLabel("Action choisie :");
        Object[] elements = new Object[]{"ARRACHER", "ECLAIRCIR", "RECOLTER", "SEMER", "TRANSPLANTER", "NULL"};
		liste1 = new JComboBox(elements);

		LocalDate localDate = LocalDate.now();
		JLabel titredate = new JLabel("Date choisie : "+localDate);

		JLabel titrescroll = new JLabel("Liste des légumes disponible :");

        LegumeBase legumeaffichage = new LegumeBase();
       	JList<String> listelegume = legumeaffichage.getAllLegume();

        JScrollPane scrollPane = new JScrollPane(listelegume, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        listelegume.setPreferredSize(new Dimension(200, 200));

        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Quitter");

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
		contrainte.insets = new Insets(0, 40, 30, 0);
		this.add(panneau, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 3;
		contrainte.insets = new Insets(0, 10, 5, 0);
		this.add(titreaction, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 3;
		contrainte.insets = new Insets(0, 230, 5, 0);
		this.add(titredate, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 4;
		contrainte.insets = new Insets(0, 10, 30, 0);
		this.add(liste1, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 5;
		contrainte.insets = new Insets(0, 10, 5, 0);
		this.add(titrelegume, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 6;
		contrainte.insets = new Insets(0, 10, 10, 0);
		this.add(ajouter, contrainte);

		contrainte.insets=new Insets(10,180,0,40);
		contrainte.gridx=0;
		contrainte.gridy=6;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.anchor=GridBagConstraints.BASELINE;
		contrainte.fill=GridBagConstraints.HORIZONTAL;
		this.add(champ, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 7;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 10, 30, 0);
		this.add(supprimer, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 8;
		contrainte.insets = new Insets(0, 10, 5, 0);
		this.add(titrescroll, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 9;
		contrainte.insets = new Insets(0, 10, 30, 0);
		this.add(scrollPane, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 10;
		contrainte.insets = new Insets(0, 120, 30, 0);
		this.add(valider, contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 10;
		contrainte.insets = new Insets(0, 220, 30, 0);
		this.add(annuler, contrainte);

		MenuActionListener observateur = new MenuActionListener(fenetre,gestionnaire,pan,id,localDate,liste1,listelegume,scrollPane,legumeaffichage,champ);
		actionsol.addActionListener(observateur);
		actionleg.addActionListener(observateur);
		valider.addActionListener(observateur);
		annuler.addActionListener(observateur);
		ajouter.addActionListener(observateur);
		supprimer.addActionListener(observateur);
	}
}
