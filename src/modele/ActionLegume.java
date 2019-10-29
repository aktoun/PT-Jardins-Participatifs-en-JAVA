/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.modele;

import java.util.*;

import java.time.Month;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;

import src.modele.ActionLegumeType;
import src.modele.ActionLegumeFactory;

public class ActionLegume extends ActionLegumeFactory
{
    private int[] id;
    private LocalDate[] dateAct;
    private String[] act;
    private String[] legume;
    private boolean condition;
    private int compteur;

    public ActionLegume(){}

    /**
     * Méthode NouvelleActionLegume Ajoute une nouvelle action liée aux légumes.
     * @param date
     *          LocalDate à la dat de réalisation de l'action.
     * @param type
     *          ActionSolType le type de l'action effectué.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     * @param legume
     *          String nom du légume utilisé lors de l'action.
     */
    public void NouvelleActionLegume(LocalDate date, ActionLegumeType type, int idParcelle, String legume)
    {
        String login = "mana";
        String mdp = "yaiba0304";
        String nomJardin = "";
        String datee = "";

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            try
            {
                Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
                try
                {
                    PreparedStatement res = cn.prepareStatement("SELECT NomJardin FROM Parcelle WHERE Id = ?");
                    res.setInt(1, idParcelle);
                    res.executeUpdate();

                    ResultSet rs = res.executeQuery();
                    if (rs != null) 
                    {
                        while (rs.next())
                        {
                            nomJardin = rs.getString(1);
                        }
                    }

                    rs.close();
                    String typee = ActionToString(type);

                    if(legume!=null)
                    {
                        res = cn.prepareStatement("INSERT INTO Historique(idParcelle, NomJardin, Date, Action, Legume, Cultiver,Typeaction) VALUES(?,?,?,?,?,?,?);");
                        res.setInt(1, idParcelle);
                        res.setString(2, nomJardin);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
                        datee = date.format(formatter);

                        res.setObject(3, datee);
                        res.setString(4, typee);
                        res.setString(5, legume);
                        res.setBoolean(6, true);
                        res.setBoolean(7, false);
                        res.executeUpdate();
                        res.close();
                    }else{
                        res = cn.prepareStatement("INSERT INTO Historique(idParcelle, NomJardin, Date, Action,Typeaction) VALUES(?,?,?,?,?);");
                        res.setInt(1, idParcelle);
                        res.setString(2, nomJardin);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
                        datee = date.format(formatter);

                        res.setObject(3, datee);
                        res.setString(4, typee);
                        res.setBoolean(5, false);
                        res.executeUpdate();
                        res.close();
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
     * Méthode VerifPresenceCulture vérifie la présence d'une culture sur une parcelle.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     * @return
     *          boolean true=présence d'une culture, false=absence de culture.
     */
    public boolean VerifPresenceCulture(int idParcelle)
    {
        String login = "mana";
        String mdp = "yaiba0304";
        int test=0;
        String action = ActionToString(ActionLegumeType.SEMER);
        boolean cultiver = true;

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            try
            {
                Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
                try
                {
                    PreparedStatement requete = cn.prepareStatement("select COUNT(*) from Historique where idParcelle=? AND Action=? AND Cultiver=?;");
                    requete.setInt(1, idParcelle);
                    requete.setString(2, action);
                    requete.setBoolean(3, cultiver);
                    requete.executeUpdate();
                    ResultSet res = requete.executeQuery();
                    
                    while(res.next())
                    {
                        test = res.getInt(1);
                    }

                    if(test==1)
                    {
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
        return this.condition;
    }

    /**
     * Méthode destructCulture enlève une culture sur une parcelle suite à une action.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     */
    public void destructCulture(int idParcelle)
    {
        String login = "mana";
        String mdp = "yaiba0304";
        String action = ActionToString(ActionLegumeType.SEMER);
        boolean vrai = true;
        boolean faux = false;

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            try
            {
                Connection cn = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/" + login, login, mdp); /*Dwarves.arda*/
                try
                {
                    PreparedStatement res = cn.prepareStatement("UPDATE Historique SET Cultiver = ? WHERE idParcelle=? AND Action=? AND Cultiver = ?;");
                    res.setBoolean(1, faux);
                    res.setInt(2, idParcelle);
                    res.setString(3, action);
                    res.setBoolean(4, vrai);
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
     * Méthode GetActionJardin requete permettant de récupérer par la suite les actions effectuées sur une parcelle.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     */
    public void GetActionJardin(int idParcelle)
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
                    PreparedStatement res = cn.prepareStatement("SELECT IdParcelle, Date, Action, Legume FROM Historique WHERE idParcelle = ? AND Typeaction=?;");
                    res.setInt(1, idParcelle);
                    res.setBoolean(2, false);
                    res.executeUpdate();

                    ResultSet rs = res.executeQuery();
                    int size = 0;
                    int n = 0;
                    String dating;

                    if (rs != null && rs.next()) 
                    {
                        rs.last();
                        size = rs.getRow();

                        rs.first();
                        id = new int[size];
                        dateAct = new LocalDate[size];
                        act = new String[size];
                        legume = new String[size];

                        id[n] = rs.getInt(1);
                        dating = rs.getString(2);
                        dateAct[n] = LocalDate.parse(dating);
                        act[n] = rs.getString(3);
                        legume[n] = rs.getString(4);
                        n++;

                        while (rs.next())
                        {
                            id[n] = rs.getInt(1);
                            dating = rs.getString(2);
                            dateAct[n] = LocalDate.parse(dating);
                            act[n] = rs.getString(3);
                            legume[n] = rs.getString(4);
                            n++;
                        }
                        this.compteur=size;
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
    }

    /**
     * Méthode ActionToString convertit le type d'action en un string.
     * @param act
     *          ActionLegumeType est le type de l'action réalisé.
     * @return 
     *          String action réalisé
     */
    public String ActionToString(ActionLegumeType act)
    {
        String phrase;
        if (act == ActionLegumeType.ARRACHER)
        {
            phrase = "ARRACHER";
            return phrase;
        }
        else if (act == ActionLegumeType.ECLAIRCIR)
        {
            phrase = "ECLAIRCIR";
            return phrase;
        }
        else if (act == ActionLegumeType.RECOLTER)
        {
            phrase = "RECOLTER";
            return phrase;
        }
        else if (act == ActionLegumeType.SEMER)
        {
            phrase = "SEMER";
            return phrase;
        }
        else if (act == ActionLegumeType.TRANSPLANTER)
        {
            phrase = "TRANSPLANTER";
            return phrase;
        }
        else
        {
            return "erreur";
        }
    }

    /**
     * Méthode StringToAction convertit le string en un type d'action sur le sol.
     * @param act
     *          String est l'action réalisé.
     * @return 
     *          ActionLegumeType action réalisé
     */
    public ActionLegumeType StringToAction(String phrase)
    {
        ActionLegumeType o;
        if (phrase == "ARRACHER")
        {
            o = ActionLegumeType.ARRACHER;
            return o;
        }
        else if (phrase == "ECLAIRCIR")
        {
            o = ActionLegumeType.ECLAIRCIR;
            return o;
        }
        else if (phrase == "RECOLTER")
        {
            o = ActionLegumeType.RECOLTER;
            return o;
        }
        else if (phrase == "SEMER")
        {
            o = ActionLegumeType.SEMER;
            return o;
        }
        else if (phrase == "TRANSPLANTER")
        {
            o = ActionLegumeType.TRANSPLANTER;
            return o;
        }
        else
        {
            return ActionLegumeType.NULL;
        }
    }

    /**
     * Méthode getId récupère un tableau d'id.
     * @return
     *          int[] tableau d'id.
     */
    public int[] getId()
    {
        return id;
    }

    /**
     * Méthode getDateAct récupère un tableau de date.
     * @return
     *          LocalDate[] tableau de date.
     */
    public LocalDate[] getDateAct()
    {
        return dateAct;
    }

    /**
     * Méthode getAction récupère un tableau d'action.
     * @return
     *          String[] tableau d'action.
     */
    public String[] getAction()
    {
        return act;
    }

    /**
     * Méthode getLegume récupère un tableau de légumes.
     * @return
     *          String[] tableau de légumes.
     */
    public String[] getLegume()
    {
        return legume;
    }

    /**
     * Méthode getnbr récupère le nombre de tuple.
     * @return
     *          int nbr de tuple.
     */
    public int getnbr()
    {
        return this.compteur;
    }
}