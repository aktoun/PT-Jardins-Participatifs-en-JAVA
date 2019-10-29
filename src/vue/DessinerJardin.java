/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import javax.swing.JComponent;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
 
public class DessinerJardin extends JComponent 
{
  private int dimx;
  private int dimy;
  private int nbr_parcelle;
  private int[] tabdimX;
  private int[] tabdimY;
  private int[] tabposX;
  private int[] tabposY;

  /**
   * Constructeur pour DessinerJardin correspond au dessin du jardin dans sa globalité.
   * @param dimx
   *      int dimension en longueur de la parcelle racine.
   * @param dimy
   *      int dimension en hauteur de la parcelle racine.
   * @param nbr
   *      int correspondant au nombre de sous-parcelle en tout d'un jardin.
   * @param tabdimx
   *      int[] est un tableau contenant les dimensions en longueur de toutes les parcelles d'un jardin.
   * @param tabdimy
   *      int[] est un tableau contenant les dimensions en hauteur de toutes les parcelles d'un jardin.
   * @param tabposx
   *      int[] est un tableau contenant les positions X du coin supérieur gauche de toutes les parcelles d'un jardin.
   * @param tabposy
   *      int[] est un tableau contenant les positions Y du coin supérieur gauche de toutes les parcelles d'un jardin.
   */
  public DessinerJardin(int dimx, int dimy, int nbr, int[] tabdimX, int[] tabdimY, int[] tabposX, int[] tabposY)
  {
    this.dimx=dimx;
    this.dimy=dimy;
    this.nbr_parcelle=nbr;

    this.tabdimX= new int[this.nbr_parcelle];
    this.tabdimY= new int[this.nbr_parcelle];
    this.tabposX= new int[this.nbr_parcelle];
    this.tabposY= new int[this.nbr_parcelle];

    this.tabdimX=tabdimX;
    this.tabdimY=tabdimY;
    this.tabposX=tabposX;
    this.tabposY=tabposY;
  }

  @Override
  protected void paintComponent(Graphics pinceau) 
  {
    Graphics secondPinceau = pinceau.create();
    int x, y;
    
    if (this.isOpaque()) 
    {
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    x = (this.getWidth() - this.dimx)/2;
    y = (this.getHeight() - this.dimy)/2;

    secondPinceau.setColor(Color.BLACK);
    secondPinceau.fillRect(x,y,this.dimx+5,this.dimy+5);
    int vert = 100;

    for(int i=0; i<this.nbr_parcelle; i++)
    {
      secondPinceau.setColor(new Color(0,vert,0));
      secondPinceau.fillRect(x+this.tabposX[i]+5,y+this.tabposY[i]+5,this.tabdimX[i]-5,this.tabdimY[i]-5);

      if(i%2==0)
      {
        vert=vert+10;
        if(vert>255)
        {
          vert=100;
        }
      }
    }
  }
}