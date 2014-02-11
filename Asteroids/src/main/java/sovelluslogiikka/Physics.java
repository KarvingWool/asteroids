package sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Sets the height and width limits to the given parameters. Creates one
     * ship and 20 asteroids, adding the asteroids to their array.
     *
     * @param width
     * @param height
     */
    public Physics(int width, int height) {
        this.height = height;
        this.width = width;
        this.ship = new Ship();
        for (int i = 0; i < 2; i++) {
            Asteroid a = new Asteroid();
            asteroids.add(a);
        }
    }
    /**
     * Puts all the asteroids and the ship in their starting position.
     */
    public void setup() {
        ship.setX(width / 2);
        ship.setY(height / 2);
        for (Asteroid a : asteroids) {
            asteroidStartingPoint(a);
        }
    }

    /**
     * Gives asteroid a its starting position.
     *
     * @param a
     */
    public void asteroidStartingPoint(Asteroid a) {
        a.setX(random.nextInt(width));
        if (random.nextBoolean()) {
            a.setVelX(-1 - random.nextInt(2));
        } else {
            a.setVelX(random.nextInt(2) + 1);
        }

        if (random.nextBoolean()) {
            a.setY(height);
            a.setVelY(-1 - random.nextInt(2));
        } else {
            a.setY(0);
            a.setVelY(1 + random.nextInt(2));
        }

    }

    /**
     * Tests for collisions between the ship and all asteroids, and between all
     * lasers and asteroids.
     */
    public void collisionCount() {
        for (Asteroid a : asteroids) {
            if (ship.collision(a)) {
                ship.setAlive(false);
            }
            for (Laser l : lasers) {
                if (l.collision(a) && l.getAlive() && a.getAlive()) {
                    l.setAlive(false);
                    a.setAlive(false);
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
        for (int d : dead) {
            lasers.remove(d - i);
            i++;
        }
    }

    /**
     * Applys the method movement to every VectorShape
     * still present.
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
     * Adds the velX and velY of parameter v, to x and y
     * respectively. If the new x or y were to be out of bounds,
     * the method wraps the x or y to the opposite side of the
     * field.
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
    }

    /**
     * Calculates the change to VelX and VelY depending on the shapes facing
     * direction.
     *
     * @param v
     */
    public void accelerate(VectorShape v) {
        v.setVelY(v.getVelY() + Math.sin(Math.toRadians(v.getFaceDir())));
        v.setVelX(v.getVelX() - Math.cos(Math.toRadians(v.getFaceDir())));
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
        Laser l = new Laser(ship.getX(), ship.getY(), ship.getFaceDir());
        lasers.add(l);

        while ((l.getVelX() * l.getVelX() + (l.getVelY() * l.getVelY())) < 100) {
            accelerate(l);
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
}
