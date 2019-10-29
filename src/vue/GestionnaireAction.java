/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import src.controleur.Fermeture;

import src.vue.PageActionSol;
import src.vue.PageActionLegume;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unchecked")

public class GestionnaireAction extends JFrame 
{
	/**
     * Constructeur GestionnaireAction Accueil pour la création des actions sur le sol et les légumes.
     * @param id
     *          int id de la parcelle selectionnée.
     */
	public GestionnaireAction(int id) 
	{

		super("Gestionnaire des actions sur la parcelle");
        this.setSize(700,700);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new Fermeture(this));
        this.setResizable(false);

        JPanel pan = new JPanel();

	    CardLayout gestionnaire = new CardLayout();
		pan.setLayout(gestionnaire);

		PageActionSol page1 = new PageActionSol(this,gestionnaire,pan,id);
		PageActionLegume page2 = new PageActionLegume(this,gestionnaire,pan,id);

		pan.add(page2, "Page action legume");
		pan.add(page1, "Page action sol");

		this.add(pan);
  
        this.setVisible(true);
	}
}
