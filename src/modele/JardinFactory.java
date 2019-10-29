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

import src.modele.AbstractJardinFactory;

public class JardinFactory implements AbstractJardinFactory
{
	private JList<String> afficherNomJardin;
	private String selected;
	private int dimx;
	private int dimy;

	/**
     * Méthode AddJardin Ajoute un nouveau jardin.
     * @param nomJardin
     *          String nom du jardin.
     * @param dimx
     *          int dimension en longueur.
     * @param dimy
     *          int dimension en hauteur.
     */
    public void AddJardin(String nomJardin, int dimx, int dimy)
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
		    		PreparedStatement requete = cn.prepareStatement("INSERT INTO Jardin(Nom,TailleX,TailleY) VALUES(?,?,?);");
		    		requete.setString(1, nomJardin);
		    		requete.setInt(2, dimx);
		    		requete.setInt(3, dimy);
		    		requete.executeUpdate();

		    		requete.close();

		    		int posx=0;
		    		int posy=0;
		    		int idmere=0;

		    		PreparedStatement requete3 = cn.prepareStatement("INSERT INTO Parcelle(NomJardin,dimx,dimy,posx,posy,Mere,Couper) VALUES(?,?,?,?,?,?,?);");
		    		requete3.setString(1, nomJardin);
		    		requete3.setInt(2, dimx);
		    		requete3.setInt(3, dimy);
		    		requete3.setInt(4, posx);
		    		requete3.setInt(5, posy);
		    		requete3.setInt(6, idmere);
		    		requete3.setBoolean(7, false);
		    		requete3.executeUpdate();

		    		requete.close();
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
     * Méthode DeleteJardin supprime jardin.
     * @param nomJardin
     *          String nom du jardin.
     */
    public void DeleteJardin(String nomJardin)
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
		    		PreparedStatement requete = cn.prepareStatement("delete from Jardin where Nom = ?;");
		    		requete.setString(1, nomJardin);
		    		requete.executeUpdate();

		    		requete.close();
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
     * Méthode getAllJardin supprime jardin.
     * @return
     *          JList liste de nom de jardin.
     */
    public JList getAllJardin()
    {
    	String login = "mana";
		String mdp = "yaiba0304";
		String[] nomJardin;
		int n = 0;
		int size = 0;
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
					if (rs != null && rs.next()) 
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
			    		this.afficherNomJardin = new JList<String>(nomJardin);
		    		}else{
		    			nomJardin = new String[0];
		    			this.afficherNomJardin = new JList<String>(nomJardin);
		    		}
		    	}
		    	catch (SQLException ex)
				{
			    	System.err.println("A SQLException was caught after the connection: " + ex.getMessage());
			        ex.printStackTrace();
				}
				finally
				{
					cn.close();
				}
		    }
		    catch (SQLException ex)
			{
				System.err.println("A SQLException was caught: " + ex.getMessage());
		        ex.printStackTrace();
			}
		}
		catch (ClassNotFoundException ex)
		{
			System.err.println("A ClassNotFoundException (mariadb.Driver) was caught: " + ex.getMessage());
	            ex.printStackTrace();
		}
		return this.afficherNomJardin;
    }

    /**
     * Méthode getJListJardin supprime jardin.
     * @return
     *          JList liste de nom de jardin.
     */
    public JList getJListJardin()
    {
    	return this.afficherNomJardin;
    }

    /**
     * Méthode getX recuperer dimension en longueur.
     * @param nomJardin
     *          String nom du jardin.
	 * @return
     *          int dimension en longueur.
     */
    public int getX(String jardin)
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
		    		PreparedStatement requete = cn.prepareStatement("SELECT TailleX FROM Jardin where nom = ?");
		    		requete.setString(1, jardin);
		    		requete.executeUpdate();
		    		ResultSet res = requete.executeQuery();
		    		res.next();
			    	this.dimx = res.getInt(1);

			    	requete.close();
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
		return this.dimx;
	}

	/**
     * Méthode getY recuperer dimension en hauteur.
     * @param nomJardin
     *          String nom du jardin.
	 * @return
     *          int dimension en hauteur.
     */
    public int getY(String jardin)
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
		    		PreparedStatement requete = cn.prepareStatement("SELECT TailleY FROM Jardin where nom = ?");
		    		requete.setString(1, jardin);
		    		requete.executeUpdate();
		    		ResultSet res = requete.executeQuery();
		    		res.next();
			    	this.dimy = res.getInt(1);

			    	requete.close();
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
		return this.dimy;
    }

    /**
     * Méthode setSelect recuperer le jardin selectionne dans la liste.
     * @param sel
     *          String nom du jardin.
     */
    public void setSelect(String sel)
    {
    	this.selected = sel;
    }
}
