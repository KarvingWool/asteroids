package asteroids.asteroids;

import org.junit.Test;
import static org.junit.Assert.*;
import raw.Asteroid;
import org.junit.Before;

public class PhysicsTest {
    Physics p;
    
    @Before
    public void before(){
        p = new Physics();
    }

    @Test
    public void testAsteroidLaserStartAmount() {
       
        
        assertEquals(20, p.getAsteroids().size());
        assertEquals(0, p.getLasers().size());
    }

    @Test
    public void testCollisionCount() {
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
    public void testMovementCalculationAccuracy(){
        p.getShip().setVelX(5);
        p.getShip().setVelY(-3);
        p.movement(p.getShip());
        assertEquals(5, p.getShip().getX(), 0.01);
        assertEquals(-3, p.getShip().getY(), 0.01);
    }

    @Test
    public void testAccelerateCalculatesVelXYCorrectly() {
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
        assertEquals(10, p.getShip().getX(), 0.01);
        assertEquals(5, p.getShip().getY(), 0.01);
    }
    
    @Test
    public void testDecelerateCalculatesVelXYCorrectly(){
        p.getShip().setFaceDir(180);

        for (int i = 0; i < 5; i++) {
            p.decelerate(p.getShip());
        }
        p.movement(p.getShip());
        assertEquals(-5, p.getShip().getX(), 0.01);
   
        p.getShip().setFaceDir(90);
        for (int i = 0; i < 4; i++) {
            p.decelerate(p.getShip());
        }

        p.movement(p.getShip());
        assertEquals(-10, p.getShip().getX(), 0.01);
        assertEquals(-4, p.getShip().getY(), 0.01);
    }

    @Test
    public void testRoundOfMovement() {
        p.getShip().setFaceDir(270);
        p.shoot();
        p.getShip().setVelY(-3);
        
        double astX = p.getAsteroids().get(0).getX();
        double astVelX = p.getAsteroids().get(0).getVelX();
        double astY = p.getAsteroids().get(0).getY();
        double astVelY = p.getAsteroids().get(0).getVelY();
        p.roundOfMovement();

        assertEquals(astX+astVelX, p.getAsteroids().get(0).getX(), 0.01);
        assertEquals(astY+astVelY, p.getAsteroids().get(0).getY(), 0.01);
        assertEquals(-10, p.getLasers().get(0).getY(), 0.01);
        assertEquals(-3, p.getShip().getY(), 0.01);
    }
}
