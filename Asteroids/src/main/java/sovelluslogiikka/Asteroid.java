package sovelluslogiikka;

import java.awt.Rectangle;
import java.util.Random;

/** Parameters and methods of a single Asteroid*/
public class Asteroid extends VectorShape {

    private int rotationVel;
    private Random random = new Random();

    /*The constructor creates an Asteroid with a random rotation
    *velocity, and a set width and height.
    */
    public Asteroid() {
        super();
        this.rotationVel = random.nextInt(5);
        super.setHeight(16);
        super.setWidth(16);
    }
    
    /** @return The rectangle encasing the Asteroid.
     */
    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - (super.getWidth()/2), (int) getY() - (super.getHeight()/2), super.getWidth(), super.getHeight());
        return r;
    }

    public int getRotationVel() {
        return rotationVel;
    }

    public void setRotationVel(int rotationVel) {
        this.rotationVel = rotationVel;
    }
}
