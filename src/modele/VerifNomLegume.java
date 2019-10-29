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

public class VerifNomLegume
{
	private String nomLegume;
	private boolean condition;

	/**
     * Constructeur VerifNomLegume verifie si ce nom de légume est déjà utilisé ou non.
     * @param nom
     *          String est le nom de légume.
     */
	public VerifNomLegume(String nom)
	{
		this.nomLegume=nom;
		String login = "mana";
		String mdp = "yaiba0304";
		String test=null;
		try
		{
	        Class.forName("org.mariadb.jdbc.Driver");
	        try
			{
		        Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
		        try
		    	{
		    		PreparedStatement requete = cn.prepareStatement("SELECT nomLegume FROM Legumes Where nomLegume = ?;");
		    		requete.setString(1, this.nomLegume);
		    		requete.executeUpdate();
		    		ResultSet res = requete.executeQuery();
		    		
		    		while(res.next()){
		    			test = res.getString(1);
		    			/*System.out.println(test);*/
		    		}

		    		if(this.nomLegume.equals(test)){
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