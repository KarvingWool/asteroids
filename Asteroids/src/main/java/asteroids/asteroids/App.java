package asteroids.asteroids;

import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.*;


public class App {

    public static void main(String[] args) {
        
       Physics p = new Physics(800, 500, 20);
       

   Kayttoliittyma k = new Kayttoliittyma(p);
        Game g = new Game(p, k);
        k.setup();
        g.loop();
        
    }
}
