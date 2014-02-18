package scores;

import java.io.*;
import javax.swing.JOptionPane;
import sovelluslogiikka.Physics;

/**
 * This class handles the checking of the score made during the game,
 * and if that score exceeds the previous highscore, the score is
 * written onto the file as a new highscore.
 */
public class Highscore {

    private Physics p;
    private String highscore;

    public Highscore(Physics P) {
        this.p = P;
        highscore=getHighscore();
    }

    /**
     * This method checks whether the file highscore.dat exists, if it does not,
     * it is created.
     * It then checks if the score is higher than the previous highscore If it is,
     * the score along with the name is taken, and the old highscore replaced.
     */
    public void checkScore() {
        if (p.getScore() > Integer.parseInt(highscore.split(":")[1])) {
            String name = JOptionPane.showInputDialog("Highscore! Input name:");
            highscore = name+ ":" + p.getScore();
            File scoreFile = new File("highscore.dat");
            if(!scoreFile.exists()){
                try{
                scoreFile.createNewFile();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            FileWriter writer = null;
            BufferedWriter buffWriter = null;
            
            try{
                writer = new FileWriter(scoreFile);
                buffWriter = new BufferedWriter(writer);
                writer.write(highscore);
            }catch (Exception e) {
                
            }
            finally {
                try {
                    if(buffWriter!=null){
                        buffWriter.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } 
            }
        }
    }

    /**
     * This method fetches the highscore as a string from highscores.dat
     * @return highscore as a string
     */
    public String getHighscore() {
        FileReader reader = null;
        BufferedReader buffReader = null;

        try {
            reader = new FileReader("highscore.dat");
            buffReader = new BufferedReader(reader);
            return buffReader.readLine();
        } catch (Exception e) {
            return "0";
        } finally {
            try {
                if (buffReader != null) {
                    buffReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
