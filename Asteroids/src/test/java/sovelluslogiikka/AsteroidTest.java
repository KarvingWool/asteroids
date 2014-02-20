package sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AsteroidTest {

    Physics p = new Physics(400, 200);

    @Before
    public void before() {
        p.spawnAsteroids();
    }

    @Test
    public void testStartingRotationPosition() {

        for (Asteroid a : p.getAsteroids()) {
            if (a.getRotationPosition() > 360 || a.getRotationPosition() < -1) {
                assertTrue(false);
            }
        }
    }

    @Test
    public void testRotationPositionNeverExceedsGivenAmount() {

        for (int i = 0; i < 360; i++) {
            for (Asteroid a : p.getAsteroids()) {
                a.getRotationPosition();
                if (a.getRotationPosition() > 360 || a.getRotationPosition() < -1) {
                    assertTrue(false);
                }
            }

        }

    }

    @Test
    public void testAsteroidStartingPostion() {
        int i = 0;
        for (Asteroid ast : p.getAsteroids()) {

            if (ast.getY() < 0 || ast.getY() > p.getHeight()) {
                assertTrue(false);
            }

            if (ast.getY() > 20) {
                if (ast.getY() < p.getHeight() - 20) {
                    assertTrue(false);
                }
            }
            if (ast.getX() > p.getWidth() || ast.getX() < 0) {
                assertTrue(false);
            }
        }

    }
}
