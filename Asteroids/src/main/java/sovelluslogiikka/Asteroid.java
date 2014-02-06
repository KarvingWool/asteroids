package sovelluslogiikka;

import java.awt.Rectangle;
import java.util.Random;

public class Asteroid extends VectorShape {

    private int rotationVel;
    private Random random = new Random();

    public Asteroid() {
        super();
        this.rotationVel = random.nextInt(5);
        super.setHeight(16);
        super.setWidth(16);
    }
    

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
