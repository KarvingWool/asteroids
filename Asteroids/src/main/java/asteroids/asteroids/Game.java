package asteroids.asteroids;
import kayttoliittyma.Gui;
import scores.Highscore;
import sovelluslogiikka.Physics;

/**
 * Handles the running of the Game loop.
 *
 * @author xvax@cs
 */
public class Game implements Runnable {

    private Physics p;
    private Gui k;
    Highscore scores;
    

    public Game() {
        this.p = new Physics(800, 500, 30);
        this.scores = new Highscore(p);
        this.k = new Gui(p, scores);
        
    }

    /**
     * The game loop is run in this method.
     */
    @Override
    public void run() {
        if(scores.equals("Null:00")){
            scores.getHighscore();
        }
        

        k.setup();
        while (p.getShip().getAlive()) {

            k.render();
            try {
                Thread.sleep(33);
            } catch (Exception e) {
            }
            p.shipInputReaction();
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
        scores.checkScore();

    }

    public Physics getPhysics() {
        return p;
    }

    public Gui getK() {
        return k;
    }
    
    public Game getGame(){
        return this;
    }
    
}
