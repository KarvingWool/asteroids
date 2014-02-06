package asteroids.asteroids;

import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.Physics;


public class App {

    public static void main(String[] args) {

        Physics p = new Physics(400, 200);
        Kayttoliittyma k = new Kayttoliittyma(p);
        
        SwingUtilities.invokeLater(k);
 
        while (k.getDrawingBoard() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        k.run();
        while(p.getShip().getAlive()){
            p.roundOfMovement();
            k.getFrame().repaint();
        }
    }
}
