package sovelluslogiikka;

import java.util.ArrayList;
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
            if (a.calculateRotationPosition() > 360 || a.calculateRotationPosition() < -1) {
                assertTrue(false);
            }
        }
    }

    @Test
    public void testRotationPositionNeverExceedsGivenAmount() {

        for (int i = 0; i < 360; i++) {
            for (Asteroid a : p.getAsteroids()) {
                a.calculateRotationPosition();
                if (a.calculateRotationPosition() > 360 || a.calculateRotationPosition() < -1) {
                    assertTrue(false);
                }
            }

        }

    }

    @Test
    public void testAsteroidStartingPostion() {
        for (int i = 0; i < 20; i++) {
            p.spawnAsteroids();
        }
        for (Asteroid ast : p.getAsteroids()) {

            if (ast.getY() < 0 || ast.getY() > p.getHeight()) {
                assertTrue(false);
            }

            if (ast.getY() > 20) {
                if (ast.getY() < p.getHeight() - 20) {
                    assertTrue(false);
                }
            }

            if (ast.getY() < p.getHeight() - 20) {
                if (ast.getY() > 20) {
                    assertTrue(false);
                }
            }

            if (ast.getX() > p.getWidth() || ast.getX() < 0) {
                assertTrue(false);
            }
        }

    }

    @Test
    public void testAsteroidStartingVelocityX() {
        for (int i = 0; i < 20; i++) {
            p.spawnAsteroids();
        }

        for (Asteroid a : p.getAsteroids()) {
            if (a.getVelX() > -0.5 && a.getVelX() < 0.5) {
                assertTrue(false);
            }

            if (a.getVelX() > 2.5) {
                assertTrue(false);
            }

            if (a.getVelX() < -2.5) {
                assertTrue(false);
            }
        }
    }

    @Test
    public void testAsteroidStartingVelocityY() {
        for (int i = 0; i < 20; i++) {
            p.spawnAsteroids();
        }

        for (Asteroid a : p.getAsteroids()) {
            if (a.getVelY() > -0.5 && a.getVelY() < 0.5) {
                assertTrue(false);
            }

            if (a.getVelY() > 2.5) {
                assertTrue(false);
            }

            if (a.getVelY() < -2.5) {
                assertTrue(false);
            }
        }
    }

    @Test
    public void testAsteroidVelocityXRandomness() {
        ArrayList<Asteroid> ast = new ArrayList<Asteroid>();

        for (int i = 0; i < 20; i++) {
            p.spawnAsteroids();
        }

        for (Asteroid a : p.getAsteroids()) {
            if (a.getVelX() > 0) {
                ast.add(a);
            }
        }

        if (ast.size() < 10 || ast.size() > 390) {
            assertTrue(false);
        }
    }

    @Test
    public void testAsteroidYAndVelocityYRandomness() {
        ArrayList<Asteroid> ast = new ArrayList<Asteroid>();

        for (int i = 0; i < 20; i++) {
            p.spawnAsteroids();
        }

        for (Asteroid a : p.getAsteroids()) {
            if (a.getVelY() > 0) {
                ast.add(a);
            }
        }

        if (ast.size() < 10 || ast.size() > 390) {
            assertTrue(false);
        }
    }

    @Test
    public void testAsteroidVelocityInfluencedByGameSpeed() {
        ArrayList<Asteroid> astX = new ArrayList<Asteroid>();
        ArrayList<Asteroid> astY = new ArrayList<Asteroid>();
        p.setGameSpeed(1.2);
        for (int i = 0; i < 20; i++) {
            p.spawnAsteroids();
        }
        for (Asteroid a : p.getAsteroids()) {
            if (a.getVelX() > 2.5 || a.getVelX() < -2.5) {
                astX.add(a);
            }
            if (a.getVelY() > 2.5 || a.getVelY() < -2.5) {
                astY.add(a);
            }
        }
        
        if(astX.size()<1||astY.size()<1){
            assertTrue(false);
        }
    }

    @Test
    public void testCalculateRotationPosition() {
        p.spawnAsteroids();

        p.getAsteroids().get(0).setRotationDir(true);
        p.getAsteroids().get(0).setRotationPosition(200);
        assertEquals(201, Math.toDegrees(p.getAsteroids().get(0).calculateRotationPosition()), 0.1);

        p.getAsteroids().get(0).setRotationDir(false);
        assertEquals(200, Math.toDegrees(p.getAsteroids().get(0).calculateRotationPosition()), 0.1);
    }

    @Test
    public void testCalculateRotationPositionStaysBetween0And360() {
        p.spawnAsteroids();

        p.getAsteroids().get(0).setRotationDir(true);
        p.getAsteroids().get(0).setRotationPosition(359);
        assertEquals(0, Math.toDegrees(p.getAsteroids().get(0).calculateRotationPosition()), 0.1);
        p.getAsteroids().get(0).setRotationDir(false);
        assertEquals(359, Math.toDegrees(p.getAsteroids().get(0).calculateRotationPosition()), 0.1);

    }
}
