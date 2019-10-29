/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.modele;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class DonneeJardin
{
	
	private int[] tabId;
	private int[] tabdimX;
	private int[] tabdimY;
	private int[] tabposX;
	private int[] tabposY;
	private int nbrParcelle;
	private int[] tabmere;
	private boolean[] tabcouper;
	private String[] taborientation;
	private int[] tabfille1;
	private int[] tabfille2;

	/**
	 * Constructeur pour DonneeJardin qui récupère toutes les informations d'un jardin concernant les parcelles pour le chargement de la page jardin.
	 * @param nomjardin
	 *			String nom du jardin que l'on souhaite rechercher
	 */
    public DonneeJardin(String nomJardin)
    {
    	String login = "bernardl";
		String mdp = "LoloLaChips";
		int n = 0;
		int size = 0;

		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp);
		        try
		    	{
		    		PreparedStatement requete = cn.prepareStatement("select COUNT(*) from Parcelle where NomJardin=?;");
		    		requete.setString(1, nomJardin);
		    		requete.executeUpdate();
		    		ResultSet res = requete.executeQuery();
		    		res.next();
		    		this.nbrParcelle=res.getInt(1);

		    		res.close();
		    		requete.close();

		    		this.tabmere= new int[this.nbrParcelle];
	    			this.tabcouper= new boolean[this.nbrParcelle];
	    			this.taborientation= new String[this.nbrParcelle];
	    			this.tabfille1= new int[this.nbrParcelle];
	    			this.tabfille2= new int[this.nbrParcelle];

	    			this.tabId= new int[this.nbrParcelle];
	    			this.tabdimX= new int[this.nbrParcelle];
	    			this.tabdimY= new int[this.nbrParcelle];
	    			this.tabposX= new int[this.nbrParcelle];
	    			this.tabposY= new int[this.nbrParcelle];

		    		PreparedStatement requete2 = cn.prepareStatement("select id,dimx,dimy,posx,posy,mere,couper,orientation,fille1,fille2 from Parcelle where NomJardin=?;");
		    		requete2.setString(1, nomJardin);
		    		requete2.executeUpdate();
		    		ResultSet res2 = requete2.executeQuery();
		    		int i = 0;

		    		while(res2.next())
		    		{
		    			this.tabId[i]=res2.getInt(1);
		    			this.tabdimX[i]=res2.getInt(2);
		    			this.tabdimY[i]=res2.getInt(3);
		    			this.tabposX[i]=res2.getInt(4);
		    			this.tabposY[i]=res2.getInt(5);

		    			this.tabmere[i]=res2.getInt(6);
		    			this.tabcouper[i]=res2.getBoolean(7);
		    			this.taborientation[i]=res2.getString(8);
		    			this.tabfille1[i]=res2.getInt(9);
		    			this.tabfille2[i]=res2.getInt(10);
		    			i++;
		    		}
		    		res2.close();
		    		requete2.close();
		    		cn.close();
		    	}catch (SQLException ex){
			    	System.err.println("A SQLException was caught after the connection: " + ex.getMessage());
			        ex.printStackTrace();
				}finally{
					cn.close();
				}
		    }catch (SQLException ex){
				System.err.println("A SQLException was caught: " + ex.getMessage());
		        ex.printStackTrace();
			}
		}catch (ClassNotFoundException ex){
			System.err.println("A ClassNotFoundException (mariadb.Driver) was caught: " + ex.getMessage());
	            ex.printStackTrace();
		}
    }

    /**
	 * Méthode pour récupèrer un tableau contenant tous les id des parcelles d'un jardin.
	 * @return this.tabid
	 *			int[] tableau de id
	 */
    public int[] gettabId(){
    	return this.tabId;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant toutes les dimensions en longueur des parcelles d'un jardin.
	 * @return this.tabdimX
	 *			int[] tableau de dimension en longueur
	 */
    public int[] gettabdimX(){
    	return this.tabdimX;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant toutes les dimensions en hauteur des parcelles d'un jardin.
	 * @return this.tabdimY
	 *			int[] tableau de dimension en hauteur
	 */
    public int[] gettabdimY(){
    	return this.tabdimY;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant toutes les positions X des parcelles d'un jardin.
	 * @return this.tabposX
	 *			int[] tableau de position X
	 */
    public int[] gettabposX(){
    	return this.tabposX;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant toutes les positions Y des parcelles d'un jardin.
	 * @return this.tabposY
	 *			int[] tableau de position Y
	 */
    public int[] gettabposY(){
    	return this.tabposY;
    }

    /**
	 * Méthode pour récupèrer le nombre de sous-parcelle  d'un jardin.
	 * @return this.nbrParcelle
	 *			int nombre de sous-parcelle
	 */
    public int getNbParcelle(){
    	return this.nbrParcelle;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant tous les id mère des parcelles d'un jardin.
	 * @return this.tabmere
	 *			int[] tableau de id mère
	 */
    public int[] gettabmere(){
    	return this.tabmere;
    }

    /**
	 * Méthode pour récupèrer un tableau de boolean pour savoir si les parcelles sont coupées ou non.
	 * @return this.tabcouper
	 *			boolean[] tableau de boolean pour savoir si la parcelle est coupée ou non
	 */
    public boolean[] gettabcouper(){
    	return this.tabcouper;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant toutes les orientations des parcelles d'un jardin.
	 * @return this.taborientation
	 *			String[] tableau de id
	 */
    public String[] gettaborientation(){
    	return this.taborientation;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant tous les id fille1 des parcelles d'un jardin.
	 * @return this.tabfille1
	 *			int[] tableau de id fille1
	 */
    public int[] gettabfille1(){
    	return this.tabfille1;
    }

    /**
	 * Méthode pour récupèrer un tableau contenant tous les id fille2 des parcelles d'un jardin.
	 * @return this.tabfille2
	 *			int[] tableau de id fille2
	 */
    public int[] gettabfille2(){
    	return this.tabfille2;
    }
}
