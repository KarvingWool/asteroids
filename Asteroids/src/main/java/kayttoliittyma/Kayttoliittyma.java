package kayttoliittyma;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sovelluslogiikka.*;

/**
 * Handles graphics and the Rendering of the canvas.
 */
public class Kayttoliittyma {

    private AffineTransform identity = new AffineTransform();
    private JFrame frame;
    private Physics p;
    private ButtonResponse bResponse;
    private Random rand = new Random();
    private Canvas canvas;
    private BufferStrategy buffer;
    private BufferedImage bi;
    private GraphicsEnvironment ge;
    private GraphicsDevice gd;
    private GraphicsConfiguration gc;
    private Graphics graphics = null;
    private Graphics2D g2d = null;
    private Color background = Color.BLACK;
    private int fps;
    private int frames;
    private long totalTime;
    private long curTime;
    private long lastTime;

    public Kayttoliittyma(Physics p) {
        this.p = p;
        bResponse = new ButtonResponse(p);
    }

    /**
     * Sets up the JFrame and the canvas which will be drawn on.
     */
    public void setup() {
        frame = new JFrame("Asteroids");
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(p.getWidth(), p.getHeight());

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        gc = gd.getDefaultConfiguration();

        bi = gc.createCompatibleImage(p.getWidth(), p.getHeight());

        graphics = null;
        g2d = null;
        background = Color.BLACK;

        fps = 0;
        frames = 0;
        totalTime = 0;
        curTime = System.currentTimeMillis();
        lastTime = curTime;
        frame.addKeyListener(bResponse);
    }

    /**
     * Renders the JFrame with canvas once, with Asteroids, lasers and the ship
     * all implemented.
     */
    public void render() {

        try {
            lastTime = curTime;
            curTime = System.currentTimeMillis();
            totalTime += curTime - lastTime;
            if (totalTime > 1000) {
                totalTime -= 1000;
                fps = frames;
                frames = 0;
            }
            frames++;

            g2d = bi.createGraphics();
            g2d.setColor(background);
            g2d.fillRect(0, 0, p.getWidth(), p.getHeight());

            g2d.setColor(Color.CYAN);
            g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
            g2d.drawString(String.format("FPS: %s", fps), 20, 20);

            drawLasers();
            drawAsteroids();
            drawShip();

            graphics = buffer.getDrawGraphics();
            graphics.drawImage(bi, 0, 0, null);

            if (!buffer.contentsLost()) {
                buffer.show();
            }
            Thread.yield();

        } finally {

            if (graphics != null) {
                graphics.dispose();
            }
            if (g2d != null) {
                g2d.dispose();
            }
        }

    }

    /**
     * Draws the ship.
     */
    public void drawShip() {
        g2d.setTransform(identity);
        g2d.translate(p.getShip().getX(), p.getShip().getY());
        g2d.rotate(Math.toRadians(p.getShip().getFaceDir()+180));
        g2d.setColor(Color.ORANGE);
        g2d.fill(p.getShip().getShape());
    }

    /**
     * Draws all the asteroids.
     */
    public void drawAsteroids() {

        for (Asteroid a : p.getAsteroids()) {
            g2d.setTransform(identity);
            g2d.translate(a.getX(), a.getY());
            g2d.setColor(Color.DARK_GRAY);
            g2d.fill(a.getShape());
        }
    }

    /**
     * Draws all the lasers.
     */
    public void drawLasers() {
        for (Laser l : p.getLasers()) {
            g2d.setTransform(identity);
            g2d.translate(l.getX(), l.getY());
            g2d.setColor(Color.MAGENTA);
            g2d.fill(l.getShape());
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
