/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;
import java.time.LocalDate;

import src.modele.ActionSol;
import src.modele.ActionSolType;
import src.modele.ActionLegume;
import src.modele.ActionLegumeType;
import src.modele.LegumeBase;
import src.modele.VerifNomLegume;

public class MenuActionListener implements ActionListener
{
	
	private CardLayout g;
	private JPanel pan;
	private JFrame f;
	private int id;
	private LocalDate date;

	private JComboBox listeaction;
	private String actionsol;
	private String actionleg;
	private ActionSolType typeactionsol;
	private ActionLegumeType typeactionleg;

	private ActionLegume gestionleg = new ActionLegume();
	private ActionSol gestionsol = new ActionSol();

	private JList liste;
	private JScrollPane scroll;
	private LegumeBase legume;
	private JTextField champ;

	/**
	 * Constructeur pour MenuActionListener qui gère les boutons du menu des actions.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param id
	 *			int correspondant à la parcelle sur laquelle on effectue les actions.
	 * @param date
	 *			LocalDate pour récupérer le jour où l'action a été réalisé.
	 * @param action
	 *			JComboBox est un menu déroulant proposant toutes les actions possibles liées avec le sol.
	 */
	public MenuActionListener(JFrame f,CardLayout g,JPanel pan, int id, LocalDate date, JComboBox action)
	{
		this.g=g;
		this.pan=pan;
		this.f=f;
		this.id=id;
		this.date=date;
		this.listeaction=action;
	}

	/**
	 * Constructeur pour MenuActionListener qui gère les boutons du menu des actions.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param id
	 *			int correspondant à la parcelle sur laquelle on effectue les actions.
	 * @param date
	 *			LocalDate pour récupérer le jour où l'action a été réalisé.
	 * @param action
	 *			JComboBox est un menu déroulant proposant toutes les actions possibles liées avec le sol.
	 * @param list
	 *			JList contenant la liste des légumes dans le JScrollPane.
	 * @param scroll
	 *			JScrollPane propose tout une liste de légumes à semer.
	 * @param legume
	 *			LegumeBase est un objet servant à la création et à la supression de légumes dans la liste.
	 * @param champ
	 *			JTextField permettant de récupérer le légume que l'utilisateur veut rajouter.
	 */
	public MenuActionListener(JFrame f,CardLayout g,JPanel pan, int id, LocalDate date, JComboBox action, JList list, JScrollPane scroll, 
		LegumeBase legume,JTextField champ)
	{
		this.g=g;
		this.pan=pan;
		this.f=f;
		this.id=id;
		this.date=date;
		this.listeaction=action;

		this.scroll = scroll;
		this.liste = list;
		this.legume = legume;
		this.champ = champ;
	}

	public void actionPerformed(ActionEvent evenement)
	{
		String text=evenement.getActionCommand();
		boolean retour;
		String nomlegume;
 	
 		/* Permet d'accéder aux actions liées aux légumes */
		if (text.equals("Action avec les légumes"))
		{
			this.g.show(this.pan, "Page action legume");

		/* Permet d'accéder aux actions liées au sol */
		}else if (text.equals("Action sur le sol")){
			this.g.show(this.pan, "Page action sol");

		/* Bouton de validation pour une action au sol */
		}else if (text.equals("Effectuer l'action")){

			this.actionsol = (String)this.listeaction.getSelectedItem();
			this.typeactionsol = gestionsol.StringToAction(this.actionsol);
			gestionsol.NouvelleActionSol(this.date,this.typeactionsol,this.id);
			JOptionPane.showMessageDialog(this.f, "Action réalisée sur la parcelle", "Succès", JOptionPane.INFORMATION_MESSAGE);
	
		/* Bouton de validation pour une action aux légumes */
		}else if (text.equals("Valider")){

			this.actionleg = (String)this.listeaction.getSelectedItem();

			/* Cas de l'action SEMER */
			if((this.actionleg.equals("SEMER")))
			{
				/* Vérifie que l'utilisateur a préalablement sélectionné un légume dans le scrollPane */ 
				if (this.liste.getSelectedValue() != null)
				{
					nomlegume=(String)this.liste.getSelectedValue();
					retour = gestionleg.VerifPresenceCulture(this.id);

					/* On vérifie si un légume est déjà planté sur la parcelle ou pas */
					if(retour == true)
					{
						JOptionPane.showMessageDialog(this.f, "Un légume est déjà planté sur cette parcelle.", 
						"Message d'erreur", JOptionPane.INFORMATION_MESSAGE);
					}else{
						this.typeactionleg = gestionleg.StringToAction(this.actionleg);
						gestionleg.NouvelleActionLegume(this.date,this.typeactionleg,this.id,nomlegume);
						JOptionPane.showMessageDialog(this.f, "Le légume a été planté.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					}

				}else{
					JOptionPane.showMessageDialog(this.f, "Veuillez selectionner un légume parmi la liste pour le semer sur la parcelle.", "Message d'erreur", 
					JOptionPane.INFORMATION_MESSAGE);
				}

			/* Cas de l'action NULL où on ne fait rien */
			}else if((this.actionleg.equals("NULL"))){
				JOptionPane.showMessageDialog(this.f, "Aucune action à menée.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);

			}else{
				retour = gestionleg.VerifPresenceCulture(this.id);
				
				if(retour == true)
				{
					this.typeactionleg = gestionleg.StringToAction(this.actionleg);
					gestionleg.NouvelleActionLegume(this.date,this.typeactionleg,this.id,null);

					/* Cas de l'action ARRACHER */
					if((this.actionleg.equals("ARRACHER")))
					{
						gestionleg.destructCulture(this.id);
						JOptionPane.showMessageDialog(this.f, "La culture présente sur cette parcelle a été arraché.", "Succès", JOptionPane.INFORMATION_MESSAGE);

					/* Cas de l'action RECOLTER */
					}else if((this.actionleg.equals("RECOLTER"))){
						gestionleg.destructCulture(this.id);
						JOptionPane.showMessageDialog(this.f, "La culture présente sur cette parcelle a été récolté.", "Succès", JOptionPane.INFORMATION_MESSAGE);

					/* Cas de l'action ECLAICIR */
					}else if((this.actionleg.equals("ECLAIRCIR"))){
						JOptionPane.showMessageDialog(this.f, "La culture présente sur cette parcelle a été éclaircie.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					
					/* Cas de l'action TRANSPLANTER */
					}else if((this.actionleg.equals("TRANSPLANTER"))){
						gestionleg.destructCulture(this.id);
						JOptionPane.showMessageDialog(this.f, "La culture présente sur cette parcelle a été transplanté dans une nouvelle serre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					}
		
				}else{
					JOptionPane.showMessageDialog(this.f, "Aucune culture n'a été faite sur cette parcelle donc impossible de réaliser votre action", "Message d'erreur", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		/* Bouton quitter pour revenir sur la vue du jardin */
		}else if (text.equals("Quitter")){
			this.f.dispose();

		/* Bouton pour ajouter un nouveau légume dans la liste */
		}else if (text.equals("Ajouter un légume")){
			
			/* On vérifie si le champs n'est pas vide */
			if(this.champ.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this.f, "Veuillez écrire le nom de votre légume dans la zone de texte.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}else{
				nomlegume = this.champ.getText();
				VerifNomLegume verif = new VerifNomLegume(nomlegume);
				retour = verif.nomExiste();

				/* Vérification si le nom du légume n'existe déjà pas dans la BDD */
				if(retour==true)
				{
					JOptionPane.showMessageDialog(this.f, "Ce nom de légume existe déjà dans la liste.", "Message d'erreur", 
					JOptionPane.INFORMATION_MESSAGE);
				}else{
					this.legume.addLegume(nomlegume);
					this.liste = this.legume.getAllLegume();
					this.scroll.setViewportView(this.liste);
					this.champ.setText("");
				}
			}

		/* Bouton pour supprimer un légume de la liste */
		}else if (text.equals("Supprimer un légume")){

			if (this.liste.getSelectedValue() != null)
			{
				nomlegume=(String)this.liste.getSelectedValue();
				int reponse = JOptionPane.showConfirmDialog(this.f, "Êtes-vous bien sûr de vouloir supprimer ce légume ?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
				
				/* reponse = 2 = annuler et reponse = 0 = confirmer */
				if (reponse==0)
				{
					this.legume.delLegume(nomlegume);
					this.liste = this.legume.getAllLegume();
					this.scroll.setViewportView(this.liste);
				}
			}else{
				JOptionPane.showMessageDialog(this.f, "Veuillez selectionner le légume que vous voulez supprimer.", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}