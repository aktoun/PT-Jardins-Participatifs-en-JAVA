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

public class TrouverJardin
{
	private String[] nomJardin;
	private int n = 0;
	private int size = 0;

	/**
     * Constructeur TrouverJardin permet de recuperer par la suite la liste de jardin.
     */
	public TrouverJardin()
	{
		String login = "mana";
		String mdp = "yaiba0304";
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement res = cn.prepareStatement("SELECT * FROM Jardin");
		    		res.executeUpdate();
		    		ResultSet rs = res.executeQuery();
		    		size = 0;
					if (rs != null) 
					{
					  	rs.last();
					  	size = rs.getRow();
					
						rs.first();
						nomJardin = new String[size];

						nomJardin[n] = rs.getString(2);
			    		n++;

			    		while (rs.next())
			    		{
			    			nomJardin[n] = rs.getString(2);
			    			n++;
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
	}

	/**
     * Méthode getNomJardin recupere un tableau de nom.
     * @return 
     *          String[] est un tableau de nom de jardin.
     */
	public String[] getNomJardin()
	{
		return nomJardin;
	}

	/**
     * Méthode getElement recupere le nombre d'element dans la liste.
     * @return 
     *          int est le nombre de jardin en tout.
     */
	public int getElement()
	{
		return size;
	}

	public void resfreshTrouverJardin()
	{
		String login = "mana";
		String mdp = "yaiba0304";
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement res = cn.prepareStatement("SELECT * FROM Jardin");
		    		res.executeUpdate();
		    		ResultSet rs = res.executeQuery();
		    		size = 0;
					if (rs != null) 
					{
					  rs.last();
					  size = rs.getRow();
					}
					rs.first();
					nomJardin = new String[size];
					nomJardin[n] = rs.getString(2);
		    		n++;

		    		while (rs.next())
		    		{
		    			nomJardin[n] = rs.getString(2);
		    			n++;
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
	}
}