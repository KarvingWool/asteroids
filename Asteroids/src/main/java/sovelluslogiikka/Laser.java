package sovelluslogiikka;

import java.awt.Rectangle;

public class Laser extends VectorShape {

    public Laser(double x, double y, double dir) {
        super();
        super.setX(x);
        super.setY(y);
        super.setFaceDir(dir);
        super.setWidth(1);
        super.setHeight(1);

    }

    public boolean collision(Asteroid a) {
        if (getBounds().intersects(a.getBounds())) {
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - (super.getWidth() / 2), (int) getY() - (super.getHeight() / 2), super.getWidth(), super.getHeight());
        return r;
    }
}
