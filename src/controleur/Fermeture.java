/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.controleur;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import java.awt.*;

public class Fermeture implements WindowListener 
{
	private JFrame fenetre;
	/**
	 * Constructeur pour Fermeture qui controle la fermeture de la page.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 */
	public Fermeture(JFrame f){
		this.fenetre=f;
	}

	@Override
	public void windowOpened(WindowEvent e){}

	/*Permet de fermer la fenêtre */
	@Override
	public void windowClosing(WindowEvent e) 
	{
		this.fenetre.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e){}

	@Override
	public void windowIconified(WindowEvent e){}

	@Override
	public void windowDeiconified(WindowEvent e){}

	@Override
	public void windowActivated(WindowEvent e){}

	@Override
	public void windowDeactivated(WindowEvent e){}
}
