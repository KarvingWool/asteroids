package kayttoliittyma;

import grafiikka.DrawingBoard;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sovelluslogiikka.Physics;

public class Kayttoliittyma implements Runnable{
   
    private JFrame frame;
    private Physics p;
    private ButtonResponse bResponse;
    private Graphics g;
    private DrawingBoard d;

    public Kayttoliittyma(Physics p){
        this.p = p;
        bResponse = new ButtonResponse(p);
    }

    @Override
    public void run() {
        frame = new JFrame("Asteroids");
        int width = p.getWidth();
        int height = p.getHeight();
        
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
       createComponents(frame.getContentPane());
       
       frame.pack();
       frame.setVisible(true);
    }
    
    public void createComponents(Container container){
        d = new DrawingBoard(p);
        container.add(d);
        
        frame.addKeyListener(new ButtonResponse(p));
    }

    public JFrame getFrame() {
        return frame;
    }

    public DrawingBoard getDrawingBoard() {
        return d;
    }
    
}
