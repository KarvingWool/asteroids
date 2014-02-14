package kayttoliittyma;

/** A KeyListener class, which defines functions for certain
 * KeyEvents.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sovelluslogiikka.Physics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonResponse implements KeyListener{
    Physics p;
    public ButtonResponse(Physics p){
        this.p = p;
    }
    
    /** Gives the appropriate functions to the keys
     * up, down, left, right and space.
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            p.accelerate(p.getShip());
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            p.decelerate(p.getShip());
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            p.getShip().turnAntiClockwise();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            p.getShip().turnClockwise();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            p.shoot();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
  
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
