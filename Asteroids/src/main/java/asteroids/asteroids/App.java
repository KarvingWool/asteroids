package asteroids.asteroids;

import raw.Asteroid;
import raw.Laser;
import raw.Ship;
import raw.VectorShape;

public class App {

    public static void main(String[] args) {
        Ship a = new Ship();
        
        a.turnLeft();
        a.turnLeft();
        a.turnLeft();
        System.out.println(a.getFaceDir());
    }
}
