import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private ArrayList<Entity> towers;
    private ArrayList<Entity> monsters;

    /* taken from InfoPanel class*/
    private int currentGoldLabel;
    private int currentLivesLabel;
    private int currentKillsLabel;
    private int currentWaveLabel;

    private static final Game _inst = new Game();
    public static Game getInstance() {
        return _inst;
    }
   
    //TODO

    public Game() {
        currentGoldLabel= 25;
        currentKillsLabel = 0;
        currentLivesLabel = 3;
        currentWaveLabel = 1 ;

        //TODO
        //TODO
    }

    public void paint(Graphics g) {
        //System.out.println("Game paint called");
        //Display.getInstance().getGamePanel().repaint();
        //Display.getInstance().getInfoPanel().repaint();
        //TODO
    }

    public void step() {
        //System.out.println("Game step called");
        //Display.getInstance().getGamePanel().repaint();
        //Display.getInstance().getInfoPanel().repaint();


        //TODO
    }

    //You can make changes
    public static void startGame() {
        Display.getInstance().setVisible(true);
        //Optional additions


        new Timer(5, actionEvent -> {
            Game.getInstance().step();

            //Optional additions
            
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}
