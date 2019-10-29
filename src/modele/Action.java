/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.modele;

import java.util.*;
import java.time.LocalDate;

/*
** Une action est quelque chose qu'on fait à un moment sur une parcelle.
*/

public abstract class Action
{
	/**
     * Méthode NouvelleActionSol Ajoute une nouvelle action sur le sol.
     * @param date
     *          LocalDate à la dat de réalisation de l'action.
     * @param type
     *          ActionSolType le type de l'action effectué.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     */
    public abstract void NouvelleActionSol(LocalDate date, ActionSolType type, int idParcelle);

    /**
     * Méthode GetActionJardin lance une requette permettant de récupérer par la suite toutes les actions sur la parcelle sélectionnée.
     * @param idParcelle
     *          int id de la parcelle concernée par l'action.
     */
    public abstract void GetActionJardin(int idParcelle);

    /**
     * Méthode ActionToString convertit le type d'action en un string.
     * @param act
     *          ActionSolType est le type de l'action réalisé.
     * @return 
     *			String est le type de l'action réalisé
     */
    public abstract String ActionToString(ActionSolType act);

    /**
     * Méthode ActionSolType convertit le string en un type d'action sur le sol.
     * @param act
     *          String est l'action réalisé.
     * @return 
     *			ActionSolType est le type de l'action réalisé
     */
    public abstract ActionSolType StringToAction(String act);    
}