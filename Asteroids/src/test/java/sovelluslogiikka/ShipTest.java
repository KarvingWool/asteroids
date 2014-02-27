package sovelluslogiikka;

import sovelluslogiikka.Ship;
import sovelluslogiikka.Asteroid;
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
    public void testTurnClockwise() {
        
        for(int i=0;i<6;i++){
            s.turnClockwise();
        }
        assertEquals(330, s.getFaceDir(), 0.01);
        
        for(int i=0;i<3;i++){
            s.turnAntiClockwise();
        }
        assertEquals(30, s.getFaceDir(), 0.01);
    }
    
    @Test
    public void testSetFaceDirTransalatesNumberstoBetween0And360(){
        s.setFaceDir(359);
        assertEquals(359, s.getFaceDir(), 0.1);
        s.setFaceDir(1500);
        assertEquals(60, s.getFaceDir(), 0.1);
        s.setFaceDir(0);
        assertEquals(0, s.getFaceDir(), 0.1);
        s.setFaceDir(-800);
        assertEquals(280, s.getFaceDir(), 0.1);
    }
    
}
