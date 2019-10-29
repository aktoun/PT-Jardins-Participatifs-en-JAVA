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

import src.modele.ActionSolType;
import src.modele.Action;

public class ActionSol extends Action
{
    private int[] id;
    private LocalDate[] dateAct;
    private String[] act;
    private int compteur;

    public ActionSol(){}

    /**
     * Méthode NouvelleActionSol Ajoute une nouvelle action sur le sol.
     * @param date
     *          LocalDate à la dat de réalisation de l'action.
     * @param type
     *          ActionSolType le type de l'action effectué.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     */
    public void NouvelleActionSol(LocalDate date, ActionSolType type, int idParcelle)
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
                    res = cn.prepareStatement("INSERT INTO Historique(idParcelle, NomJardin, Date, Action,Typeaction) VALUES(?,?,?,?,?);");
                    res.setInt(1, idParcelle);
                    res.setString(2, nomJardin);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
                    datee = date.format(formatter);

                    res.setObject(3, datee);
                    res.setString(4, typee);
                    res.setBoolean(5, true);
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
     * Méthode GetActionJardin lance une requette permettant de récupérer par la suite toutes les actions sur la parcelle sélectionnée.
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
                    PreparedStatement res = cn.prepareStatement("SELECT IdParcelle, Date, Action FROM Historique WHERE idParcelle = ? AND Typeaction=?;");
                    res.setInt(1, idParcelle);
                    res.setBoolean(2, true);
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

                        id[n] = rs.getInt(1);
                        dating = rs.getString(2);
                        dateAct[n] = LocalDate.parse(dating);
                        act[n] =rs.getString(3);
                        n++;

                        while (rs.next())
                        {
                            id[n] = rs.getInt(1);
                            dating = rs.getString(2);
                            dateAct[n] = LocalDate.parse(dating);
                            act[n] =rs.getString(3);
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
     *          ActionSolType est le type de l'action réalisé.
     * @return 
     *          String est le type de l'action réalisé
     */
    public String ActionToString(ActionSolType act)
    {
        String phrase;
        if (act == ActionSolType.AMENDER)
        {
            phrase = "AMENDER";
            return phrase;
        }
        else if (act == ActionSolType.BECHER)
        {
            phrase = "BECHER";
            return phrase;
        }
        else if (act == ActionSolType.BINER)
        {
            phrase = "BINER";
            return phrase;
        }
        else if (act == ActionSolType.BUTTER)
        {
            phrase = "BUTTER";
            return phrase;
        }
        else if (act == ActionSolType.PAILLER)
        {
            phrase = "PAILLER";
            return phrase;
        }
        else if (act == ActionSolType.FUMER)
        {
            phrase = "FUMER";
            return phrase;
        }
        else
        {
            return "erreur";
        }
    }

    /**
     * Méthode ActionSolType convertit le string en un type d'action sur le sol.
     * @param act
     *          String est l'action réalisé.
     * @return 
     *          ActionSolType est le type de l'action réalisé
     */
    public ActionSolType StringToAction(String phrase)
    {
        ActionSolType o;
        if (phrase == "AMENDER")
        {
            o = ActionSolType.AMENDER;
            return o;
        }
        else if (phrase == "BECHER")
        {
            o = ActionSolType.BECHER;
            return o;
        }
        else if (phrase == "BINER")
        {
            o = ActionSolType.BINER;
            return o;
        }
        else if (phrase == "BUTTER")
        {
            o = ActionSolType.BUTTER;
            return o;
        }
        else if (phrase == "PAILLER")
        {
            o = ActionSolType.PAILLER;
            return o;
        }
        else if (phrase == "FUMER")
        {
            o = ActionSolType.FUMER;
            return o;
        }
        else
        {
            return ActionSolType.NULL;
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
     * Méthode getnbr récupère le nombre de tuple.
     * @return
     *          int nbr de tuple.
     */
    public int getnbr(){
        return this.compteur;
    }
}