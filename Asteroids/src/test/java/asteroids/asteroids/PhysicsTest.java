package asteroids.asteroids;

import org.junit.Test;
import static org.junit.Assert.*;
import raw.Asteroid;

public class PhysicsTest {

    public PhysicsTest() {
    }

    @Test
    public void testAsteroidLaserStartAmount() {
        Physics p = new Physics();
        assertEquals(20, p.getAsteroids().size());
        assertEquals(0, p.getLasers().size());
    }

    @Test
    public void testLaserStartingNumbersThroughShoot() {
        Physics p = new Physics();
        p.getShip().setX(20);
        p.getShip().setY(-15);
        p.getShip().setFaceDir(15);
        p.shoot();
        p.shoot();
        p.shoot();

        assertEquals(3, p.getLasers().size());
        assertEquals(10, p.getLasers().get(0).getSpeed(), 0.1);
        
        for (int i = 0; i < 3; i++) {
            assertEquals(20.0, p.getLasers().get(i).getX(), 0.01);
            assertEquals(-15.0, p.getLasers().get(i).getY(), 0.01);
            assertEquals(15.0, p.getLasers().get(i).getMoveDir(), 0.01);
        }
    }

    @Test
    public void testCollisionCount() {
        Physics p = new Physics();

        for (int i = 0; i < 20; i++) {
            p.getAsteroids().get(i).setX(0);
            p.getAsteroids().get(i).setY(0);
            p.shoot();
        }
        p.collisionCount();
        assertFalse(p.getShip().getAlive());
        for (int i = 0; i < 20; i++) {
            assertFalse(p.getAsteroids().get(i).getAlive());
            assertFalse(p.getLasers().get(i).getAlive());
        }

    }

    @Test
    public void testDeadRemoval() {
        Physics p = new Physics();
        p.shoot();
        p.shoot();
        for (Asteroid a : p.getAsteroids()) {
            a.setX(0);
            a.setY(0);
        }
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

    @Test
    public void movementCalculatesVelXYCorrectly() {
        Physics p = new Physics();

        p.getShip().setFaceDir(180);
        p.getShip().setSpeed(15);

        p.movement(p.getShip());

        assertEquals(-15, p.getShip().getX(), 0.01);
        assertEquals(0, p.getShip().getY(), 0.01);
    }
}
