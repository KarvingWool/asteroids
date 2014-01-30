package asteroids.asteroids;

import raw.Asteroid;
import raw.Laser;
import raw.Ship;
import raw.VectorShape;

public class App {

    public static void main(String[] args) {

        Physics p = new Physics();
        p.game();
    }
}
