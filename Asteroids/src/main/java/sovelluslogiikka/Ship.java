package sovelluslogiikka;

import java.awt.Rectangle;
import java.awt.Polygon;

/**
 * Attributes and methods of a single ship.
 */
public class Ship extends VectorShape {

    private int shipx[] = {-6, 6, 0};
    private int shipy[] = {-6, -6, 12};

    public Ship() {
        super();
        super.setShape(new Polygon(shipx, shipy, shipx.length));
        super.setHeight(16);
        super.setWidth(16);
    }

    /**
     * Tests whether the rectangle encasing the ship intersects with the given
     * Asteroid
     *
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
    public void turnClockwise() {
        super.setFaceDir(super.getFaceDir() - 20);
    }

    /**
     * Shifts the ships facing direction clockwise.
     */
    public void turnAntiClockwise() {
        super.setFaceDir(super.getFaceDir() + 20);
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
