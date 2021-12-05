import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private ArrayList<Tower> towers;
    private ArrayList<Monster> monsters;

    private ArrayList<Tower> decoratedTowers;
    private ArrayList<Tower> toRemoveTowers;
    private ArrayList<Monster> toRemoveMonsters;

    private int currentGold=25;
    private int currentLives=3;
    private int currentKills=0;
    private int currentWave=1;

    private static final Game _inst = new Game();
    public static Game getInstance() {
        return _inst;
    }

    //TODO

    public Game() {
        /**
         *  Create Empty Lists */
        monsters  = new ArrayList<Monster>();
        towers = new ArrayList<Tower>();
        decoratedTowers = new ArrayList<Tower>();
        toRemoveTowers = new ArrayList<Tower>();
        toRemoveMonsters =  new ArrayList<Monster>();
        //TODO
    }

    /**
     * using graphics call all entity paints
     * @param g
     */
    public void paint(Graphics g) {
        for (Monster monster: monsters) {
            monster.paint(g);
        }
        for (Tower tower: towers) {
            tower.paint(g);
        }
        //TODO
    }

    /**
     * call step for all monster and tower entities
     * if monster reaches the destination take one life and
     * update infopanel.
     * if towers kill count passes decorator limits
     * decorate them accordingly.
     */
    public void step() {
        for (Monster monster: monsters) {
            monster.step();
            /**
             * if monster stopped it means monster reached the starting point
             * one life is taken, succesfull monster is removed from the list
             * for next wave and infopanel is updated
             */
            Vector2D direction = monster.getMonsterDirection();
            if (direction.equals(new Vector2D(0,0))){
                this.currentLives--;
                Display.getInstance().getInfoPanel().setCurrentLivesLabel(String.valueOf(this.currentLives));
                toRemoveMonsters.add(monster);
            }
        }
        /**
         * removes the succesfull monsters from the list
         */
        for (int i = 0 ; i < toRemoveMonsters.size(); i++){
            monsters.remove(toRemoveMonsters.get(i));
        }

        /**
         *  check kill count of every tower if done decorate it
         *
          */
        for ( int i = 0; i < towers.size() ; i++)
        {
            if(towers.get(i) !=null) {
                towers.get(i).step();
                if (towers.get(i).getKillCount() == 10   ){
                    Tower decoratedTower = new TowerDecoratorGrade1(towers.get(i));
                    toRemoveTowers.add(towers.get(i));
                    decoratedTowers.add(decoratedTower);
                }
                else  if( towers.get(i).getKillCount() == 25){
                    Tower decoratedTower = new TowerDecoratorGrade2(towers.get(i));
                    toRemoveTowers.add(towers.get(i));
                    decoratedTowers.add(decoratedTower);
                }
                else  if( towers.get(i).getKillCount() == 50 ){
                    Tower decoratedTower = new TowerDecoratorGrade3(towers.get(i));
                    toRemoveTowers.add(towers.get(i));
                    decoratedTowers.add(decoratedTower);
                }
            }
        }
        for (int i = 0 ; i < toRemoveTowers.size(); i++){
            towers.remove(toRemoveTowers.get(i));
            towers.add(decoratedTowers.get(i));
        }
        toRemoveTowers = new ArrayList<Tower>();
        decoratedTowers = new ArrayList<Tower>();

        Display.getInstance().getGamePanel().repaint();
    }

    public static void startGame() {
        Display.getInstance().setVisible(true);
        /**
         * Initial wave of the game
         */
        Game.getInstance().startNewWave();

        new Timer(5, actionEvent -> {
            if( Game.getInstance().monsters.isEmpty() ){
                /**
                 * If no nore live is left
                 * Game is over!Exitted.
                 */
                if(Game.getInstance().currentLives < 1){
                    System.exit(0);
                }
                Game.getInstance().incrementWave();
                Display.getInstance().getInfoPanel().setCurrentWaveLabel( String.valueOf(Game.getInstance().currentWave));
                Game.getInstance().startNewWave();
            }
            Game.getInstance().step();

        }).start();
    }

    /**
     * increments currentWave
     */
    private void incrementWave(){
        this.currentWave++;
    }

    /**
     * it starts the newWave by adding new monsters to
     * the monster list depending on wave number
     */
    private void startNewWave(){
        for(int i=0 ; i < currentWave ; i++){
            IMonsterStrategy newMonsterStrategy= generateRandomStrategy();
            Monster newMonster = new Monster(currentWave,newMonsterStrategy);
            monsters.add(newMonster);
        }
    }

    /**
     * it is used to generate a random MonsterStrategy
     * Circular or ZigZag
     * @return IMonsterStrategy
     */
    private IMonsterStrategy generateRandomStrategy(){
        Random rand = new Random();
        int randStrategy = rand.nextInt(2);
        if (randStrategy == 0){
            return new MonsterCircularStrategy();
        }
        else{
            return new MonsterZigZagStrategy();
        }
    }


    /**
     * tower makes attack possible by using this function,
     * it returns true or false depending the closest monster is killed
     * or not. If a monster is killed it is removed from
     * monsters list and gold is increased. Since gold is changed
     * infopanel is updated
     * @param towerCenterLocation
     * @param towerType
     * @param range
     * @param damage
     * @return boolean
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

        if (closestMonster != null){
            /*  attack to monster */
            if( closestDistance <= range ){
                double remainingHealth = closestMonster.getAttacked(damage,towerType);
                if (remainingHealth <= 0 ){
                    /**
                     * kill monster increase gold and kill count
                     */
                    this.currentGold += closestMonster.getMonsterKillReward();
                    Display.getInstance().getInfoPanel().setCurrentGoldLabel(String.valueOf(this.currentGold));
                    this.monsters.remove(closestMonster );
                    this.currentKills++;
                    Display.getInstance().getInfoPanel().setCurrentKillsLabel(String.valueOf(this.currentKills));
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * called from display game panel when there is a valid click and keyboard input
     * it calls necessary factory depending keyboardInput,
     * if current gold is enough it decreases currentGold and update infopanel
     * and adds tower to tower List
     * @param mouseClickedTowerPosition
     * @param keyboardInput
     */
    public void createTowerFactory(Vector2D mouseClickedTowerPosition,char keyboardInput){
        if ( keyboardInput == 'r'){
            ITowerFactory towerRegularFactory = new TowerRegularFactory();
            Tower regularTower = towerRegularFactory.createTower(mouseClickedTowerPosition);
            if (regularTower.getTowerCost() <= this.currentGold ){
                towers.add(regularTower);
                this.currentGold -= regularTower.getTowerCost();
                Display.getInstance().getInfoPanel().setCurrentGoldLabel(String.valueOf(this.currentGold));
            }
        }
        else if( keyboardInput == 'p'){
            ITowerFactory towerPoisonFactory = new TowerPoisonFactory();
            Tower poisonTower = towerPoisonFactory.createTower(mouseClickedTowerPosition);
            if (poisonTower.getTowerCost() <= this.currentGold ){
                towers.add(poisonTower);
                this.currentGold -= poisonTower.getTowerCost();
                Display.getInstance().getInfoPanel().setCurrentGoldLabel(String.valueOf(this.currentGold));
            }
        }
        else if(keyboardInput == 'i'){
            ITowerFactory towerIceFactory = new TowerIceFactory();
            Tower iceTower = towerIceFactory.createTower(mouseClickedTowerPosition);
            if (iceTower.getTowerCost() <= this.currentGold ){
                towers.add(iceTower);
                this.currentGold -= iceTower.getTowerCost();
                Display.getInstance().getInfoPanel().setCurrentGoldLabel(String.valueOf(this.currentGold));
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}

/**
 * Tower type enumaration to be used in attack
 */
enum TowerType{
    Regular,Ice,Poison
}