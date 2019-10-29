/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.controleur;

import src.vue.DessinerJardin;
import src.vue.PageParcelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MouseJardinListener implements MouseListener
{

	private CardLayout g;
	private JPanel pan;
	private JPanel pagejardin;
	private JFrame f;
	private int dimx;
	private int dimy;
	private DessinerJardin dessin;

	private int nbrParcelle;
    private int[] tabid;
    private int[] tabdimx;  
    private int[] tabdimy;  
    private int[] tabposx;
    private int[] tabposy;
    private int[] tabfille1;
    private int[] tabmere;

    /**
	 * Constructeur pour MouseJardinListener qui repère la sous-parcelle sélectionnée grâce au clique de souris.
	 * @param g
	 *			CardLayout qui gère le chargement de mes pages.
	 * @param pan
	 *			JPanel utilisé dans le CardLayout qui gère les pages.
	 * @param dimx
	 *			int dimension en longueur de la parcelle racine.
	 * @param dimy
	 *			int dimension en hauteur de la parcelle racine.
	 * @param dessin
	 *			DessinerJardin correspond au dessin du jardin.
	 * @param nbrParcelle
	 *			int correspondant au nombre de parcelle total dans le jardin.
	 * @param tabdimx
	 *			int[] est un tableau contenant les dimensions en longueur de toutes les parcelles d'un jardin.
	 * @param tabdimy
	 *			int[] est un tableau contenant les dimensions en hauteur de toutes les parcelles d'un jardin.
	 * @param tabposx
	 *			int[] est un tableau contenant les positions X du coin supérieur gauche de toutes les parcelles d'un jardin.
	 * @param tabposy
	 *			int[] est un tableau contenant les positions Y du coin supérieur gauche de toutes les parcelles d'un jardin.
	 * @param tabid
	 *			int[] est un tableau contenant les id de toutes les parcelles d'un jardin.
	 * @param tabfille1
	 *			int[] est un tableau contenant les id des filles1 de toutes les parcelles d'un jardin.
	 * @param f
	 *			JFrame correspondant à la fenetre de la page.
	 * @param pagejardin
	 *			JPanel correspondant à la page de la parcelle racine.
	 * @param tabmere
	 *			int[] est un tableau contenant les id mère de toutes les parcelles d'un jardin.
	 */
	public MouseJardinListener(CardLayout g,JPanel pan, int dimx, int dimy, DessinerJardin dessin, int nbrParcelle, int[] tabdimx, 
		int[] tabdimy,int[] tabposx, int[] tabposy, int[] tabid, int[] tabfille1, JFrame f, JPanel pagejardin, int[] tabmere)
	{
		
		this.g=g;
		this.pan=pan;
		this.pagejardin=pagejardin;
		this.f=f;
		this.dimx=dimx;
		this.dimy=dimy;
		this.dessin=dessin;

		this.nbrParcelle = nbrParcelle;
	    this.tabid = new int[this.nbrParcelle];
	    this.tabdimx = new int[this.nbrParcelle];  
	    this.tabdimy = new int[this.nbrParcelle];  
	    this.tabposx = new int[this.nbrParcelle];
	    this.tabposy = new int[this.nbrParcelle];
	    this.tabfille1 = new int[this.nbrParcelle];
	    this.tabmere = new int[this.nbrParcelle];

	    this.tabid = tabid;
	    this.tabdimx = tabdimx;
	    this.tabdimy = tabdimy;
	    this.tabposx = tabposx;
	    this.tabposy = tabposy;
	    this.tabfille1 = tabfille1;
	    this.tabmere = tabmere;

	}

	public void mouseClicked(MouseEvent evenement){}
	public void mouseEntered(MouseEvent evenement){}
	public void mouseExited(MouseEvent evenement){}
	public void mousePressed(MouseEvent evenement)
	{

		int posx = evenement.getX()-(this.dessin.getWidth()-this.dimx)/2;
		int posy = evenement.getY()-(this.dessin.getHeight()-this.dimy)/2;

		/* Vérifie si le clique est bien dans le dessin, c'est-à-dire dans le jardin */ 
		if (((posx>0) && (posx<this.dimx)) && ((posy>0) && (posy<this.dimy)))
		{
			for (int i=0; i<this.nbrParcelle; i++)
			{
				/* Filtre seulement les plus petites sous-parcelles. */ 
				if(this.tabfille1[i] == 0)
				{
					/* Filtre seulement les plus petites sous-parcelles avec les positions. */ 
					if((posx>this.tabposx[i]) && (posy>this.tabposy[i]))
					{
						/* Filtre seulement les plus petites sous-parcelles avec les dimensions. */ 
						if((posx<this.tabposx[i]+this.tabdimx[i]) && (posy<this.tabposy[i]+this.tabdimy[i]))
						{
							PageParcelle parcelle = new PageParcelle(this.g,this.pan,this.dimx,this.dimy,this.f,nbrParcelle,i,this.tabposx,
								this.tabposy,this.tabdimx,this.tabdimy, this.tabid[i],this.pagejardin,this.tabmere[i],this.tabid);

							this.pan.add(parcelle, "parcelle");
							parcelle.revalidate();
							this.g.show(this.pan,"parcelle");
							break;
						}
					}
				}
			}
		}
	}
	public void mouseReleased(MouseEvent evenement){}
}