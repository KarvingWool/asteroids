package asteroids.asteroids;

import java.util.ArrayList;
import raw.Asteroid;
import raw.Laser;
import raw.Ship;
import raw.VectorShape;

public class Physics {

    private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    private ArrayList<Laser> lasers = new ArrayList<Laser>();
    private Ship ship;

    public Physics() {
        this.ship = new Ship();
        for (int i = 0; i < 20; i++) {
            Asteroid a = new Asteroid();
            asteroids.add(a);
        }
    }

    public void game() {
        while (ship.getAlive()) {

            collisionCount();
            deadRemoval();
        }

    }

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
    
    public void movement(VectorShape v){
        
        v.setVelX(Math.sin(Math.toRadians(v.getFaceDir()+90))*v.getSpeed());
        v.setVelY(Math.cos(Math.toRadians(v.getFaceDir()+90))*v.getSpeed());
        
        v.setX(v.getX()+v.getVelX());
        v.setY(v.getY()+v.getVelY());
    }

    public void shoot() {
        Laser l = new Laser(ship.getX(), ship.getY(), ship.getFaceDir());
        lasers.add(l);
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
}
