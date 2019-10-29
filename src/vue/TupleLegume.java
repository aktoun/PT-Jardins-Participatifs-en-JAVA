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

public class TupleLegume 
{

    private String action;
    private LocalDate date;
    private String legume;
    
    /**
     * Constructeur pour TupleLegume.
     * @param action
     *          String de l'action realisé.
     * @param date
     *          LocalDate date de realisation.
     * @param legume
     *          String nom du legume
     */
    public TupleLegume(String action, LocalDate date, String legume) 
    {
        super();
        this.action = action;
        this.date = date;
        this.legume = legume;
    }
    
    /**
     * Methode pour getAction.
     * @return
     *          String de l'action realisé.
     */
    public String getAction() 
    {
        return action;
    }
    
    /**
     * Methode pour setAction.
     * @param action
     *          String de l'action realisé.
     */
    public void setAction(String action) 
    {
        this.action = action;
    }
 
    /**
     * Methode pour getDate.
     * @return
     *          LocalDate date de realisation.
     */
    public LocalDate getDate() 
    {
        return date;
    }
    
    /**
     * Methode pour setAction.
     * @param date
     *          LocalDate date de realisation.
     */
    public void setDate(LocalDate date) 
    {
        this.date = date;
    }
 
    /**
     * Methode pour getLegume.
     * @return
     *          String nom de légume.
     */
    public String getLegume() 
    {
        return legume;
    }
    
    /**
     * Methode pour setLegume.
     * @param legume
     *          String nom de légume.
     */
    public void setLegume(String legume) 
    {
        this.legume = legume;
    }
}