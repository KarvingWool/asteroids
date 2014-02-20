package asteroids.asteroids;

import kayttoliittyma.Menu;

public class App {

    public static void main(String[] args) {




        Game g = new Game();
        Menu m = new Menu(g);


        m.run();

               while (true) {
                  try {
                     Thread.sleep(500);
                 } catch (Exception e) {
                  }
                  if (!m.isActive()) {
                     g.setGameSpeed(m.getSpeed());
                      g.setAsteroidAmount(m.getAsteroidAmount());
                      g.run();
                      break;
                  }
              }




    }
}
