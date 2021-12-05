import java.awt.*;
import java.util.Random;

public class Monster extends Entity {
    //TODO

    private int health;
    private double speed = 1 ;
    private final int reward=10;

    MonsterState monsterState;
    IMonsterStrategy monsterStrategy;

    Vector2D currentLeftPosition;
    Vector2D currentCenterPosition;
    Vector2D currentDirection;

    Monster(int waveCount,IMonsterStrategy monsterStrategy){
        this.health = 100 + waveCount*20 ;
        this.monsterStrategy =  monsterStrategy;
        this.currentLeftPosition = generateRandomStartPoint();
        this.currentCenterPosition = this.calculateCurrentCenter();
        /**
         * set monster initially to go up and its state to null
          */
        this.currentDirection= new Vector2D(1,-1);
        monsterState = new MonsterNullState(this);

        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }

    private Vector2D calculateCurrentCenter(){
        return new Vector2D(this.currentLeftPosition.getX()+ Commons.TowerZoneDivideLength/2 , this.currentLeftPosition.getY() + Commons.TowerZoneDivideLength/2  );
    }

//    public Vector2D calculateCurrentLeft(){
//        return new Vector2D(this.currentCenterPosition.getX()- Commons.TowerZoneDivideLength/2 , this.currentCenterPosition.getY() - Commons.TowerZoneDivideLength/2  );
//    }
    private void updateDirection(){
        this.currentDirection = monsterStrategy.updateDirection(currentCenterPosition,currentDirection);
//        if(currentDirection.equals( new Vector2D(0,0))){
//            Game.getInstance().takePlayerLife(this);
//        }
        this.currentLeftPosition = this.currentLeftPosition.add(this.currentDirection.multiply(speed));
        this.currentCenterPosition = this.calculateCurrentCenter();
    }

    @Override
    public void step() {
        //TODO
        this.updateDirection();
        this.monsterState.update();
    }

    private Vector2D generateRandomStartPoint(){
        Random rand = new Random();
        int randX = rand.nextInt(50);
        int randY = rand.nextInt(50)+300;
        return new Vector2D(randX,randY);
    }


    /**
     *  returns remaining health to game  */
    public double getAttacked(double damage, TowerType towerType){
        health -= damage;
        updateState(towerType);
        return health;
    }

    /**
     * updates the stata of the monster given the tower type from the game
     * @param towerType
     */
    public void updateState(TowerType towerType){
        monsterState.updateAttackingTower(towerType);
        monsterState.update();
    }

    /**
     * called from the concrete state classes and changes the states
     * @param newState
     */
    public void setMonsterState(MonsterState newState){
        this.monsterState= newState;
    }

    public int getHealth(){return this.health;};
    public Vector2D getCurrentCenterPosition(){return this.currentCenterPosition;};
    public void setSpeed(double speed){this.speed = speed;};
    public void decrementHealthByFive(){this.health=-this.health-5;};

    public Vector2D getMonsterDirection(){
        return this.currentDirection;
    }
    public int getMonsterKillReward(){return this.reward;};

    @Override
    public void paint(Graphics g) {
        //TODO

        g.setColor(Color.YELLOW);
        g.fillRect(currentLeftPosition.getIntX(), currentLeftPosition.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(health),currentCenterPosition.getIntX(),currentCenterPosition.getIntY());

        /**
         * call state specific paint
         */
        monsterState.paint(g);

    }
}
