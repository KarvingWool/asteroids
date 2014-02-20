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
    private Gui gui;
    private Highscore scores;
    private double gameSpeed = 1;
    private int asteroidAmount = 20;

    public Game() {
        this.p = new Physics(800, 500);
        this.scores = new Highscore(p);
        this.gui = new Gui(p, scores);

    }

    /**
     * The game loop is run in this method.
     */
    @Override
    public void run() {

        if (scores.equals("Null:00")) {
            scores.getHighscore();
        }


        gui.setup();
        p.setAsteroidAmount(asteroidAmount);
        p.setGameSpeed(gameSpeed);
        p.spawnAsteroids();
        while (p.getShip().getAlive()) {

            if (p.getFirstRound()) {
                gui.render();
                try {
                    Thread.sleep(4000);
                    p.setFirstRound(false);
                } catch (Exception e) {
                }
            }

            gui.render();
            try {
                Thread.sleep(33);
            } catch (Exception e) {
            }
            p.shipInputReaction();
            p.roundOfMovement();
            p.collisionCount();
            p.deadRemoval();

            if (p.getAsteroids().isEmpty()) {
                p.getShip().setX(p.getWidth() / 2);
                p.getShip().setY(p.getHeight() / 2);
                p.getShip().setFaceDir(90);
                p.setLevelup(true);
                gui.render();
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                }
                p.setAsteroidAmount(p.getAsteroidAmount() + 5);
                p.spawnAsteroids();
                p.getShip().setVelX(0);
                p.getShip().setVelY(0);
                p.setLevelup(false);
            }
        }
        gui.render();
        scores.checkScore();

    }

    public Physics getPhysics() {
        return p;
    }

    public Gui getGui() {
        return gui;
    }

    public Game getGame() {
        return this;
    }

    public void setGameSpeed(double gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public void setAsteroidAmount(int asteroidAmount) {
        this.asteroidAmount = asteroidAmount;
    }
}
