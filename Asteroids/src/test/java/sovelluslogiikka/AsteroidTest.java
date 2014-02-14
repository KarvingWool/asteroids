package sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AsteroidTest {

    Asteroid a;

    @Before
    public void before() {
    }


    @Test
    public void testAsteroidRotationVel() {
        a = new Asteroid();
        if (a.getRotationVel() < 0 || a.getRotationVel() > 5) {
            assertTrue(false);
        }
    }

    @Test
    public void testAsteroidStartingPostion() {
        Physics p = new Physics(400, 200, 20);
        int i = 0;
        for (Asteroid ast : p.getAsteroids()) {
            
            if (ast.getY() != 0){
                if(ast.getY() != p.getHeight()){
                    assertTrue(false);
                }
            }
            if (ast.getX() > p.getWidth() || ast.getX() < 0) {
                assertTrue(false);
            }
        }

    }
}
