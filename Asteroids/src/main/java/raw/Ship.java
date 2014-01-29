package raw;

import java.awt.Rectangle;

public class Ship extends VectorShape {

    public boolean collision(Asteroid a) {

        if (getBounds().intersects(a.getBounds())) {
            return true;
        }
        return false;
    }

    public void turnLeft() {
        if (super.getFaceDir() == 0) {
            super.setFaceDir(359);
        } else {
            super.setFaceDir(super.getFaceDir() - 1);
        }
    }

    public void turnRight() {
        if (super.getFaceDir() == 359) {
            super.setFaceDir(0);
        } else {
            super.setFaceDir(super.getFaceDir() + 1);
        }
    }

    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - 6, (int) getY() - 6, 6, 6);
        return r;
    }
}
