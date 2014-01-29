package raw;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsteroidTest {

    public AsteroidTest() {
    }

    @Test
    public void testAsteroidRotationVel() {
        Asteroid a = new Asteroid();

        if (a.getRotationVel() < 0 || a.getRotationVel() > 5) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testAsteroidStartingPostion(){
        ArrayList<Asteroid> a = new ArrayList<Asteroid>();
        for(int i=0;i<10;i++){
            a.add(new Asteroid());
        }

        int i=0;
        for(Asteroid ast : a){
            if(Math.abs(ast.getY())!=100){
                assertTrue(false);
            }
            if(ast.getX()>200||ast.getX()<-200){
                assertTrue(false);
            }
        }
        
        
    }
    
    @Test
    public void testAsteroidStartingSpeed(){
        for(int i=0;i<20;i++){
            Asteroid a = new Asteroid();
            
            if(a.getSpeed()<3||a.getSpeed()>6){
                assertTrue(false);
            }
        }
        
    }
}
