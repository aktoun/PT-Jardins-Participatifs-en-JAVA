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

import src.vue.TableauHistoriqueLegume;
import src.vue.TableauHistoriqueSol;

import src.controleur.Fermeture;

public class JTableHistorique extends JFrame 
{
    /**
     * Constructeur JTableHistorique Accueil pour l'historique des actions sur le sol et les légumes.
     * @param id
     *          int id de la parcelle selectionnée.
     */
    public JTableHistorique(int id) 
    {
        
        super("Historique de la parcelle");
        this.addWindowListener(new Fermeture(this));
        setSize(1000,400);
        setLocationRelativeTo(null);
        setResizable(false);

        GridLayout gestionnaire = new GridLayout(1,2);
        setLayout(gestionnaire);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        JLabel titre1 = new JLabel("Historique des actions avec les légumes", JLabel.CENTER);
        JTable tableau = new JTable(new TableauHistoriqueLegume(id));

        JLabel titre2 = new JLabel("Historique des actions sur le sol", JLabel.CENTER);
        JTable tableau2 = new JTable(new TableauHistoriqueSol(id));
 
        panel1.add(titre1);
        panel1.add(new JScrollPane(tableau), BorderLayout.CENTER);
        panel2.add(titre2);
        panel2.add(new JScrollPane(tableau2), BorderLayout.CENTER);

        add(panel1);
        add(panel2);
        setVisible(true);
    }
}