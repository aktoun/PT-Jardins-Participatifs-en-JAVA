/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.Stack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import src.modele.JardinFactory;
import src.modele.ParcelleFactory;
import src.modele.Orientation;
import src.modele.ActionSol;
import src.modele.ActionLegume;
import src.modele.ActionSolType;

import src.vue.DessinerJardin;
import src.vue.Page_Jardin;
import src.vue.GestionnaireAction;
import src.vue.JTableHistorique;

public class MenuJardinListener implements ActionListener
{

	private CardLayout g;
	private JPanel pan;
	private JPanel page;
	private JPanel pagejardin;
	private JFrame f;
	private int nbrParcelle;
	private int idmere;
	private int progressionParcelle = 0;
	private Stack<Integer> stackofid = new Stack<Integer>();
	private int dimx;
	private int dimy;
	private String nomjardin;
	private int id;

	/**
	 * Constructeur pour MenuJardinListener qui gère les actions sur la parcelle racine.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param page
	 *			JPanel correspondant à la page affichant la parcelle mère.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param nbParcelle
	 *			int qui contient le nombre de parcelle contenue dans un jardin.
	 */

	public MenuJardinListener(CardLayout g,JPanel pan, JPanel page, JFrame f, int nbrParcelle)
	{
		this.g=g;
		this.pan=pan;
		this.page=page;
		this.f=f;
		this.nbrParcelle=nbrParcelle;
	}

	/**
	 * Constructeur pour MenuJardinListener qui gère les actions sur la parcelle sélectionnée.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param page
	 *			JPanel correspondant à la page affichant la parcelle sélectionnée.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param nbParcelle
	 *			int qui contient le nombre de parcelle contenue dans un jardin.
	 * @param id
	 *			int qui contient l'id de la sous-parcelle sélectionnée.
	 * @param pagejardin
	 *			JPanel correspondant à la page affichant la parcelle mère.
	 * @param idmere
	 *			int qui contient l'id de la mère de la sous-parcelle sélectionnée.
	 * @param dimx
	 *			int dimension en longueur de la parcelle racine.
	 * @param dimy
	 *			int dimension en hauteur de la parcelle racine.
	 */
	public MenuJardinListener(CardLayout g,JPanel pan, JPanel page, JFrame f, int nbrParcelle, int id ,JPanel pagejardin, int idmere, int dimx, int dimy)
	{
		this.g=g;
		this.pan=pan;
		this.page=page;
		this.pagejardin=pagejardin;
		this.idmere=idmere;
		this.f=f;
		this.nbrParcelle=nbrParcelle;
		this.stackofid.push(id);
		this.id=id;
		this.dimx=dimx;
		this.dimy=dimy;
	}

	public void actionPerformed(ActionEvent evenement)
	{
		String text=evenement.getActionCommand();

		/* Lorsque l'utilisateur appuie sur le bouton quitter, il revient sur le menu principal */ 
		if (text.equals("Quitter le jardin"))
		{
			while(!this.stackofid.empty())
			{
				this.stackofid.pop();
			}

			this.pan.remove(this.page);
			this.g.show(this.pan, "Menu principal");

		/* Permet à l'utilisateur de revenir sur la parcelle racine à partir d'une sous-parcelle sélectionnée */ 
		}else if (text.equals("Annuler la sélection")){
			if(!this.stackofid.empty())
			{	
				this.stackofid.pop();
				this.pan.remove(this.page);
				this.g.show(this.pan, "Jardin Mère");
			}else{
				JOptionPane.showMessageDialog(this.f, "Aucune parcelle sélectionnée.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}

		/* Lorsque l'utilisateur a sélectionné une sous-parcelle, il peut la séparer en deux sous-parcelles */ 
		} else if(text.equals("Séparer")){

			if(!this.stackofid.empty())
			{
				ActionLegume gestion = new ActionLegume();
				boolean retour = gestion.VerifPresenceCulture(this.id);

				if((retour!=true))
				{
					String choices[]={ "HORIZONTAL", "VERTICAL"};
					int reponse=JOptionPane.showOptionDialog(this.f, "Dans quel sens voulez-vous séparer votre jardin ?",
					 "Faites votre choix", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
					
					ParcelleFactory parcelle = new ParcelleFactory();
					int id;

					/* reponse = 1 = VERTICAL et reponse = 0 = Horizontal */
					if (reponse==0)
					{
						id = this.stackofid.pop();
						parcelle.SplitParcelle(Orientation.HORIZONTAL, id);
						this.nomjardin = parcelle.getNomJardin(id);
						this.pan.remove(this.page);
						this.pan.remove(this.pagejardin);
						Page_Jardin page4 = new Page_Jardin(this.g,this.pan,this.dimx,this.dimy,this.f,this.nomjardin);
						this.pan.add(page4, "Jardin Mère");
						this.g.show(this.pan, "Jardin Mère");

						JOptionPane.showMessageDialog(this.f, "Séparation Horizontale effectué, mise à jour du jardin accomplie !", "Succès", 
						JOptionPane.INFORMATION_MESSAGE);
					}else if (reponse==1){	
						id = this.stackofid.pop();
						parcelle.SplitParcelle(Orientation.VERTICAL, id);
						this.nomjardin = parcelle.getNomJardin(id);
						this.pan.remove(this.page);
						this.pan.remove(this.pagejardin);
						Page_Jardin page4 = new Page_Jardin(this.g,this.pan,this.dimx,this.dimy,this.f,this.nomjardin);
						this.pan.add(page4, "Jardin Mère");
						this.g.show(this.pan, "Jardin Mère");
						
						JOptionPane.showMessageDialog(this.f, "Séparation Verticale effectué, mise à jour du jardin accomplie !", "Succès", 
						JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this.f, "Il y a une culture présente cette parcelle, impossible de la diviser.", "Message d'erreur", 
					JOptionPane.INFORMATION_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(this.f, "Veuillez d'abord sélectionner une parcelle pour la diviser en deux.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}
			
		/* Lorsque l'utilisateur a sélectionné une sous-parcelle, il peut décider de la fusioner avec sa parcelle jumelle. */
		}else if(text.equals("Réunir")){
			if(!this.stackofid.empty())
			{
				String choices[]={ "OUI", "NON"};
				int reponse=JOptionPane.showOptionDialog(this.f, "Êtes-vous sûr de vouloir réunir la parcelle sélectionnée avec sa parcelle jumelle ? Cette action détruira toutes les sous-parcelles, y compris les cultures sur ces parcelles.",
				 "Faites votre choix", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

				/* reponse = 1 = OUI et reponse = 0 = NON */
				if (reponse==0 && this.idmere!=0)
				{
					ParcelleFactory parcelle = new ParcelleFactory();

					parcelle.reset(this.idmere);
					this.nomjardin = parcelle.getNomJardin(idmere);
					this.pan.remove(this.page);
					this.pan.remove(this.pagejardin);
					Page_Jardin page4 = new Page_Jardin(this.g,this.pan,this.dimx,this.dimy,this.f,this.nomjardin);
					this.pan.add(page4, "Jardin Mère");
					this.g.show(this.pan, "Jardin Mère");

					JOptionPane.showMessageDialog(this.f, "Fusion de la parcelle fille effectué, mise à jour du jardin accomplie !", "Succès", 
					JOptionPane.INFORMATION_MESSAGE);
				}else if(reponse==0 && this.idmere==0){
					JOptionPane.showMessageDialog(this.f, "Il n'y a pas de sous-parcelles à réunir. Vous êtes sur la parcelle racine.", "Message d'erreur", 
					JOptionPane.INFORMATION_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(this.f, "Veuillez d'abord sélectionner une parcelle pour la réunir avec sa parcelle jumelle.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}
		
		/* Permet d'accéder à une page dédiée à la réalisation d'actions sur la parcelle */
		}else if(text.equals("Actions sur la parcelle")){

			if(!this.stackofid.empty())
			{
				GestionnaireAction frame = new GestionnaireAction(this.id);

			}else{
				JOptionPane.showMessageDialog(this.f, "Veuillez d'abord sélectionner une parcelle pour effectué une action dessus.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}

		/* Permet d'accéder à une page dédiée à l'historique de la parcelle */
		}else if(text.equals("Accéder à l'historique")){

			if(!this.stackofid.empty())
			{
				JTableHistorique tableauHistorique = new JTableHistorique(this.id);
			}else{
				JOptionPane.showMessageDialog(this.f, "Veuillez d'abord sélectionner une parcelle pour accéder à son historique.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}