package raw;

import java.awt.Rectangle;
import java.util.Random;

public class Asteroid extends VectorShape {

    private int rotationVel;
    private Random random = new Random();

    public Asteroid() {
        super();
        this.rotationVel = random.nextInt(5);
        super.setFaceDir(random.nextInt(359));
        super.setX(random.nextInt(400)-200);
        super.setSpeed(random.nextInt(3)+3);
        if(random.nextBoolean()){
            super.setY(100);
        } else {
            super.setY(-100);
        }
    }
    

    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - 15, (int) getY() - 15, 15, 15);
        return r;
    }

    public int getRotationVel() {
        return rotationVel;
    }

    public void setRotationVel(int rotationVel) {
        this.rotationVel = rotationVel;
    }
}
