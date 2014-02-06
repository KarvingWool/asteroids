package sovelluslogiikka;

import java.awt.Rectangle;

/**
 * Attributes and methods of a single ship.
 */
public class Ship extends VectorShape {

    public Ship() {
        super();
        super.setHeight(16);
        super.setWidth(16);
    }

    /** Tests whether the rectangle encasing the ship
     * intersects with the given Asteroid
     * @param Asteroid to be tested
     * @return Whether the collision happens
    */
    public boolean collision(Asteroid a) {
        if (getBounds().intersects(a.getBounds())) {
            return true;
        }
        return false;
    }

    /**
     * Shifts the ships facing direction anticlockwise.
     */
    public void turnLeft() {
        if (super.getFaceDir() == 0) {
            super.setFaceDir(359);
        } else {
            super.setFaceDir(super.getFaceDir() - 1);
        }
    }

    /**
     * Shifts the ships facing direction clockwise.
     */
    public void turnRight() {
        if (super.getFaceDir() == 359) {
            super.setFaceDir(0);
        } else {
            super.setFaceDir(super.getFaceDir() + 1);
        }
    }

    /**
     * @return The rectangle encasing the Ship.
     */
    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - (super.getWidth() / 2), (int) getY() - (super.getHeight() / 2), super.getWidth(), super.getHeight());
        return r;
    }
}
