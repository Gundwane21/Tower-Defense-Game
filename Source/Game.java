import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private ArrayList<Tower> towers;
    private ArrayList<Monster> monsters;

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
        monsters  = new ArrayList<Monster>();
        towers = new ArrayList<Tower>();

        //TODO

    }

    public void paint(Graphics g) {
        for (Monster monster: monsters) {
            monster.paint(g);
        }
        for (Tower tower: towers) {
            tower.paint(g);
        }
        //TODO
    }

    public void step() {
        for (Monster monster: monsters) {
            monster.step();
        }
        for ( int i=0 ; i < towers.size() ; i++) {
            towers.get(i).step();
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

    public void decorateTowerGrade1(Tower tower){
        Tower decoratedTower = new TowerDecoratorGrade1(tower);
        //this.towers.remove(tower);
        towers.add(decoratedTower);
    }

    /**
     * tower makes attack possible by using this function,
     * it returns true or false depending the closest monster is killed
     * @param towerCenterLocation
     * @param towerType
     * @param range
     * @param damage
     * @return
     */
    public boolean attackToMonsterIfRange(Vector2D towerCenterLocation, TowerType towerType,double range,double damage){
        double closestDistance = Double.max(Commons.GamePanelWidth,Commons.GameHeight);
        Monster closestMonster=null;
        for (Monster monster: monsters){
            //Monster monster = (Monster)monsterE;
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
                double remainingHealth = closestMonster.getAttacked(damage,towerType);
                if (remainingHealth <= 0 ){
                    //this.monsters.remove((Entity) closestMonster );
                    this.monsters.remove(closestMonster );
                    return true;
                }
            }
        }
        return false;
    }

    public void addTower(Tower tower){ this.getTowers().add(tower);}
    public void addMonster(Monster monster){ this.getMonsters().add(monster);}

    public ArrayList<Tower> getTowers(){return this.towers;}
    public ArrayList<Monster> getMonsters(){return this.monsters;}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}

enum TowerType{
    Regular,Ice,Poison
}