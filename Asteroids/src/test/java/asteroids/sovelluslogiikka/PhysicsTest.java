package asteroids.sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PhysicsTest {

    Physics p;

    @Before
    public void before() {
        p = new Physics(400, 200);
        p.spawnAsteroids();
    }

    @Test
    public void testAsteroidLaserStartAmount() {

        assertEquals(20, p.getAsteroids().size());
        assertEquals(0, p.getLasers().size());
    }

    @Test
    public void testCollisionCount() {
        for (int i = 0; i < 10; i++) {
            p.getAsteroids().get(i).setX(p.getWidth() / 2);
            p.getAsteroids().get(i).setY(p.getHeight() / 2);
            p.shoot();
        }
        //      assertTrue(p.getShip().getAlive());
        p.collisionCount();
        assertFalse(p.getShip().getAlive());
        for (int i = 0; i < 10; i++) {
            assertFalse(p.getAsteroids().get(i).getAlive());
            assertFalse(p.getLasers().get(i).getAlive());
        }

    }

    @Test
    public void testDeadLasersDontCollide() {
        p.shoot();
        p.getLasers().get(0).setAlive(false);
        Asteroid a = p.getAsteroids().get(0);
        a.setX(p.getWidth() / 2);
        a.setY(p.getHeight() / 2);
        p.collisionCount();
        assertTrue(a.getAlive());
    }

    @Test
    public void testDeadAsteroidsDontCollide() {
        p.shoot();
        Asteroid a = p.getAsteroids().get(0);
        Laser l = p.getLasers().get(0);
        a.setAlive(false);
        a.setX(p.getWidth() / 2);
        a.setY(p.getHeight() / 2);
        p.collisionCount();

        assertTrue(l.getAlive());
        assertTrue(p.getShip().getAlive());
    }

    @Test
    public void testDeadRemovalAsteroids() {
        p.shoot();
        p.shoot();
        for (Asteroid a : p.getAsteroids()) {
            a.setX(p.getWidth() / 2);
            a.setY(p.getHeight() / 2);
        }
        p.collisionCount();
        p.deadRemoval();
        assertEquals(18, p.getAsteroids().size());

        for (int i = 0; i < 5; i++) {
            p.shoot();
        }
        p.collisionCount();
        p.deadRemoval();
        assertEquals(13, p.getAsteroids().size());

    }

    @Test
    public void testDeadRemovalLasers() {
        p.shoot();
        p.shoot();
        for (int i = 0; i < 6; i++) {
            p.getAsteroids().get(i).setX(p.getWidth() / 2);
            p.getAsteroids().get(i).setY(p.getHeight() / 2);
        }
        p.collisionCount();
        p.deadRemoval();
        assertEquals(0, p.getLasers().size());

        for (int i = 0; i < 10; i++) {
            p.shoot();
        }

        p.collisionCount();
        p.deadRemoval();
        assertEquals(6, p.getLasers().size());
    }

    @Test
    public void testMovementCalculationAccuracy() {
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
    public void testMovementWrapAroundXAxis() {
        p.getShip().setX(p.getWidth() - 1);
        p.getShip().setVelX(2);
        p.movement(p.getShip());
        assertEquals(0, p.getShip().getX(), 0.01);
        p.movement(p.getShip());
        assertEquals(2, p.getShip().getX(), 0.01);

        p.getShip().setVelX(-5);
        p.movement(p.getShip());
        assertEquals(p.getWidth(), p.getShip().getX(), 0.01);
        p.movement(p.getShip());
        assertEquals(p.getWidth() - 5, p.getShip().getX(), 0.01);
    }

    @Test
    public void testMovementWrapAroundYAxis() {
        p.getShip().setY(p.getHeight() - 1);
        p.getShip().setVelY(2);
        p.movement(p.getShip());
        assertEquals(0, p.getShip().getY(), 0.01);
        p.movement(p.getShip());
        assertEquals(2, p.getShip().getY(), 0.01);

        p.getShip().setVelY(-5);
        p.movement(p.getShip());
        assertEquals(p.getHeight(), p.getShip().getY(), 0.01);
        p.movement(p.getShip());
        assertEquals(p.getHeight() - 5, p.getShip().getY(), 0.01);
    }

    @Test
    public void testAccelerate() {
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
    public void testAccelerateHasMax() {
        p.getShip().setVelY(19);
        p.accelerate(p.getShip());
        assertEquals(19, p.getShip().getVelY(), 0.01);

        p.getShip().setVelY(19);
        p.getShip().setVelX(6);
        p.getShip().setFaceDir(180);
        p.accelerate(p.getShip());
        assertEquals(6, p.getShip().getVelX(), 0.01);

    }

    @Test
    public void testDecelerate() {
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
    public void testDecelerateHasMax() {
        p.getShip().setFaceDir(270);
        p.getShip().setVelY(19);
        p.decelerate(p.getShip());
        assertEquals(19, p.getShip().getVelY(), 0.01);

        p.getShip().setVelY(19);
        p.getShip().setVelX(6);
        p.getShip().setFaceDir(0);
        p.decelerate(p.getShip());
        assertEquals(6, p.getShip().getVelX(), 0.01);

    }

    @Test
    public void testRoundOfMovement() {
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

    @Test
    public void testShipInputReactionAccelerate() {
        p.shipInputReaction();
        p.getShip().setAccelerating(true);
        p.shipInputReaction();
        assertEquals(1, p.getShip().getVelY(), 0.01);
    }

    @Test
    public void testShipInputReactionDelerate() {
        p.shipInputReaction();
        p.getShip().setDecelerating(true);
        p.shipInputReaction();
        assertEquals(-1, p.getShip().getVelY(), 0.01);
    }

    @Test
    public void testShipInputReactionTurnAntiClockwise() {
        p.shipInputReaction();
        p.getShip().setTurningAntiClockwise(true);
        p.shipInputReaction();
        assertEquals(110, p.getShip().getFaceDir(), 0.1);
    }

    @Test
    public void testShipInputReactionTurnClockwise() {
        p.shipInputReaction();
        p.getShip().setTurningClockwise(true);
        p.shipInputReaction();
        assertEquals(70, p.getShip().getFaceDir(), 0.1);
    }

    @Test
    public void testScoreCount() {
        assertEquals(0, p.calculateScore(), 0.1);
        p.spawnAsteroids();
        for (int i = 0; i < 5; i++) {
            p.shoot();
            p.getAsteroids().get(i).setX(p.getWidth() / 2);
            p.getAsteroids().get(i).setY(p.getHeight() / 2);
        }
        p.collisionCount();
        assertEquals(10, p.calculateScore(), 0.1);
    }

    @Test
    public void testScoreProportionalToAsteroidAmount() {
        assertEquals(0, p.calculateScore(), 0.1);
        p.setAsteroidAmount(30);
        p.spawnAsteroids();
        for (int i = 0; i < 5; i++) {
            p.shoot();
            p.getAsteroids().get(i).setX(p.getWidth() / 2);
            p.getAsteroids().get(i).setY(p.getHeight() / 2);
        }
        p.collisionCount();
        assertEquals(15, p.calculateScore(), 0.1);
        p.setAsteroidAmount(10);
        p.shoot();
        p.getAsteroids().get(10).setX(p.getWidth() / 2);
        p.getAsteroids().get(10).setY(p.getHeight() / 2);
        p.collisionCount();
        assertEquals(16, p.calculateScore());
    }

    @Test
    public void testCalculateScoreAffectedByGameSpeed() {
        assertEquals(0, p.calculateScore());
        p.spawnAsteroids();
        for (int i = 0; i < 5; i++) {
            p.shoot();
            p.getAsteroids().get(i).setX(p.getWidth() / 2);
            p.getAsteroids().get(i).setY(p.getHeight() / 2);
        }
        p.collisionCount();
        p.setGameSpeed(1.2);
        assertEquals(12, p.calculateScore());
        p.setGameSpeed(1.5);
        assertEquals(15, p.calculateScore());
        p.setGameSpeed(2);
        assertEquals(20, p.calculateScore());
    }
}
