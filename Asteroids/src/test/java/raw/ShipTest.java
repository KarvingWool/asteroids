package raw;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ShipTest {
    Ship s;
    
    @Before
    public void before() {
        s = new Ship();
    }

    @Test
    public void testShipStartingPositionCorrect() {

        assertEquals(0.0, s.getX(), 0.001);
        assertEquals(0.0, s.getY(), 0.001);
        assertEquals(90, s.getFaceDir(), 0.01);
    }

    @Test
    public void testShipAliveAndNotMovingBeginning() {
        assertEquals(0.0, s.getVelX(), 0.01);
        assertEquals(0.0, s.getVelY(), 0.01);
        assertEquals(true, s.getAlive());
    }

    @Test
    public void testShipAsteroidCollision() {
        Asteroid a = new Asteroid();

        a.setX(0);
        a.setY(0);
        assertTrue(s.collision(a));

        s.setX(50);
        assertFalse(s.collision(a));
    }

    @Test
    public void testShipTurning() {
        
        for(int i=0;i<125;i++){
            s.turnLeft();
        }
        assertEquals(325, s.getFaceDir(), 0.01);
        
        for(int i=0;i<45;i++){
            s.turnRight();
        }
        assertEquals(10, s.getFaceDir(), 0.01);
    }
    
}
