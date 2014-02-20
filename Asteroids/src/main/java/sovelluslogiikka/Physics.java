package sovelluslogiikka;

import asteroids.asteroids.Game;
import java.util.ArrayList;
import java.util.Random;
import scores.Highscore;

/**
 * Handles the physics and running of the game.
 */
public class Physics {

    private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    private ArrayList<Laser> lasers = new ArrayList<Laser>();
    private Random random = new Random();
    private Ship ship;
    private int height;
    private int width;
    private boolean levelup = false;
    private Highscore hscore;
    private int score = 0;
    private double gameSpeed = 1;
    private int asteroidAmount = 20;
    private boolean firstRound = true; 

    /**
     * Sets the height and width limits to the given parameters. Creates one
     * the ship, and sets it in its place.
     *
     * @param width
     * @param height
     */
    public Physics(int width, int height) {
        this.height = height;
        this.width = width;
        this.ship = new Ship();
        setupShip();
    }

    /**
     * Puts the ship in its starting position.
     */
    public void setupShip() {
        ship.setX(width / 2);
        ship.setY(height / 2);

    }

    /**
     * Spawns all the asteroids and give them their starting positions.
     *
     */
    public void spawnAsteroids() {
        for (int i = 0; i < asteroidAmount; i++) {
            Asteroid a = new Asteroid();
            asteroids.add(a);
            a.setX(random.nextInt(width));

            if (random.nextBoolean()) {
                a.setVelX(gameSpeed * (-0.5 - (random.nextDouble() * 2)));
            } else {
                a.setVelX(gameSpeed * ((random.nextDouble() * 2) + 0.5));
            }

            if (random.nextBoolean()) {
                a.setY(height - random.nextInt(20));
                a.setVelY(gameSpeed * (-0.5 - (random.nextDouble() * 2)));
            } else {
                a.setY(0 + random.nextInt(20));
                a.setVelY(gameSpeed * (0.5 + (random.nextDouble() * 2)));
            }
        }
    }

    /**
     * Tests for collisions between the ship and all asteroids, and between all
     * lasers and asteroids. With a collision, alive is set to false, and in
     * case of destroyed asteroids, the score is raised.
     */
    public void collisionCount() {
        for (int i = 0; i < asteroids.size(); i++) {
            if (ship.collision(asteroids.get(i)) && asteroids.get(i).getAlive()) {
                ship.setAlive(false);
            }
            for (int e = 0; e < lasers.size(); e++) {
                if (lasers.get(e).collision(asteroids.get(i)) && lasers.get(e).getAlive() && asteroids.get(i).getAlive()) {
                    score = score + (asteroidAmount / 10);
                    lasers.get(e).setAlive(false);
                    asteroids.get(i).setAlive(false);
                }
            }

        }
    }

    /**
     * Removes all lasers and asteroids from their corresponding arrays, given
     * if their alive status is false;
     */
    public void deadRemoval() {
        ArrayList<Integer> dead = new ArrayList<Integer>();
        int i = 0;
        for (Asteroid a : asteroids) {
            if (!a.getAlive()) {
                dead.add(i);
            }
            i++;
        }
        i = 0;
        for (int d : dead) {
            asteroids.remove(d - i);
            i++;
        }
        dead.clear();
        i = 0;

        for (Laser l : lasers) {
            if (!l.getAlive()) {
                dead.add(i);
            }
            i++;
        }
        i = 0;
        try {
            for (int d : dead) {
                lasers.remove(d - i);
                i++;
            }
        } catch (Exception e) {
        }
    }

    /**
     * Applys the method movement to every VectorShape still present.
     */
    public void roundOfMovement() {
        movement(ship);
        for (Asteroid a : asteroids) {
            movement(a);
        }
        for (Laser l : lasers) {
            movement(l);
        }
    }

    /**
     * Adds the velX and velY of parameter v, to x and y respectively. If the
     * new x or y were to be out of bounds, the method wraps the x or y to the
     * opposite side of the field.
     *
     * @param v
     */
    public void movement(VectorShape v) {
        v.setX(v.getX() + v.getVelX());
        v.setY(v.getY() + v.getVelY());

        if (v.getX() > this.width) {
            v.setX(0);
        }
        if (v.getX() < 0) {
            v.setX(width);
        }
        if (v.getY() > this.height) {
            v.setY(0);
        }
        if (v.getY() < 0) {
            v.setY(this.height);
        }

        if (v.getClass() == new Laser(0, 0, 0).getClass()) {
            v.setCounter(v.getCounter() + 1);
            if (v.getCounter() > 20) {
                v.setAlive(false);
            }
        }
    }

    public void shipInputReaction() {
        if (getShip().isAccelerating()) {
            accelerate(getShip());
        }
        if (getShip().isDecelerating()) {
            decelerate(getShip());
        }
        if (getShip().isTurningAntiClockwise()) {
            getShip().turnAntiClockwise();
        }
        if (getShip().isTurningClockwise()) {
            getShip().turnClockwise();
        }

    }

    /**
     * Calculates the change to VelX and VelY depending on the shapes facing
     * direction.
     *
     * @param v
     */
    public void accelerate(VectorShape v) {
        if ((v.getVelX() * v.getVelX() + (v.getVelY() * v.getVelY())) < 400) {
            v.setVelY(v.getVelY() + Math.sin(Math.toRadians(v.getFaceDir())));
            v.setVelX(v.getVelX() - Math.cos(Math.toRadians(v.getFaceDir())));
        }
    }

    /**
     * Calculates the change to VelX and VelY depending on the inverse of the
     * shapes facing direction.
     *
     * @param v
     */
    public void decelerate(VectorShape v) {
        v.setVelY(v.getVelY() + Math.sin(Math.toRadians(v.getFaceDir() + 180)));
        v.setVelX(v.getVelX() - Math.cos(Math.toRadians(v.getFaceDir() + 180)));
    }

    /**
     * Creates a laser at the ships position, giving it its direction according
     * to the ships facing direction.
     */
    public void shoot() {
        if (lasers.size() < 10) {
            Laser l = new Laser(ship.getX(), ship.getY(), ship.getFaceDir());
            lasers.add(l);

            while ((l.getVelX() * l.getVelX() + (l.getVelY() * l.getVelY())) < 100) {
                accelerate(l);
            }
            l.setVelX(l.getVelX() + getShip().getVelX());
            l.setVelY(l.getVelY() + getShip().getVelY());
        }
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public Ship getShip() {
        return ship;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getAsteroidAmount() {
        return asteroidAmount;
    }

    public int getScore() {
        return score;
    }

    public boolean getLevelup() {
        return levelup;
    }
    
    public boolean getFirstRound(){
        return firstRound;
    }

    public void setLevelup(boolean levelup) {
        this.levelup = levelup;
    }

    public void setAsteroidAmount(int asteroidAmount) {
        this.asteroidAmount = asteroidAmount;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    public void setGameSpeed(double gameSpeed) {
        this.gameSpeed = gameSpeed;
    }
    
    
    
    
}
