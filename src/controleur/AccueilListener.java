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

public class AccueilListener implements ActionListener
{

	private JTextField login;
	private JTextField mdp;
	private JFrame f;
	private CardLayout g;
	private JPanel pan;
	private JButton butt;

	/**
	 * Constructeur pour AccueilListener qui gère le contrôle des buttons sur la page d'acceuil.
	 * @param champ
	 *			JTextField correspondant à la zone de texte pour récuperer le pseudo.
	 * @param champ2
	 *			JTextField correspondant à la zone de texte pour récuperer le mot de passe.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 */
	public AccueilListener(JTextField champ, JTextField champ2, JFrame f, CardLayout g,JPanel pan)
	{
		this.login=champ;
		this.mdp=champ2;
		this.f=f;
		this.g=g;
		this.pan=pan;
	}

	public void actionPerformed(ActionEvent evenement)
	{
		String text=evenement.getActionCommand();

		/* Lorsque l'utilisateur appuie sur valider afin de se connecter */
		if (text.equals("Valider"))
		{
			if ((this.login.getText().equals("admin")) && (this.mdp.getText().equals("admin")))
			{
				this.g.next(this.pan);
			}else{
				this.mdp.setText("");
				JOptionPane.showMessageDialog(this.f, "Le nom d'utilisateur ou le mot sont incorrects", "Message d'erreur", 
				JOptionPane.INFORMATION_MESSAGE);
			}
		}

		/* Lorsque l'utilisateur appuie sur annuler pour annuler sa saisie*/
		if (text.equals("Annuler"))
		{
			this.login.setText("");
			this.mdp.setText("");
		}
	}
}