package sovelluslogiikka;

import java.awt.Rectangle;

public class Ship extends VectorShape {

        public Ship() {
        super();
        super.setHeight(16);
        super.setWidth(16);
    }

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
        r = new Rectangle((int) getX() - (super.getWidth() / 2), (int) getY() - (super.getHeight() / 2), super.getWidth(), super.getHeight());
        return r;
    }
}
