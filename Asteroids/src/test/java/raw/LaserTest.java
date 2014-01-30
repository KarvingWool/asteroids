package raw;

import asteroids.asteroids.Physics;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LaserTest {
    Laser l;
    
    @Before
    public void before(){
        l= new Laser(0, 0, 0);
    }
    

    @Test
    public void testLaserStartingAtributesThroughShoot() {
        Physics p = new Physics();
        
        p.getShip().setX(20);
        p.getShip().setY(-15);
        p.getShip().setFaceDir(0);
        p.shoot();
        p.shoot();
        p.shoot();

        assertEquals(3, p.getLasers().size());
        assertEquals(-10, p.getLasers().get(0).getVelX(), 0.1);

        for (int i = 0; i < 3; i++) {
            assertEquals(20.0, p.getLasers().get(i).getX(), 0.01);
            assertEquals(-15.0, p.getLasers().get(i).getY(), 0.01);
        }
    }
    
    @Test
    public void testLaserAsteroidCollision() {
        Asteroid a = new Asteroid();

        a.setX(0);
        a.setY(0);
        assertTrue(l.collision(a));

        l.setX(-45);
        assertFalse(l.collision(a));
    }
}
