/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.modele;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class LegumeBase
{
    private JList<String> afficherlegume;

    public LegumeBase(){}

    /**
     * Méthode addLegume Ajoute un nouveau legume.
     * @param nomLegume
     *          String nom du legume.
     */
    public void addLegume(String nomLegume)
    {
        String login = "bernardl";
        String mdp = "LoloLaChips";

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            try
            {
                Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
                try
                {
                    PreparedStatement res = cn.prepareStatement("INSERT INTO Legumes(nomLegume) VALUES(?);");
                    res.setString(1, nomLegume);
                    res.executeUpdate();
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
    }

    /**
     * Méthode delLegume supprime un legume.
     * @param nomLegume
     *          String nom du legume.
     */
    public void delLegume(String nomLegume)
    {
        String login = "bernardl";
        String mdp = "LoloLaChips";
        String legume = "";

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            try
            {
                Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
                try
                {
                    PreparedStatement res = cn.prepareStatement("DELETE FROM Legumes WHERE nomLegume = ?");
                    res.setString(1, nomLegume);
                    res.executeUpdate();
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
    }

    /**
     * Méthode getAllLegume récupère une liste de légume.
     * @return 
     *          JList liste de légume.
     */
    public JList getAllLegume()
    {
        String login = "bernardl";
        String mdp = "LoloLaChips";
        String[] nomlegume;
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
                    PreparedStatement res = cn.prepareStatement("SELECT * FROM Legumes");
                    res.executeUpdate();
                    ResultSet rs = res.executeQuery();

                    if (rs != null && rs.next()) 
                    {
                        rs.last();
                        size = rs.getRow();
                        rs.first();
                        nomlegume = new String[size];
                        nomlegume[n] = rs.getString(2);
                        n++;

                        while (rs.next())
                        {
                            nomlegume[n] = rs.getString(2);
                            n++;
                        }
                        this.afficherlegume = new JList<String>(nomlegume);
                    }else{
                        nomlegume = new String[0];
                        this.afficherlegume = new JList<String>(nomlegume);
                    }
                    rs.close();
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
        return this.afficherlegume;
    }

    /**
     * Méthode getListLegume récupère une liste de légume.
     * @return 
     *          JList liste de légume.
     */
    public JList getListLegume()
    {
        return this.afficherlegume;
    }
}