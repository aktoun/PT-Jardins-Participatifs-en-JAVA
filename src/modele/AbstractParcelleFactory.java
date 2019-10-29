/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.modele;

import java.util.*;
import java.time.*;

import java.sql.*;

import src.modele.Orientation;
import src.modele.Action;

/**
 * Interface principale de l'API
 * Explicite les méthodes permettant de gérer les parcelles (taille, structure, navigation) et les actions sur ces dernières.
 */
public interface AbstractParcelleFactory{


    /**     
     * retourne un itérateur sur les actions réalisées sur la parcelle mais pas sur une ancêtre de celle-ci.
     */
    public void getActions();

    /**     
     * retourne un itérateur sur les actions vraiment réalisées sur la parcelle (y compris sur une ancêtre de celle-ci).
     */
    @SuppressWarnings("unchecked")
    public void getAllActions();
    
    /**     
     * ajoute une action à la parcelle.
     */
    public void addAction(Action a);
    
    /**
     * retourne le type de split de la parcelle (voir Orientation.java) null si pas de sous-parcelle
     */
    public Orientation getSplit(int id);

    /**
     * retourne la sous parcelle gauche (si split vertical) ou haute (si split horizontal).
     * IllegalStateException si la parcelle n'a pas de sous-parcelle
     */
    public int getFirst(int idMere);

    /**
     * retourne la sous parcelle droite (si split vertical) ou basse (si split horizontal).
     * IllegalStateException si la parcelle n'a pas de sous-parcelle
     */
    public int getSecond(int idMere);

    /**
    * fonction recursive qui permet de suprimer toutes les sous-parcelles filles lorsque l'on réunit 2 parcelles jumelles.
    */
    public void delFille(int id, Connection cn);

    /**
     * réunit les sous-parcelles (on oublie les sous-parcelles)
     */
    public void reset(int id);
    
    /**
     *  retourne la parcelle mère de la parcelle (self si parcelle racine du jardin)
     */
    public void getMother(); //Type parcelle

    /**
     *  retourne la parcelle racine de la parcelle (self si racine du jardin)
     */
    public void getRoot(); //Type parcelle

    /**
     *  retourne l'abscisse du coin en haut à gauche de la parcelle
     */
    public int getx0();
    
    /**
     *  retourne l'ordonnée du coin en haut à gauche de la parcelle
     */
    public int gety0();
    
    /**
     *  retourne l'abscisse du coin en bas à droite de la parcelle
     */
    public int getx1();
    
    /**
     *  retourne l'ordonnée du coin en bas à droite de la parcelle
     */
    public int gety1();
    
    /**
     *  retourne le nom du jardin de la parcelle
     */
    public String getNomJardin(int id);

    /**
     * découpe la parcelle actuelle en crééant deux sous-parcelles selon l'orientation passée en argument.
     * Les parcelles sont de taille quasi-identique. La dimension qui est divisé par deux sera arrondi à l'entier supérieur pour la première parcelle. 
     */
    public void SplitParcelle(Orientation o, int id);
    
    /**
     * Une méthode analogue à toString pour faciliter les tests (je ne peux pas la nommer toString car toString est le nom de Object)
     *
     * Si on essaye de le nommer toString() ça donne :
     * Parcelle.java:98: error: default method toString in interface Parcelle overrides a member of java.lang.Object
     * public default String toString(){
     *                       ^
     * 1 error
     *
     * Remarque. Depuis java 8, on peut même avoir des méthodes avec du code.
     * La différence entre classe abstraite et interface s'amenuise...
     * 
     */
    
}