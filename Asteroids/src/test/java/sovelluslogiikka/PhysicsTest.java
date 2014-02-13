package sovelluslogiikka;

import sovelluslogiikka.Physics;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.Asteroid;
import org.junit.Before;

public class PhysicsTest {

    Physics p;

    @Before
    public void before() {
        p = new Physics(400, 200, 20);
    }

    @Test
    public void testAsteroidLaserStartAmount() {

        assertEquals(20, p.getAsteroids().size());
        assertEquals(0, p.getLasers().size());
    }

    @Test
    public void testCollisionCount() {
        for (int i = 0; i < p.getAsteroidAmount(); i++) {
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
        p.shoot();
        p.shoot();
        for (Asteroid a : p.getAsteroids()) {
            a.setX(0);
            a.setY(0);
        }
        p.collisionCount();
        p.deadRemoval();
        assertEquals(18, p.getAsteroids().size());
        assertEquals(0, p.getLasers().size());

        for (int i = 0; i < 20; i++) {
            p.shoot();
        }
        p.collisionCount();
        p.deadRemoval();
        assertEquals(0, p.getAsteroids().size());
        assertEquals(2, p.getLasers().size());
    }

    @Test
    public void testMovementCalculationAccuracy() {
        p.setup();
        p.getShip().setVelX(5);
        p.getShip().setVelY(-3);
        p.movement(p.getShip());
        assertEquals(205, p.getShip().getX(), 0.01);
        assertEquals(97, p.getShip().getY(), 0.01);
        
        p.getShip().setY(2);
        p.movement(p.getShip());
        p.movement(p.getShip());
        assertEquals(197, p.getShip().getY(), 0.01);
    }

    @Test
    public void testAccelerateCalculatesVelXYCorrectly() {

        p.setup();
        p.getShip().setFaceDir(180);

        for (int i = 0; i < 5; i++) {
            p.accelerate(p.getShip());
        }
        p.movement(p.getShip());
        p.getShip().setFaceDir(90);

        for (int i = 0; i < 5; i++) {
            p.accelerate(p.getShip());
        }
        p.movement(p.getShip());
        assertEquals(210, p.getShip().getX(), 0.01);
        assertEquals(105, p.getShip().getY(), 0.01);
    }

    @Test
    public void testDecelerateCalculatesVelXYCorrectly() {
        p.setup();
        p.getShip().setFaceDir(180);

        for (int i = 0; i < 5; i++) {
            p.decelerate(p.getShip());
        }
        p.movement(p.getShip());
        assertEquals(195, p.getShip().getX(), 0.01);

        p.getShip().setFaceDir(90);
        for (int i = 0; i < 4; i++) {
            p.decelerate(p.getShip());
        }

        p.movement(p.getShip());
        assertEquals(190, p.getShip().getX(), 0.01);
        assertEquals(96, p.getShip().getY(), 0.01);
    }

    @Test
    public void testRoundOfMovement() {
        p.setup();
        p.getAsteroids().get(0).setX(100);
        p.getAsteroids().get(0).setY(50);
        p.getAsteroids().get(0).setVelX(2);
        p.getAsteroids().get(0).setVelY(-6);
        p.getShip().setFaceDir(270);
        p.shoot();
        p.getShip().setVelY(-3);

        p.roundOfMovement();
        
        assertEquals(102, p.getAsteroids().get(0).getX(), 0.01);
        assertEquals(44, p.getAsteroids().get(0).getY(), 0.01);
        assertEquals(90, p.getLasers().get(0).getY(), 0.01);
        assertEquals(97, p.getShip().getY(), 0.01);
    }
}
