import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private ArrayList<Entity> towers;
    private ArrayList<Entity> monsters;

    /* taken from InfoPanel class*/
    private int currentGold=25;
    private int currentLives=3;
    private int currentKills=0;
    private int currentWave=1;

    private int stepCounter=0;

    private static final Game _inst = new Game();
    public static Game getInstance() {
        return _inst;
    }
   
    //TODO

    public Game() {
        /*  Create Empty Lists */
        monsters  = new ArrayList<Entity>();
        towers = new ArrayList<Entity>();

        //TODO

    }

    public void paint(Graphics g) {
        for (Entity monster: monsters) {
            monster.paint(g);
        }
        for (Entity tower: towers) {
            tower.paint(g);
        }
        //TODO
    }

    public void step() {
        for (Entity monster: monsters) {
            monster.step();
        }
        for (Entity tower: towers) {
            tower.step();
        }

        Display.getInstance().getGamePanel().repaint();

        //TODO
    }

    //You can make changes
    public static void startGame() {
        Display.getInstance().setVisible(true);
        //Optional additions


        new Timer(20, actionEvent -> {
            Game.getInstance().step();

            //Optional additions
            
        }).start();
    }

    public void addTower(Entity tower){ this.getTowers().add(tower);}
    public void addMonster(Entity monster){ this.getMonsters().add(monster);}

    public void attackToMonsterIfRange(Vector2D towerCenterLocation,double range,double damage){
        double closestDistance = Double.max(Commons.GamePanelWidth,Commons.GameHeight);
        Monster closestMonster=null;
        for (Entity monsterE: monsters){
            Monster monster = (Monster)monsterE;
            Vector2D monsterCenterPosition = monster.getCurrentCenterPosition();
            double distance = towerCenterLocation.distance(monsterCenterPosition);
            if (distance < closestDistance ){
                closestDistance = distance;
                closestMonster = monster;
            }
        }
        if(closestMonster == null)
            System.out.println("Game attackToMonsterIfRange failed!!!");
        else{
            /*  attack to monster */
            if( closestDistance <= range ){
                double remainingHealth = closestMonster.getAttacked(damage);
                if (remainingHealth <= 0 ){
                    this.monsters.remove((Entity) closestMonster );
                }
            }
        }
    }

    public ArrayList<Entity> getTowers(){return this.towers;}
    public ArrayList<Entity> getMonsters(){return this.monsters;}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}
