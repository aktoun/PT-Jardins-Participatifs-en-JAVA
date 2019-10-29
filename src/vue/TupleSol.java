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

public class TupleSol 
{

    private String action;
    private LocalDate date;
    
    /**
     * Constructeur pour TupleSol.
     * @param action
     *          String de l'action realisé.
     * @param date
     *          LocalDate date de realisation.
     */
    public TupleSol(String action, LocalDate date) 
    {
        super();
        this.action = action;
        this.date = date;
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
}