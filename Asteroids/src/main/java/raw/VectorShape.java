package raw;

public class VectorShape {

    private double x, y;
    private double velX, velY;
    private double moveDir, faceDir;
    private boolean alive;

    public VectorShape() {
        setX(0.0);
        setY(0.0);
        setVelX(0.0);
        setVelY(0.0);
        setMoveDir(0.0);
        setFaceDir(0.0);
        setAlive(true);
    }
    
    

    
    public void setVelX(double velocityX) {
        this.velX = velocityX;
    }

    public void setVelY(double velocityY) {
        this.velY = velocityY;
    }

    public void setMoveDir(double moveDir) {
        this.moveDir = moveDir;
    }

    public void setFaceDir(double faceDir) {
        this.faceDir = faceDir;
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

    
    
    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public double getMoveDir() {
        return moveDir;
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
