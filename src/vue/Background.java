/**
 * @author Loïc BERNARD
 * @author Antoine MAN
 * 
 */

package src.vue;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background extends JPanel 
{
    
    final ImageIcon image = new ImageIcon("img/champs.jpeg");
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

}