package asteroids.sovelluslogiikka;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Parameters and methods of a single Asteroid
 */
public class Asteroid extends VectorShape {

    /**
     *The x-coordinates for the outline of an asteroid.
     */
    private int[] astx = {-20, -13, 0, 20, 22, 20, 12, 2, -10, -22, -16};
    /**
     * The y-coordinates for the outline of an asteroid.
     */
    private int[] asty = {20, 23, 17, 20, 16, -20, -22, -14, -17, -20, -5};
    /**
     * A boolean defining which way the asteroid rotates. True for Anticlockwise
     * and false for clockwise.
     */
    private boolean rotationDir;
    /**
     * The number between 0 and 360 defining the asteroids position of rotation
     * used when drawing the asteroid.
     */
    private int rotationPosition;
    private Random random = new Random();

    /**
     * The constructor creates an Asteroid with a random rotation
     * direction and position, a width nd height of 40, and sets the
     * shape according to the points defined in astx and asty.
     */
    public Asteroid() {
        super();
        super.setShape(new Polygon(astx, asty, astx.length));
        this.rotationDir = random.nextBoolean();
        this.rotationPosition = random.nextInt(359);
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

    /**
     * Either adds or subtracts one from rotationPosition depending on
     * rotationDir. Wraps the number around, if it exceeds 359 or goes below 0.
     *
     * @return rotationPosition
     */
    public double calculateRotationPosition() {
        if (rotationDir) {
            rotationPosition++;
        } else {
            rotationPosition--;
        }

        if (rotationPosition > 359) {
            rotationPosition = 0;
        } else if (rotationPosition < 0) {
            rotationPosition = 359;
        }

        return Math.toRadians(rotationPosition);
    }

    public boolean getRotationDir() {
        return rotationDir;
    }

    public void setRotationDir(boolean rotationDir) {
        this.rotationDir = rotationDir;
    }

    public int getRotationPosition() {
        return rotationPosition;
    }

    public void setRotationPosition(int rotationPosition) {
        this.rotationPosition = rotationPosition;
    }
}
