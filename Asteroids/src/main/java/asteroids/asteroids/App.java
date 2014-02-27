package asteroids.asteroids;

import javax.swing.SwingUtilities;
import kayttoliittyma.Menu;

/**
 * Contains the Apps main method, which starts up the App.
 * @author JJV
 */

public class App {

    public static void main(String[] args) {




        Game g = new Game();
        Menu m = new Menu(g);
              SwingUtilities.invokeLater(m);


        while (true) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            if (!m.isActive()) {
                g.setGameSpeed(m.getSpeed());
                g.setAsteroidAmount(m.getAsteroidAmount());
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                g.run();
                break;
            }
        }




    }
}
