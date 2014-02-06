package asteroids.asteroids;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.Physics;

/**
 * Handles the creating of the JFrame, and running of the Game loop.
 *
 * @author xvax@cs
 */
public class Game extends JFrame {

    private Physics p;
    private Kayttoliittyma k;
    private JFrame frame;


    public Game(Physics p, Kayttoliittyma k) {
        this.p = p;
        this.k = k;
    }

    public void loop() {
        p.setup();
        k.setup();
        
        while(p.getShip().getAlive()){
            k.render();
            p.roundOfMovement();
            p.collisionCount();
            p.deadRemoval();
        }
        
    }

}
