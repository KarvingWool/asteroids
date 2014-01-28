package raw;

import asteroids.asteroids.Physics;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaserTest {

    public LaserTest() {
    }

    @Test
    public void testLaserAsteroidCollision() {
        Asteroid a = new Asteroid();
        Laser l = new Laser(0, 0, 0);

        a.setX(0);
        a.setY(0);
        assertTrue(l.collision(a));

        l.setX(-45);
        assertFalse(l.collision(a));
    }
    
    @Test
    public void testLaserStartingNumbers(){
        Physics p = new Physics();
        
        p.getShip().setX(-20);
        p.getShip().setY(50);
        p.shoot();
    }
}
