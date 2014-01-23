package asteroids.asteroids;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import raw.Asteroid;
import raw.Laser;
import raw.Ship;

public class AppTest {


    
    @Test
    public void testShipStartingPositionCorrect() {
        Ship a = new Ship();
        assertEquals(0.0, a.getX(), 0.001);
        assertEquals(0.0, a.getY(), 0.001);
        assertEquals(0.0, a.getFaceDir(), 0.01);
    }

    @Test
    public void testShipAliveAndNotMovingBeginning() {
        Ship a = new Ship();
        assertEquals(0.0, a.getVelX(), 0.01);
        assertEquals(0.0, a.getVelY(), 0.01);
        assertEquals(true, a.getAlive());
    }

    @Test
    public void testAsteroidRotationVel() {
        Asteroid a = new Asteroid();

        if (a.getRotationVel() < 0 || a.getRotationVel() > 5) {
            assertTrue(false);
        }
    }

    @Test
    public void testShipAsteroidCollision() {
        Asteroid a = new Asteroid();
        Ship s = new Ship();

        assertTrue(s.collision(a));
        
        s.setX(50);
        assertFalse(s.collision(a));
    }

    @Test
    public void testLaserAsteroidCollision() {
        Asteroid a = new Asteroid();
        Laser l = new Laser(0, 0, 0);

        assertTrue(l.collision(a));
        
        l.setX(-45);
        assertFalse(l.collision(a));
    }

    @Test
    public void testAsteroidLaserStartAmount() {
        Physics p = new Physics();
        assertEquals(20, p.getAsteroids().size());
        assertEquals(0, p.getLasers().size());
    }

    @Test
    public void testShoot() {
        Physics p = new Physics();
        p.getShip().setX(20);
        p.getShip().setY(-15);
        p.getShip().setFaceDir(15);
        p.shoot();
        p.shoot();
        p.shoot();

        assertEquals(3, p.getLasers().size());

        assertEquals(20.0, p.getLasers().get(0).getX(), 0.01);
        assertEquals(-15.0, p.getLasers().get(0).getY(), 0.01);
        assertEquals(15.0, p.getLasers().get(0).getMoveDir(), 0.01);
    }

    @Test
    public void testCollisionCount() {
        Physics p = new Physics();

        for (int i = 0; i < 20; i++) {
            p.shoot();
        }
        p.collisionCount();
        assertFalse(p.getShip().getAlive());
        for (int i = 0; i < 20; i++) {
            assertFalse(p.getAsteroids().get(i).getAlive());
        }


    }

    @Test
    public void testDeadRemoval() {
        Physics p = new Physics();
        p.shoot();
        p.shoot();
        p.collisionCount();
        p.deadRemoval();
        assertEquals(18, p.getAsteroids().size());
        
        for (int i = 0; i < 18; i++) {
            p.shoot();
        }
        p.collisionCount();
        p.deadRemoval();
        assertEquals(0, p.getAsteroids().size());
    }
}
