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

import src.vue.TupleSol;

import src.modele.ActionSol;

public class TableauHistoriqueSol extends AbstractTableModel 
{
    
    private final TupleSol[] tuples;
    private final String[] entetes = {"Action réalisée", "Date"};
 
    /**
     * Constructeur pour TableauHistoriqueSol correspondant à la creation des tableau de l'historique.
     * @param id
     *          int id de la parcelle selectionnée.
     */
    public TableauHistoriqueSol(int id) 
    {
        super();

        ActionSol gestionsol = new ActionSol();
        gestionsol.GetActionJardin(id);
        int compteur = gestionsol.getnbr();

        String[] tabaction = new String[compteur];
        LocalDate[] date = new LocalDate[compteur];

        tabaction = gestionsol.getAction();
        date = gestionsol.getDateAct();

        tuples = new TupleSol[compteur];

        for (int j = 0; j<compteur; j++)
        {
            tuples[j]= new TupleSol(tabaction[j],date[j]);
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
            default:
                return null; //Ne devrait jamais arriver
        }
    }
}