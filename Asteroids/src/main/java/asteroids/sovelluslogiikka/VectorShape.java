package asteroids.sovelluslogiikka;

import java.awt.Shape;

/**
 * Parameters and methods of the VectorShape class. Its contents are extended
 * into the Asteroid, Laser and Ship classes.
 */
public class VectorShape {

    /**
     * The counter used for determining the amount of times a laser has moved.
     **/
    private int counter = 0;
    /**
     * The x and y coordinates of the shapes position.
     */
    private double x, y;
    /**
     * The vectors of the shape on the X and Y axis.
     */
    private double velX, velY;
    /**
     * The facing direction of the shape in degrees.
     */
    private double faceDir;
    /**
     * True if the shape is classified as alive, false otherwise.
     */
    private boolean alive;
    /**
     * The width of the shape.
     */
    private int width;
    /*
     * The height of the shape.
     */
    private int height;
    /**
     * The shape of the shape.
     */
    private Shape shape;

    /**
     * VelX and Y are set to 0, the facing direction to 90, and alive as true.
     */
    public VectorShape() {
        setVelX(0.0);
        setVelY(0.0);
        setFaceDir(90.0);
        setAlive(true);
    }

    /**
     * Sets the facing direction in faceDir. Method translates all numbers to
     * between 0-359 degrees.
     *
     * @param faceDir
     */
    public void setFaceDir(double faceDir) {
        if (faceDir > 359) {
            while (faceDir > 359) {
                faceDir = faceDir - 360;
            }
        }
        if (faceDir < 0) {
            while (faceDir < 0) {
                faceDir = faceDir + 360;
            }
        }
        this.faceDir = faceDir;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setVelX(double velocityX) {
        this.velX = velocityX;
    }

    public void setVelY(double velocityY) {
        this.velY = velocityY;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Shape getShape() {
        return shape;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public double getFaceDir() {
        return faceDir;
    }

    public boolean getAlive() {
        return alive;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
