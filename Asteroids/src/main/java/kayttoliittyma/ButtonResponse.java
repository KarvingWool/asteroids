package kayttoliittyma;

/**
 * A KeyListener class, which defines functions for certain KeyEvents within the game.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sovelluslogiikka.Physics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonResponse implements KeyListener {

    private Gui gui;
    private long lastPressed = 0;

    public ButtonResponse(Gui gui) {
        this.gui = gui;
    }

    /**
     * Sets the corresponding booleans to true when the keys up, down, left or right
     * is pressed. With space, if the time between this press and the previous shoot is
     * long enough, the shoot method from physics is called.
     * space.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            gui.getPhysics().getShip().setAccelerating(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            gui.getPhysics().getShip().setDecelerating(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gui.getPhysics().getShip().setTurningAntiClockwise(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gui.getPhysics().getShip().setTurningClockwise(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (System.currentTimeMillis() - lastPressed > 250) {
                gui.getPhysics().shoot();
                lastPressed = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * With the release of a directional key, sets the corresponding boolean to false
     * which was made to true by the pressing of the key.
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            gui.getPhysics().getShip().setAccelerating(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            gui.getPhysics().getShip().setDecelerating(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gui.getPhysics().getShip().setTurningAntiClockwise(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gui.getPhysics().getShip().setTurningClockwise(false);
        }
    }
}
