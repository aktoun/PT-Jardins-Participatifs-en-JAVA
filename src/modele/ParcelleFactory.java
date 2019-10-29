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

import src.modele.Orientation;
import src.modele.AbstractJardinFactory;

public class ParcelleFactory implements AbstractParcelleFactory
{
	public ParcelleFactory(){}

	/**
     * Méthode getSplit verification si la parcelle est coupée ou non.
     * @param idMere
     *          id de la parcelle selectionnée.
     * @return 
     *			si oui, retourne une orientation
     */
	public Orientation getSplit(int idMere)
	{
		String login = "bernardl";
		String mdp = "LoloLaChips";
		Boolean couper;
		int mere;
		String orient = null;
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement res = cn.prepareStatement("SELECT Mere,Couper,Orientation FROM Parcelle WHERE Id = ?");
		    		res.setInt(1, idMere);
		    		res.executeUpdate();
		    		ResultSet rs = res.executeQuery();
					if (rs != null) 
					{
						while (rs.next())
			    		{
				    		mere = rs.getInt(1);		  
				    		couper = rs.getBoolean(2);
				    		orient = rs.getString(3);
			    		}
					}
					rs.close();
					res.close();
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

		if (orient != null)
		{
			Orientation o = stringToOrientation(orient);
			return o;
		}else{
			return Orientation.NULL;
		}
	}

	/**
     * Méthode SplitParcelle coupe la parcelle selectionnée selon l'orientation choisie.
     * @param o
     *          Orientation choisie.
     * @param idMere
     *          id de la parcelle selectionnée.
     */
	public void SplitParcelle(Orientation o, int idMere)
	{
		String login = "bernardl";
		String mdp = "LoloLaChips";
		String nomJardin = null;
		int dimx = 0;
		int dimy = 0;
		int idFille1 = 0;	//Fille1 == Parcelle gauche ou haute.
		int idFille2 = 0;
		int moitierx = 0;
		int moitiery = 0;
		int posx = 0;
		int posy = 0;
		int verif = 0;
		String orient = orientationToString(o);
		Boolean couper;
		int mere;

		if (this.getSplit(idMere) == Orientation.NULL)
		{
			try
			{
		        Class.forName("org.mariadb.jdbc.Driver");
		        try
				{
			        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
			        try
			    	{
			    		PreparedStatement res = cn.prepareStatement("SELECT * FROM Parcelle WHERE Id = ?");
			    		res.setInt(1, idMere);
			    		res.executeUpdate();
			    		ResultSet rs = res.executeQuery();
						if (rs != null) 
						{
							while (rs.next())
				    		{
					    		nomJardin = rs.getString(1);		  
					    		dimx = rs.getInt(3);
					    		dimy = rs.getInt(4);
								posx = rs.getInt(5);
								posy = rs.getInt(6);
				    		}
						}
						rs.close();

						if (o == Orientation.HORIZONTAL)
						{
							verif = dimy % 10;
							moitiery = (int) dimy / 2;
							moitierx = (int) dimx;

							if (verif == 1)
							{
								moitiery = moitiery + 1;
							}
						}else{
							verif = dimx % 10;
							moitierx = (int) dimx / 2;
							moitiery = (int) dimy;

							if (verif == 1)
							{
								moitierx = moitierx + 1;
							}
						}
						res = cn.prepareStatement("INSERT INTO Parcelle(nomJardin,dimx,dimy,posx,posy,Mere,Couper) Value(?,?,?,?,?,?,?);");
						res.setString(1, nomJardin);
			    		res.setInt(2, moitierx);
			    		res.setInt(3, moitiery);
						res.setInt(4, posx);
			    		res.setInt(5, posy);
			    		res.setInt(6, idMere);
			    		res.setBoolean(7, false);
			    		res.executeUpdate();

						if (o == Orientation.HORIZONTAL)
						{
							posy = posy + moitiery;
						}else{
							posx = posx + moitierx;
						}
						res = cn.prepareStatement("SELECT LAST_INSERT_ID() FROM Parcelle");
						res.executeUpdate();
			    		rs = res.executeQuery();
						if (rs != null) 
						{
							while (rs.next())
				    		{
					    		idFille1 = rs.getInt(1);
				    		}
						}
						rs.close();
						if (o == Orientation.HORIZONTAL)
						{
							moitiery = dimy - moitiery;
						}else{
							moitierx = dimx - moitierx;
						}
						res = cn.prepareStatement("INSERT INTO Parcelle(nomJardin,dimx,dimy,posx,posy,Mere,Couper) Value(?,?,?,?,?,?,?);");
						res.setString(1, nomJardin);
			    		res.setInt(2, moitierx);
			    		res.setInt(3, moitiery);
			    		res.setInt(4, posx);
			    		res.setInt(5, posy);
			    		res.setInt(6, idMere);
			    		res.setBoolean(7, false);
			    		res.executeUpdate();

			    		res = cn.prepareStatement("SELECT LAST_INSERT_ID() FROM Parcelle");
						res.executeUpdate();
			    		rs = res.executeQuery();
						if (rs != null) 
						{
							while (rs.next())
				    		{
					    		idFille2 = rs.getInt(1);
				    		}
						}
						rs.close();

						res = cn.prepareStatement("UPDATE Parcelle SET Couper = ?, Orientation = ?, Fille1 = ?, Fille2 = ? WHERE Id = ?;");
						res.setBoolean(1, true);
			    		res.setString(2, orient);
			    		res.setInt(3, idFille1);
			    		res.setInt(4, idFille2);
			    		res.setInt(5, idMere);
			    		res.executeUpdate();
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
	}

	/**
     * Méthode stringToOrientation convertit le string en un type d'orientation.
     * @param phrase
     *          String l'orientation choisie.
     * @return 
     *          Orientation est le type d'orientation
     */
	public Orientation stringToOrientation(String phrase)
	{
		Orientation o;
		if (phrase == "VERTICAL")
		{
			o = Orientation.VERTICAL;
			return o;
		}else if (phrase == "HORIZONTAL"){
			o = Orientation.HORIZONTAL;
			return o;
		}else{
			return Orientation.NULL; //Trouver une solution pour sortir sans retourer un orientation.
		}
	}

	/**
     * Méthode orientationToString convertit le type d'orientation en un string.
     * @param phrase
     *          Orientation est l'orientation choisie.
     * @return 
     *          String est le type d'orientation
     */
	public String orientationToString(Orientation o)
	{
		String phrase;
		if (o == Orientation.VERTICAL)
		{
			phrase = "VERTICAL";
			return phrase;
		}else if (o == Orientation.HORIZONTAL){
			phrase = "HORIZONTAL";
			return phrase;
		}else{
			return "erreur";
		}
	}

	/**
     * Méthode delFille supprime les 2 sous parcelles.
     * @param id
     *          int est l'id de la parcelle mere.
     * @param cn
     *          Connection en cours à la BDD.
     */
	public void delFille(int id, Connection cn)
	{
		int idFille1, idFille2;
		Boolean couper = false;

		try
		{	
    		PreparedStatement res = cn.prepareStatement("SELECT Couper FROM Parcelle WHERE Id = ?");
    		res.setInt(1, id);
    		ResultSet rs = res.executeQuery();
			if (rs != null) 
			{
				rs.next();
		    	couper = rs.getBoolean(1);
			}

			rs.close();
			res.close();

			if (couper == true)
			{
				idFille1 = this.getFirst(id);
				idFille2 = this.getSecond(id);
				this.delFille(idFille1, cn);
				this.delFille(idFille2, cn);

				res = cn.prepareStatement("DELETE FROM Parcelle WHERE Id = ? OR Id = ?;");
				res.setInt(1, idFille1);
				res.setInt(2, idFille2);
				res.executeUpdate();
				res.close();
			}
		}catch (SQLException ex){
	    	System.err.println("A SQLException was caught after the connection: " + ex.getMessage());
	        ex.printStackTrace();
		}
	}

	/**
     * Méthode reset supprime les sous parcelles recursivement.
     * @param id
     *          int est l'id de la parcelle mere.
     */
	public void reset(int idMere)
	{
		String login = "bernardl";
		String mdp = "LoloLaChips";
		int idFille1, idFille2;
		int mere = 0;
		Boolean couper = false;
		String orient = null;

		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/ 
		        this.delFille(idMere, cn);
		        try
		        {
			        PreparedStatement res = cn.prepareStatement("UPDATE Parcelle SET  Couper = ?, Orientation = ?, Fille1 = ?, Fille2 = ? WHERE Id = ?;");
					res.setInt(1, 0);
					res.setString(2, null);
					res.setString(3, null);
					res.setString(4, null);
					res.setInt(5, idMere);
					res.executeUpdate();

					res.close();
				}catch (SQLException ex){
					System.err.println("A SQLException was caught: " + ex.getMessage());
			        ex.printStackTrace();
				}
				cn.close();
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
     * Méthode getFirst recupere la premiere fille d'une parcelle.
     * @param id
     *          int est l'id de la parcelle mere.
     */
	public int getFirst(int idMere)
	{
		String login = "bernardl";
		String mdp = "LoloLaChips";
		int mere = 0;
		Boolean couper = false;
		String orient = null;
		int idFille1 = 0;
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement res = cn.prepareStatement("SELECT Mere,Couper,Orientation,Fille1 FROM Parcelle WHERE Id = ?");
		    		res.setInt(1, idMere);
		    		res.executeUpdate();
		    		ResultSet rs = res.executeQuery();
					if (rs != null) 
					{
						while (rs.next())
			    		{
				    		mere = rs.getInt(1);		  
				    		couper = rs.getBoolean(2);
				    		orient = rs.getString(3);
							idFille1 = rs.getInt(4);
			    		}
					}
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
		if (couper == true)
		{
			return idFille1;
		}
		return 0;
	}

	/**
     * Méthode getFirst recupere la deuxième fille d'une parcelle.
     * @param id
     *          int est l'id de la parcelle mere.
     */
	public int getSecond(int idMere)
	{
		String login = "bernardl";
		String mdp = "LoloLaChips";
		int mere = 0;
		Boolean couper = false;
		String orient = null;
		int idFille2 = 0;
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement res = cn.prepareStatement("SELECT Mere,Couper,Orientation,Fille2 FROM Parcelle WHERE Id = ?");
		    		res.setInt(1, idMere);
		    		res.executeUpdate();
		    		ResultSet rs = res.executeQuery();
					if (rs != null) 
					{
						while (rs.next())
			    		{
				    		mere = rs.getInt(1);		  
				    		couper = rs.getBoolean(2);
				    		orient = rs.getString(3);
							idFille2 = rs.getInt(4);
			    		}
					}
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
		if (couper == true)
		{
			return idFille2;
		}
		return 0;
	}

	/**
     * Méthode getNomJardin recupere le nom de la parcelle.
     * @param id
     *          int est l'id de la parcelle mere.
     * @return 
     *          String est le nom de la parcelle.
     */
	public String getNomJardin(int id){

		String login = "bernardl";
		String mdp = "LoloLaChips";
		String nom=null;
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement res = cn.prepareStatement("SELECT nomJardin FROM Parcelle WHERE Id = ?");
		    		res.setInt(1, id);
		    		res.executeUpdate();
		    		ResultSet rs = res.executeQuery();
					if (rs != null) 
					{
						rs.next();
						nom = rs.getString(1);
					}
					rs.close();
					res.close();
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
		return nom;
	}

	public void getActions(){}

    public void getAllActions(){}

    public void addAction(Action a){}

    public void getMother(){} //Type parcelle

    public void getRoot(){} //Type parcelle

    public int getx0(){
    	return 0;
    }

    public int gety0(){
    	return 0;
    }

    public int getx1(){
    	return 0;
    }

    public int gety1(){
    	return 0;
    }

}