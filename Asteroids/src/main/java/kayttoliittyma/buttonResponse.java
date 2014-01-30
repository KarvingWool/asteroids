
package kayttoliittyma;

import asteroids.asteroids.Physics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class buttonResponse implements KeyListener{
    Physics p;
    public buttonResponse(Physics p){
        this.p = p;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            p.accelerate(p.getShip());
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            p.decelerate(p.getShip());
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            p.getShip().turnLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            p.getShip().turnRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            p.shoot();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
