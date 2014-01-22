package asteroids.asteroids;

import raw.Asteroid;
import raw.Laser;
import raw.Ship;
import raw.VectorShape;

public class App {

    public static void main(String[] args) {
        Physics p = new Physics();

        for (int i = 0; i < 20; i++) {
            p.shoot();
        }
        p.collisionCount();
    }
}
