package raw;

import java.awt.Rectangle;

public class Laser extends VectorShape {
    
    public Laser(double x, double y, double dir){
        super();
        super.setX(x);
        super.setY(y);
        super.setMoveDir(dir);
        super.setFaceDir(dir);
    }

    public boolean collision(Asteroid a) {
        if (getBounds().intersects(a.getBounds())) {
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int) getX() - 1, (int) getY() - 1, 1, 1);
        return r;
    }
}
