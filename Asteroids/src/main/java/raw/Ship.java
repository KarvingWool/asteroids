package raw;

import java.awt.Rectangle;

public class Ship extends VectorShape {

    public boolean collision(Asteroid a) {

        if (getBounds().intersects(a.getBounds())) {
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - 6, (int) getY() - 6, 6, 6);
        return r;
    }
}
