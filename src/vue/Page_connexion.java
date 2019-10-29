/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import src.controleur.AccueilListener;

import src.vue.Background;

@SuppressWarnings("unchecked")

public class Page_connexion extends JPanel 
{
	/**
	 * Constructeur pour Page_connexion correspondant à la vue de la page d'identification.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 */
	public Page_connexion(JFrame fenetre, CardLayout gestionnaire, JPanel pan) 
	{
        /* Chargement de la premiere page qui correspond a la page de connexion */
        JLabel login = new JLabel("Login");
        JTextField login1 = new JTextField();
        JLabel mdp = new JLabel("Mot de Passe");
        JPasswordField mdp1 = new JPasswordField();
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
		GridBagLayout gestion = new GridBagLayout();
	    this.setLayout(gestion);

	    JLabel titre = new JLabel("Page d'accueil", JLabel.CENTER);
         // new font size is 20
        titre.setFont(new Font(titre.getFont().getName(), titre.getFont().getStyle(), 30));
        // draw label border to verify the new label size
        titre.setBorder(new LineBorder(Color.BLACK));
        // change label size
        titre.setPreferredSize(new Dimension(440, 50));

	    GridBagConstraints contrainte = new GridBagConstraints();

	    /* placement du label login */
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 450, 70, 0);
		this.add(titre, contrainte);
  
  		/* placement du label login */
		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 300, 0, 0);
		this.add(login, contrainte);

		/*placement du label mdp */
		contrainte.gridx = 0;
		contrainte.gridy = 4;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.;
		contrainte.weighty = 0.;
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.anchor = GridBagConstraints.BASELINE_LEADING; 
		contrainte.insets = new Insets(0, 300, 0, 0);
		this.add(mdp, contrainte);

		/* placement du bouton de connexion */
		contrainte.gridx = 0; 
		contrainte.gridy = 6;
		contrainte.gridheight = 1; 
		contrainte.fill = GridBagConstraints.NONE;
		contrainte.gridx = 0; 
		contrainte.gridwidth = GridBagConstraints.RELATIVE; 
		contrainte.anchor = GridBagConstraints.BASELINE_TRAILING;
		contrainte.weightx = 1.;
		contrainte.insets = new Insets(30, 0, 0, 670);
		this.add(valider, contrainte);

		/* placement du bouton reset */
		contrainte.gridx = 0; 
		contrainte.gridy = 6;
		contrainte.weightx = 0.; 
		contrainte.insets = new Insets(0, 50, 0, 530);
		this.add(annuler, contrainte);

		contrainte.insets=new Insets(10,450,5,400);
		contrainte.gridx=0;
		contrainte.gridy=2;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.anchor=GridBagConstraints.BASELINE;
		contrainte.fill=GridBagConstraints.HORIZONTAL;
		this.add(login1, contrainte);

		contrainte.insets=new Insets(10,450,0,400);
		contrainte.gridx=0;
		contrainte.gridy=4;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.anchor=GridBagConstraints.BASELINE;
		contrainte.fill=GridBagConstraints.HORIZONTAL;
		this.add(mdp1, contrainte);

		AccueilListener observateur = new AccueilListener(login1,mdp1,fenetre,gestionnaire,pan);
		valider.addActionListener(observateur);
		annuler.addActionListener(observateur);

		/*Background bg = new Background();
		fenetre.setContentPane(bg);
		this.setOpaque(true);*/

	}
}
