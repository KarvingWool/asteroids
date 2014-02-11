package sovelluslogiikka;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Parameters and methods of a single Asteroid
 */
public class Asteroid extends VectorShape {

    private int[] astx = {-20, -13, 0, 20, 22, 20, 12, 2, -10, -22, -16};
    private int[] asty = {20, 23, 17, 20, 16, -20, -22, -14, -17, -20, -5};
    private int rotationVel;
    private Random random = new Random();

    /*The constructor creates an Asteroid with a random rotation
     *velocity, and a set width and height.
     */
    public Asteroid() {
        super();
        super.setShape(new Polygon(astx, asty, astx.length));
        this.rotationVel = random.nextInt(5);
        super.setHeight(40);
        super.setWidth(40);
    }

    /**
     * @return The rectangle encasing the Asteroid.
     */
    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - (super.getWidth() / 2), (int) getY() - (super.getHeight() / 2), super.getWidth(), super.getHeight());
        return r;
    }

    public int getRotationVel() {
        return rotationVel;
    }

    public void setRotationVel(int rotationVel) {
        this.rotationVel = rotationVel;
    }
}
