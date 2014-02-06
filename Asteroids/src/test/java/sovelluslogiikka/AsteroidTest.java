package sovelluslogiikka;

import sovelluslogiikka.Asteroid;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AsteroidTest {
    Asteroid a;
    
    @Before
    public void before(){
        a= new Asteroid();
    }
    
    public AsteroidTest() {
    }

    @Test
    public void testAsteroidRotationVel() {

        if (a.getRotationVel() < 0 || a.getRotationVel() > 5) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testAsteroidStartingPostion(){
        Physics p = new Physics(400, 200);

        int i=0;
        for(Asteroid ast : p.getAsteroids()){
            if(Math.abs(ast.getY())!=100){
                assertTrue(false);
            }
            if(ast.getX()>200||ast.getX()<-200){
                assertTrue(false);
            }
        }
        
        
    }
    
}
