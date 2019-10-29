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

public interface AbstractJardinFactory{

    /**
     * Méthode AddJardin Ajoute si possible un nouveau jardin.
     * @param nomJardin
     *          String qui correspond au nom du jardin.
     * @param dimx
     *          int dimension en longueur du jardin.
     * @param dimy
     *          int dimension en hauteur du jardin.
     */
    public void AddJardin(String nomJardin, int dimx, int dimy);

    /**
     * Méthode DeleteJardin Supprime (si existant) le jardin.
     * @param nomJardin
     *          String qui correspond au nom du jardin.
     */
    public void DeleteJardin(String nomJardin);

    /**
     * Méthode getAllJardin Récupère tous les noms de jardins sous forme d'une JList
     * @return retourne la liste de jardin
     */
    public JList getAllJardin(); 

    /**
     * Méthode getJListJardin Récupère tous les noms de jardins sous forme d'une JList
     * @return retourne la liste de jardin
     */
    public JList getJListJardin();

    /**
     * Méthode getX Récupère la dimension en longueur
     * @param nomJardin
     *          String qui correspond au nom du jardin.
     * @return retourne la dimension voulu
     */
    public int getX(String nomjardin);

    /**
     * Méthode getY Récupère la dimension en hauteur
     * @param nomJardin
     *          String qui correspond au nom du jardin.
     * @return retourne la dimension voulu
     */
    public int getY(String nomjardin);

    /**
     * Méthode setSelect Stocke le jardin sélectionnée du scrollPane
     */
    public void setSelect(String sel);
}