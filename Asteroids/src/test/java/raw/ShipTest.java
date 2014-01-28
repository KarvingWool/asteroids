package raw;


import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTest {

    public ShipTest() {
    }


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
    public void testShipAsteroidCollision() {
        Asteroid a = new Asteroid();
        Ship s = new Ship();

        
        a.setX(0);
        a.setY(0);
        assertTrue(s.collision(a));

        s.setX(50);
        assertFalse(s.collision(a));
    }
}
