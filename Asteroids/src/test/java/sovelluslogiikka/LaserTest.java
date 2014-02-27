package sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LaserTest {

    Laser l;
    Physics p;

    @Before
    public void before() {
        l = new Laser(0, 0, 0);
        p = new Physics(400, 200);
    }

    @Test
    public void testShootAddsToLasers(){
        for(int i=0; i<5;i++){
            assertEquals(i, p.getLasers().size());
            p.shoot();
        }
    }
    @Test
    public void testLaserStartingAtributesThroughShoot() {
        p.getShip().setX(20);
        p.getShip().setY(-15);
        p.getShip().setFaceDir(0);

        for (int i = 0; i < 3; i++) {
            p.shoot();
            assertEquals(-10, p.getLasers().get(i).getVelX(), 0.1);
            assertEquals(20.0, p.getLasers().get(i).getX(), 0.01);
            assertEquals(-15.0, p.getLasers().get(i).getY(), 0.01);
        }
    }

    @Test
    public void testShootAddsShipVelToLaserVelocity() {
        p.getShip().setVelX(5);
        p.getShip().setVelY(4);
        p.shoot();
        assertEquals(5, p.getLasers().get(0).getVelX(), 0.01);
        assertEquals(14, p.getLasers().get(0).getVelY(), 0.01);

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

    @Test
    public void testLaserLifetimeCounter() {
        p.spawnAsteroids();
        for (Asteroid a : p.getAsteroids()) {
            a.setAlive(false);
        }
        l.setVelX(5);
        for (int i = 0; i < 20; i++) {
            p.movement(l);
        }
        assertTrue(l.getAlive());
        p.movement(l);
        assertFalse(l.getAlive());
    }
}
