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
        /*  Create Empty Lists */
        monsters  = new ArrayList<Monster>();
        towers = new ArrayList<Tower>();
        decoratedTowers = new ArrayList<Tower>();
        toRemoveTowers = new ArrayList<Tower>();
        toRemoveMonsters =  new ArrayList<Monster>();
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
            /**
             * if monster stopped it means monster reached the starting point
             */
            Vector2D direction = monster.getMonsterDirection();
            if (direction.equals(new Vector2D(0,0))){
                this.currentLives--;
                Display.getInstance().getInfoPanel().setCurrentLivesLabel(String.valueOf(this.currentLives));
                toRemoveMonsters.add(monster);
            }
        }
        for (int i = 0 ; i < toRemoveMonsters.size(); i++){
            monsters.remove(toRemoveMonsters.get(i));
        }

        /* check kill count of every tower if done decorate it */
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
        //TODO
    }

    public static void startGame() {
        Display.getInstance().setVisible(true);
        //Initial wave
        Game.getInstance().startNewWave();

        new Timer(5, actionEvent -> {
            if( Game.getInstance().monsters.isEmpty() ){
                /**
                 * Game is over!
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


    private void incrementWave(){
        this.currentWave++;
    }
    private void startNewWave(){
        for(int i=0 ; i < currentWave ; i++){
            IMonsterStrategy newMonsterStrategy= generateRandomStrategy();
            Monster newMonster = new Monster(currentWave,newMonsterStrategy);
            monsters.add(newMonster);
        }
    }

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

    public void takePlayerLife(Monster monster){
        this.currentLives -- ;
        monsters.remove(monster);
    }

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

    public void addTower(Tower tower){ this.getTowers().add(tower);}
    public ArrayList<Tower> getTowers(){return this.towers;}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}

enum TowerType{
    Regular,Ice,Poison
}