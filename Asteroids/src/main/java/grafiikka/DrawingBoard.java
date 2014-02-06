package grafiikka;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import sovelluslogiikka.*;

public class DrawingBoard extends JPanel {

    private Physics p;

    public DrawingBoard(Physics p) {
        this.p = p;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        for (Asteroid a : p.getAsteroids()) {
            g.fillOval((int)a.getX(),(int)a.getY(), a.getHeight(), a.getWidth());
        }
        
        g.setColor(Color.white);    
        for(Laser l : p.getLasers()){
            g.fill3DRect((int)l.getX(), (int)l.getY(), l.getHeight(), l.getWidth(), false);
        }
        
        g.setColor(Color.GREEN);
        g.fillRect((int)p.getShip().getX(),(int) p.getShip().getY(), p.getShip().getHeight(), p.getShip().getWidth());
    }
}
