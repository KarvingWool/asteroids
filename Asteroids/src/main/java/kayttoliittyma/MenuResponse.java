/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import asteroids.asteroids.Game;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author JJV
 */
public class MenuResponse implements ActionListener{
    private Menu m;
    private JFrame frame;
    
    public MenuResponse(Menu m, JFrame frame){
        this.m = m;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("10".equals(e.getActionCommand())){
            m.setAsteroidAmount(10);
        }
        if("20".equals(e.getActionCommand())){
            m.setAsteroidAmount(20);
        }
        if("30".equals(e.getActionCommand())){
            m.setAsteroidAmount(30);
        }
    }

    
}
