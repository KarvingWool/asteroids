package sovelluslogiikka;

import java.awt.Rectangle;

/**
 * Parameters and methods of a single Laser.
 */
public class Laser extends VectorShape {
    
/** Creates a laser at the coordinate x,y, with
 *  the facing direction set to dir.
 * @param x
 * @param y
 * @param dir 
 */
    public Laser(double x, double y, double dir) {
        super();
        super.setX(x);
        super.setY(y);
        super.setFaceDir(dir);
        super.setShape(new Rectangle(0, 0, 1, 1));
        super.setWidth(1);
        super.setHeight(1);

    }

    /**
     * Tests whether the rectangle encasing the Laser intersects with the given
     * Asteroid
     * @param a to be tested
     * @return Whether the collision happens
     */
    public boolean collision(Asteroid a) {
        if (getBounds().intersects(a.getBounds())) {
            return true;
        }
        return false;
    }

    /**
     * @return The rectangle encasing the laser.
     */
    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - (super.getWidth() / 2), (int) getY() - (super.getHeight() / 2), super.getWidth(), super.getHeight());
        return r;
    }
    
}
