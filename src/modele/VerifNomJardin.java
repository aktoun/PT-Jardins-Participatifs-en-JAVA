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

public class VerifNomJardin
{
	private String nom_jardin;
	private boolean condition;

	/**
     * Constructeur VerifNomJardin verifie si ce nom de jardin est déjà utilisé ou non.
     * @param nom
     *          String est le nom de jardin.
     */
	public VerifNomJardin(String nom)
	{
		this.nom_jardin=nom;
		String login = "bernardl";
		String mdp = "LoloLaChips";
		String test=null;
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement requete = cn.prepareStatement("SELECT Nom FROM Jardin Where Nom = ?;");
		    		requete.setString(1, this.nom_jardin);
		    		requete.executeUpdate();
		    		ResultSet res = requete.executeQuery();
		    		
		    		while(res.next()){
		    			test = res.getString(1);
		    			/*System.out.println(test);*/
		    		}

		    		if(this.nom_jardin.equals(test)){
		    			this.condition=true;
		    		}else{
		    			this.condition=false;
		    		}
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

	public boolean nomExiste()
	{
		return this.condition;
	}

}