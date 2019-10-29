/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import javax.swing.*;
import java.awt.*;

import src.vue.Page_connexion;
import src.vue.Menu_principal;

import src.controleur.Fermeture;

@SuppressWarnings("unchecked")

public class Gestionnaire_vue extends JFrame 
{
	/**
	 * Accueil de notre application
	 */
	public Gestionnaire_vue() 
	{

		super("Accueil");
        this.setSize(1300,800);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new Fermeture(this));
        this.setResizable(false);

        JPanel pan = new JPanel();

	    CardLayout gestionnaire = new CardLayout();
		pan.setLayout(gestionnaire);

		Page_connexion page1 = new Page_connexion(this,gestionnaire,pan);
		Menu_principal composant = new Menu_principal(this,gestionnaire,pan);

		JPanel page2 = new JPanel();
		JPanel page3 = new JPanel();

		page2 = composant.getMenu();
		page3 = composant.getMenuCreationJardin();

		pan.add(page1, "Page de connexion");
		pan.add(page2, "Menu principal");
		pan.add(page3, "Creation d'un jardin");

		this.add(pan);
  
        this.setVisible(true);
	}
}
