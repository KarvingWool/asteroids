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
        int i  =0;
        while (ship.getAlive()) {
            roundOfMovement();
            collisionCount();
            deadRemoval();
            i++;
            System.out.println(i);
        }
        System.out.println("Game over");
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

    public void roundOfMovement() {
        movement(ship);
        for (Asteroid a : asteroids) {
            movement(a);
        }
        for (Laser l : lasers) {
            movement(l);
        }
    }

    public void movement(VectorShape v) {
        v.setX(v.getX() + v.getVelX());
        v.setY(v.getY() + v.getVelY());
    }

    public void accelerate(VectorShape v) {
        v.setVelY(v.getVelY()+Math.sin(Math.toRadians(v.getFaceDir())));
        v.setVelX(v.getVelX()-Math.cos(Math.toRadians(v.getFaceDir())));
    }
    
    public void decelerate(VectorShape v){
        v.setVelY(v.getVelY()+Math.sin(Math.toRadians(v.getFaceDir()+180)));
        v.setVelX(v.getVelX()-Math.cos(Math.toRadians(v.getFaceDir()+180)));
    }

    public void shoot() {
        Laser l = new Laser(ship.getX(), ship.getY(), ship.getFaceDir());
        lasers.add(l);
        
        while((l.getVelX()*l.getVelX()+(l.getVelY()*l.getVelY()))<100){
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
}
