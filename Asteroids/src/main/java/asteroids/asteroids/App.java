package asteroids.asteroids;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.Menu;
import sovelluslogiikka.*;

public class App {

    public static void main(String[] args) {

        //    Scanner lukija = new Scanner(System.in);
        //     int asteroids;
        //   System.out.println("Select Asteroid amount(number):");
        //    System.out.println("10, easy");
        //    System.out.println("20, normal");
        //     System.out.println("30, hard");
        //      System.out.println("50, insane");
        //      asteroids = Integer.parseInt(lukija.nextLine());

        Physics p = new Physics(800, 500, 30);
        Kayttoliittyma k = new Kayttoliittyma(p);
        Game g = new Game(p, k);
        Menu m = new Menu(g);
        g.run();
        m.run();

        

    }
}
