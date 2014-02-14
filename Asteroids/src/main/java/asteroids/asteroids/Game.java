package asteroids.asteroids;


import java.io.File;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.Physics;

/**
 * Handles the creating of the JFrame, and running of the Game loop.
 *
 * @author xvax@cs
 */
public class Game implements Runnable {

    private Physics p;
    private Kayttoliittyma k;
    File file = new File("Highscores.txt");
    

    public Game(Physics p, Kayttoliittyma k) {
        this.p = p;
        this.k = k;
    }

    @Override
    public void run() {

        k.setup();
        while (p.getShip().getAlive()) {

            k.render();
            try {
                Thread.sleep(30);
            } catch (Exception e) {
            }
            p.roundOfMovement();
            p.collisionCount();
            p.deadRemoval();

            if (p.getAsteroids().size() == 0) {
                p.setLevelup(true);
                k.render();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
                p.setAsteroidAmount(p.getAsteroidAmount() + 10);
                p.spawnAsteroids();
                p.getShip().setX(p.getWidth() / 2);
                p.getShip().setY(p.getHeight() / 2);
                p.getShip().setVelX(0);
                p.getShip().setVelY(0);
                p.setLevelup(false);
            }
        }

    }

    public Physics getPhysics() {
        return p;
    }

    public Kayttoliittyma getK() {
        return k;
    }
    
    public Game getGame(){
        return this;
    }
    
}
