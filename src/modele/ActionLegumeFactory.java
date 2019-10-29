/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.modele;

import java.util.*;
import java.time.LocalDate;

import src.modele.ActionLegumeType;

/*
** Une action est quelque chose qu'on fait à un moment sur une parcelle.
*/

public abstract class ActionLegumeFactory
{
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
    public abstract void NouvelleActionLegume(LocalDate date, ActionLegumeType type, int idParcelle, String legume);

    /**
     * Méthode GetActionJardin requete permettant de récupérer par la suite les actions effectuées sur une parcelle.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     */
    public abstract void GetActionJardin(int idParcelle);

    /**
     * Méthode ActionToString convertit le type d'action en un string.
     * @param act
     *          ActionLegumeType est le type de l'action réalisé.
     * @return 
     *          String action réalisé
     */
    public abstract String ActionToString(ActionLegumeType act);

    /**
     * Méthode StringToAction convertit le string en un type d'action sur le sol.
     * @param act
     *          String est l'action réalisé.
     * @return 
     *          ActionLegumeType action réalisé
     */
    public abstract ActionLegumeType StringToAction(String act);  

    /**
     * Méthode VerifPresenceCulture vérifie la présence d'une culture sur une parcelle.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     * @return
     *          boolean true=présence d'une culture, false=absence de culture.
     */
    public abstract boolean VerifPresenceCulture(int idParcelle);

    /**
     * Méthode destructCulture enlève une culture sur une parcelle suite à une action.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     */
    public abstract void destructCulture(int idParcelle);
}