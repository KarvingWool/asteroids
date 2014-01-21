package asteroids.asteroids;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import raw.Asteroid;
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

    public void shipStartingPositionCorrect() {
        Ship a = new Ship();
        assertEquals(0.0, a.getX());
        assertEquals(0.0, a.getY());
        assertEquals(0.0, a.getFaceDir());
    }

    public void shipAliveAndNotMovingInBeginning() {
        Ship a = new Ship();
        assertEquals(0.0, a.getVelX());
        assertEquals(0.0, a.getVelY());
        assertEquals(true, a.getAlive());
    }

    public void VectorShapeSettersWork() {
        Ship a = new Ship();

        a.setVelX(2.0);
        assertEquals(2.0, a.getVelX());

        a.setVelY(12.0);
        assertEquals(12.0, a.getVelY());

        a.setX(107.0);
        assertEquals(107.0, a.getX());

        a.setY(2.7);
        assertEquals(2.7, a.getY());

        a.setAlive(false);
        assertEquals(false, a.getAlive());

        a.setFaceDir(-32.0);
        assertEquals(-32.0, a.getFaceDir());

        a.setMoveDir(-2.0);
        assertEquals(-2.0, a.getMoveDir());

    }
}
