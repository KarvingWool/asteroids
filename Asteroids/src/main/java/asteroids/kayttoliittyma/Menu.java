package asteroids.kayttoliittyma;

import asteroids.asteroids.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.WindowConstants;
import asteroids.sovelluslogiikka.Physics;

/**
 * Manages the Startup Menu.
 *
 * @author JJV
 */
public class Menu implements Runnable{

    private JFrame frame;
    private int asteroidAmount = 20;
    private double speed = 1;
    private Game g;
    private boolean active = true;

    public Menu(Game g) {
        this.g = g;
    }

    /**
     * Creates the JFrame, runs creatComponents() and packs the frame to be
     * readily shown.
     */
    @Override
    public void run() {
        frame = new JFrame("Asteroids Settings");
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates the Menu JFrames buttons, links the correct responses to them and
     * finally adds them to the frames content
     * @param container 
     */
    private void createComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("Choose asteroid starting amount:"));

        JRadioButton ast10 = new JRadioButton("10");
        JRadioButton ast20 = new JRadioButton("20");
        JRadioButton ast30 = new JRadioButton("30");
        JRadioButton spd1 = new JRadioButton("x1.0");
        JRadioButton spd2 = new JRadioButton("x1.2");
        JRadioButton spd3 = new JRadioButton("x1.5");
        JRadioButton spd4 = new JRadioButton("x2.0");
        JLabel spdLabel = new JLabel("Choose asteroid speed multiplier:");
        
        JButton ready = new JButton("Ready");
        ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                setActive(false);
            }
        });

        ast10.addActionListener(new MenuResponse(this));
        ast20.addActionListener(new MenuResponse(this));
        ast30.addActionListener(new MenuResponse(this));
        spd1.addActionListener(new MenuResponse(this));
        spd2.addActionListener(new MenuResponse(this));
        spd3.addActionListener(new MenuResponse(this));
        spd4.addActionListener(new MenuResponse(this));

        ButtonGroup bGroupAst = new ButtonGroup();
        bGroupAst.add(ast10);
        bGroupAst.add(ast20);
        bGroupAst.add(ast30);
        
        ButtonGroup bGroupSpd = new ButtonGroup();
        bGroupSpd.add(spd1);
        bGroupSpd.add(spd2);
        bGroupSpd.add(spd3);
        bGroupSpd.add(spd4);

        container.add(ast10);
        container.add(ast20);
        container.add(ast30);
        container.add(spdLabel);
        container.add(spd1);
        container.add(spd2);
        container.add(spd3);
        container.add(spd4);
        container.add(ready);

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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public void setActive(boolean b){
        active = b;
    }
    public boolean isActive(){
        return active;
    }

}
