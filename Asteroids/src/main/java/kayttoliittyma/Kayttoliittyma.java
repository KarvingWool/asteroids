package kayttoliittyma;

import java.awt.*;
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
        BufferStrategy buffer = canvas.getBufferStrategy();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        BufferedImage bi = gc.createCompatibleImage(p.getWidth(), p.getHeight());

        Graphics graphics = null;
        Graphics2D g2d = null;
        Color background = Color.BLACK;

        int fps = 0;
        int frames = 0;
        long totalTime = 0;
        long curTime = System.currentTimeMillis();
        long lastTime = curTime;
    }

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

            g2d.fillRect(0, 0, 399, 199);
            
            g2d.setColor(Color.DARK_GRAY);

            for (Asteroid a : p.getAsteroids()) {
                g2d.fillOval((int)a.getX(), (int)a.getY(), a.getWidth(), a.getHeight());
            }
            
            g2d.setColor(Color.CYAN);
            for(Laser l : p.getLasers()){
                g2d.fillRect((int)l.getX(), (int)l.getY(), l.getWidth(), l.getHeight());
            }
            
            g2d.setColor(Color.GREEN);
            g2d.fillRect((int)p.getShip().getX(), (int)p.getShip().getY(), p.getShip().getWidth(), p.getShip().getHeight());
            
            g2d.setFont(new Font("Courier New", Font.PLAIN, 12));

            g2d.drawString(String.format("FPS: %s", fps), 20, 20);

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

    public JFrame getFrame() {
        return frame;
    }

}
