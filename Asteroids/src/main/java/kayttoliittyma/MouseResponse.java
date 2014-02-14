/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import asteroids.asteroids.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author JJV
 */
public class MouseResponse implements ActionListener{
    private Game g;
    private JFrame frame;
    
    public MouseResponse(Game g, JFrame frame){
        this.g = g;
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        g.run();
        frame.dispose();
    }
    
}
