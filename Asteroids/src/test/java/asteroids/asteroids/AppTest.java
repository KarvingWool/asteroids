package asteroids.asteroids;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import raw.Asteroid;
import raw.Laser;
import raw.Ship;

public class AppTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testShipStartingPositionCorrect() {
        Ship a = new Ship();
        assertEquals(0.0, a.getX());
        assertEquals(0.0, a.getY());
        assertEquals(0.0, a.getFaceDir());
    }

    public void testShipAliveAndNotMovingBeginning() {
        Ship a = new Ship();
        assertEquals(0.0, a.getVelX());
        assertEquals(0.0, a.getVelY());
        assertEquals(true, a.getAlive());
    }

    public void testAsteroidRotationVel() {
        Asteroid a = new Asteroid();

        if (a.getRotationVel() < 0 || a.getRotationVel() > 5) {
            assertTrue(false);
        }
    }

    public void testShipAsteroidCollision() {
        Asteroid a = new Asteroid();
        Ship s = new Ship();

        assertTrue(s.collision(a));
    }

    public void testLaserAsteroidCollision() {
        Asteroid a = new Asteroid();
        Laser l = new Laser(0, 0, 0);

        assertTrue(l.collision(a));
    }

    public void testAsteroidLaserStartAmount() {
        Physics p = new Physics();
        assertEquals(20, p.getAsteroids().size());
        assertEquals(0, p.getLasers().size());
    }

    public void testShoot() {
        Physics p = new Physics();
        p.getShip().setX(20);
        p.getShip().setY(-15);
        p.getShip().setFaceDir(15);
        p.shoot();
        p.shoot();
        p.shoot();

        assertEquals(3, p.getLasers().size());

        assertEquals(20.0, p.getLasers().get(0).getX());
        assertEquals(-15.0, p.getLasers().get(0).getY());
        assertEquals(15.0, p.getLasers().get(0).getMoveDir());
    }

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
