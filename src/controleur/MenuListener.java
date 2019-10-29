/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import src.modele.JardinFactory;

import src.vue.Page_Jardin;

public class MenuListener implements ActionListener
{
	
	private CardLayout g;
	private JPanel pan;
	private JList liste;
	private JFrame f;
	private JScrollPane scroll;
	private JardinFactory jardin;
	private String nomjardin;

	private int dimx;
	private int dimy;

	/**
	 * Constructeur pour MenuListener les boutons du menu principal.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 */
	public MenuListener(JFrame f,CardLayout g,JPanel pan)
	{
		this.g=g;
		this.pan=pan;
		this.f=f;
	}

	/**
	 * Constructeur pour MenuListener les boutons du menu principal.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param list
	 *			JList est une liste de string contenant tous les noms des jardins existants.
	 * @param scroll
	 *			JScrollPane qui va contenir l'ensemble des jardins.
	 * @param jardin
	 *			JardinFactory permet de mettre à jour le scrollPane après une supression ou une création de jardin.
	 */
	public MenuListener(JFrame f,CardLayout g,JPanel pan, JList list, JScrollPane scroll, JardinFactory jardin)
	{
		this.g=g;
		this.pan=pan;
		this.f=f;
		this.scroll = scroll;
		this.liste = list;
		this.jardin = jardin;
	}

	public void actionPerformed(ActionEvent evenement)
	{

		String text=evenement.getActionCommand();
		GridBagConstraints contrainte = new GridBagConstraints();
 
 		/* Lorsque l'utilisateur appuie sur le bouton, il accède à une page dédiée à la création des jardins */ 
		if (text.equals("Créer un jardin"))
		{
			this.g.next(this.pan);

		}else{

			this.liste = jardin.getJListJardin();

			/* Vérifie que l'utilisateur a préalablement sélectionné un jardin dans le scrollPane */ 
			if (this.liste.getSelectedValue() != null)
			{

				/* Lorsque l'utilisateur appuie sur le bouton, il accéde à son jardin */ 
				if (text.equals("Voir Jardin"))
				{
					
					this.nomjardin=(String)this.liste.getSelectedValue();
					this.dimx=this.jardin.getX(this.nomjardin);
					this.dimy=this.jardin.getY(this.nomjardin);

					Page_Jardin page4 = new Page_Jardin(this.g,this.pan,this.dimx,this.dimy,this.f,this.nomjardin);
					this.pan.add(page4, "Jardin Mère");
					page4.revalidate();

					this.g.show(this.pan, "Jardin Mère");

				/* Lorsque l'utilisateur appuie sur le bouton, il accède à une page présentant l'historique du jardin */ 
				}else if (text.equals("Supprimer un jardin")){
					String jardin = (String)this.liste.getSelectedValue();
					int retour = JOptionPane.showConfirmDialog(this.f, "Êtes-vous bien sûr de vouloir supprimer ce jardin ?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
					
					/* retour = 2 = annuler et retour = 0 = confirmer */
					if (retour==0)
					{
						this.jardin.DeleteJardin(jardin);
						this.liste = this.jardin.getAllJardin();
						this.scroll.setViewportView(this.liste);
					}
				}
			}else{
				JOptionPane.showMessageDialog(this.f, "Veuillez selectionner un jardin pour y accéder, le supprimer ou voir son historique.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Méthode pour récupérer le nom du jardin.
	 * @return String this.nomjardin
	 */
	public String getNom()
	{
		return this.nomjardin;
	}
}