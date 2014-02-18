package kayttoliittyma;

import asteroids.asteroids.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.WindowConstants;
import sovelluslogiikka.Physics;

/**
 * Manages the Statup Menu.
 *
 * @author JJV
 */
public class Menu {

    private JFrame frame;
    private int asteroidAmount = 20;
    private Game g;

    public Menu(Game g) {
        this.g = g;
    }

    public void run() {
        frame = new JFrame("Asteroids Settings");
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("Choose asteroid starting amount:"));

        JRadioButton ast10 = new JRadioButton("10");
        JRadioButton ast20 = new JRadioButton("20");
        JRadioButton ast30 = new JRadioButton("30");
        JButton ready = new JButton("Ready");
        ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                g.run();
            }
        });

        ast10.addActionListener(new MenuResponse(this, getFrame()));
        ast20.addActionListener(new MenuResponse(this, getFrame()));
        ast30.addActionListener(new MenuResponse(this, getFrame()));

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(ast10);
        bGroup.add(ast20);
        bGroup.add(ast30);

        container.add(ast10);
        container.add(ast20);
        container.add(ast30);
        container.add(ready);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setAsteroidAmount(int i) {
        this.asteroidAmount = i;
    }

    public int getAsteroidAmount() {
        return asteroidAmount;
    }

    public Game getG() {
        return g;
    }

}
