package asteroids.kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ActionLister class used by the Menu object.
 * @author JJV
 */
public class MenuResponse implements ActionListener{
    private Menu m;
    
    public MenuResponse(Menu m){
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("10".equals(e.getActionCommand())){
            m.setAsteroidAmount(10);
        }
        if("20".equals(e.getActionCommand())){
            m.setAsteroidAmount(20);
        }
        if("30".equals(e.getActionCommand())){
            m.setAsteroidAmount(30);
        }
        if("x1.0".equals(e.getActionCommand())){
            m.setSpeed(1);
        }
        if("x1.2".equals(e.getActionCommand())){
            m.setSpeed(1.2);
        }
        if("x1.5".equals(e.getActionCommand())){
            m.setSpeed(1.5);
        }
        if("x2.0".equals(e.getActionCommand())){
            m.setSpeed(2);
        }
        
    }

    
}
