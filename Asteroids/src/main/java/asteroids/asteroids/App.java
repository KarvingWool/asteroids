package asteroids.asteroids;


import kayttoliittyma.Menu;

public class App {

    public static void main(String[] args) {
        
        
        

        Game g = new Game();
        Menu m = new Menu(g);
        m.run();
        
        System.out.println(m.getAsteroidAmount());
        
        
     //   g.run();

        

    }
}
