/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import javax.swing.table.AbstractTableModel;

import src.vue.TupleLegume;

import src.modele.ActionLegume;

public class TableauHistoriqueLegume extends AbstractTableModel 
{
    
    private final TupleLegume[] tuples;
    private final String[] entetes = {"Action réalisée", "Date", "Légume"};
    
    /**
     * Constructeur pour TableauHistoriqueLegume correspondant à la creation des tableau de l'historique.
     * @param id
     *          int id de la parcelle selectionnée.
     */
    public TableauHistoriqueLegume(int id) 
    {
        super();

        ActionLegume gestionleg = new ActionLegume();
        gestionleg.GetActionJardin(id);
        int compteur = gestionleg.getnbr();

        String[] tabaction = new String[compteur];
        String[] legume = new String[compteur];
        LocalDate[] date = new LocalDate[compteur];

        tabaction = gestionleg.getAction();
        legume = gestionleg.getLegume();
        date = gestionleg.getDateAct();

        tuples = new TupleLegume[compteur];

        for (int j = 0; j<compteur; j++)
        {
            tuples[j]= new TupleLegume(tabaction[j],date[j],legume[j]);
        }

    }
    
    /**
     * Methode pour getRowCount.
     * @return 
     *          int nombre de ligne/tuple.
     */
    public int getRowCount() 
    {
        return tuples.length;
    }
 
    /**
     * Methode pour getColumnCount.
     * @return 
     *          int nombre de colonne.
     */
    public int getColumnCount() 
    {
        return entetes.length;
    }
 
    /**
     * Methode pour getColumnName.
     * @return 
     *          String intitulé de la nième colonne.
     */
    public String getColumnName(int columnIndex) 
    {
        return entetes[columnIndex];
    }
    
    /**
     * Methode pour getValueAt.
     * @param rowIndex
     *          int de la ligne.
     * @param columnIndex
     *          int de la colonne.
     * @return 
     *          String valeur de telle ligne et de telle colonne.
     */
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        switch(columnIndex)
        {
            case 0:
                return tuples[rowIndex].getAction();
            case 1:
                return tuples[rowIndex].getDate();
            case 2:
                return tuples[rowIndex].getLegume();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
}