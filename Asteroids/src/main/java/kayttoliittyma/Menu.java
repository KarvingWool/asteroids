package kayttoliittyma;

import asteroids.asteroids.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import sovelluslogiikka.Physics;

/**
 * Manages the Statup Menu.
 *
 * @author JJV
 */
public class Menu implements Runnable {

    private JFrame frame;
    private Game g;

    public Menu(Game g) {
        this.g = g;
    }

    @Override
    public void run() {
        frame = new JFrame("The Effect");
        frame.setPreferredSize(new Dimension(200, 100));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BorderLayout layout = new BorderLayout();
        container.setLayout(layout);

        JButton button = new JButton("Click!");

        MouseResponse mr = new MouseResponse(g, frame);

        button.addActionListener(mr);

        container.add(button);
    }
}
