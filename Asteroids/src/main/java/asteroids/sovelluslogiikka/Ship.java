package asteroids.sovelluslogiikka;

import java.awt.Rectangle;
import java.awt.Polygon;

/**
 * Attributes and methods of a single ship.
 */
public class Ship extends VectorShape {

    /**
     * The x-coordinates for the outline of the ship.
     */
    private int shipx[] = {-7, 7, 0};
    /**
     * The y-coordinates for the outline of the ship.
     */
    private int shipy[] = {-7, -7, 14};
    /**
     * States whether the ship should turn anticlockwise.
     */
    private boolean turningAntiClockwise = false;
    /**
     * States whether the ship should turn clockwise.
     */
    private boolean turningClockwise = false;
    /**
     * States whether the ship should accelerate.
     */
    private boolean accelerating = false;
    /**
     * States whether the ship should decelerate.
     */
    private boolean decelerating = false;
/**
 * Creates a ship with the width and height of 8, and the outline of
 * the points in shipx and shpiy.
 */
    public Ship() {
        super();
        super.setShape(new Polygon(shipx, shipy, shipx.length));
        super.setHeight(14);
        super.setWidth(14);
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

    public boolean isTurningAntiClockwise() {
        return turningAntiClockwise;
    }

    public boolean isTurningClockwise() {
        return turningClockwise;
    }

    public boolean isAccelerating() {
        return accelerating;
    }

    public boolean isDecelerating() {
        return decelerating;
    }

    public void setTurningAntiClockwise(boolean turningAntiClockwise) {
        this.turningAntiClockwise = turningAntiClockwise;
    }

    public void setTurningClockwise(boolean turningClockwise) {
        this.turningClockwise = turningClockwise;
    }

    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

    public void setDecelerating(boolean decelerating) {
        this.decelerating = decelerating;
    }
}
