/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import src.modele.VerifNomJardin;
import src.modele.JardinFactory;

import src.vue.Page_Jardin;

@SuppressWarnings("unchecked")

public class CreationJardinListener implements ActionListener
{
	
	private JTextField nom_jardin;
	private JTextField taillex;
	private JTextField tailley;

	private CardLayout g;
	private JPanel pan;
	private JFrame f;
	private boolean condition;
	private JList<String> afficherNomJardin;
	private JScrollPane scroll;
	private JardinFactory jardin;

	/**
	 * Constructeur pour CreationJardinListener qui gère le formulaire de création d'un jardin.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param champ
	 *			JTextField correspondant à la zone de texte pour récuperer le nom du jardin.
	 * @param champ2
	 *			JTextField correspondant à la zone de texte pour récuperer la taille du jardin en longueur.
	 * @param champ3
	 *			JTextField correspondant à la zone de texte pour récuperer la taille du jardin en hauteur.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param scroll
	 *			JScrollPane qui va contenir l'ensemble des jardins.
	 * @param jardin
	 *			JardinFactory permet de mettre à jour le scrollPane après une supression ou une création de jardin.
	 */
	public CreationJardinListener(JFrame f,JTextField champ, JTextField champ2,JTextField champ3,CardLayout g,JPanel pan, JScrollPane scroll, JardinFactory jardin)
	{
		this.g=g;
		this.pan=pan;
		this.nom_jardin=champ;
		this.taillex=champ2;
		this.tailley=champ3;
		this.f=f;
		this.scroll=scroll;
		this.jardin = jardin;
	}

	public void actionPerformed(ActionEvent evenement)
	{
		String text=evenement.getActionCommand();
		String jardin;
		int dimx;
		int dimy;

		/* Lorsque l'utilisateur appuie sur valider afin de valider la création du jardin */
		if (text.equals("Valider"))
		{	
			/* Vérification des champs s'ils ne sont pas vide */
			if ((this.nom_jardin.getText().equals("")) || (this.taillex.getText().equals("")) || (this.tailley.getText().equals("")))
			{
				JOptionPane.showMessageDialog(this.f, "Veuillez remplir tous les champs.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}else{

				/* Vérification des champs acceptant seulement des entiers */
				try
				{
					jardin = this.nom_jardin.getText();
					dimx = Integer.parseInt(this.taillex.getText());
					dimy = Integer.parseInt(this.tailley.getText());

					/* Vérification des dimensions du jardin */
					if((dimx>1200) || (dimx<600) || (dimy>600) || (dimy<300))
					{
						JOptionPane.showMessageDialog(this.f, "Veuillez respecter les dimensions maximales et minimales.", "Message d'erreur", 
						JOptionPane.INFORMATION_MESSAGE);
					}else{
						VerifNomJardin verification = new VerifNomJardin(jardin);
						this.condition = verification.nomExiste();

						/* Vérification du nom de jardin s'il est n'existe pas dans la BDD */
						if (this.condition==true)
						{
							JOptionPane.showMessageDialog(this.f, "Ce nom de jardin existe déjà.", "Message d'erreur", 
							JOptionPane.INFORMATION_MESSAGE);
						}else{
							this.jardin.AddJardin(jardin, dimx, dimy);
							this.afficherNomJardin = this.jardin.getAllJardin();
							this.scroll.setViewportView(this.afficherNomJardin);
							
							Page_Jardin page4 = new Page_Jardin(this.g,this.pan,dimx,dimy,this.f,jardin);
							this.pan.add(page4, "Jardin Mère");

							this.nom_jardin.setText("");
							this.taillex.setText("");
							this.tailley.setText("");

							this.g.show(this.pan, "Jardin Mère");
						}
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(this.f, "Veuillez entrer des entiers pour les dimensions.", "Message d'erreur", 
					JOptionPane.INFORMATION_MESSAGE);
				}
			}
		/* Lorsque l'utilisateur appuie sur annuler afin d'annuler la création de son jardin */
		}else if (text.equals("Annuler")){
			this.g.previous(this.pan);
		}
	}
}