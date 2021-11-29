import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static final Game _inst = new Game();
    public static Game getInstance() {
        return _inst;
    }
   
    //TODO

    public Game() {
        //TODO
    }

    public void paint(Graphics g) {
        //TODO
    }

    public void step() {
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
