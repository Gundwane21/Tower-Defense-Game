import java.awt.*;
import java.util.Random;

public class Monster extends Entity {
    //TODO

    private int health;
    private double speed = 1 ;
    private final int reward=10;

    protected MonsterState monsterState;
    private IMonsterStrategy monsterStrategy;

    Vector2D currentLeftPosition;
    Vector2D currentCenterPosition;
    Vector2D currentDirection;

    /**
     * creates a new monster by taking the strateg in runtime
     *initially monster is set to MonsterNullState which is created by me
     * to represent while the monster is not in poison and ice state.
     * It creates a random starting position for monster inside the starting zone
     *
     * integer wave count it is used to calculate health of the monster @param waveCount
     * takes a IMonsterStrategy and creates the monster by this strategy in runtime @param monsterStrategy
     */

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
    }

    private Vector2D calculateCurrentCenter(){
        return new Vector2D(this.currentLeftPosition.getX()+ Commons.TowerZoneDivideLength/2 , this.currentLeftPosition.getY() + Commons.TowerZoneDivideLength/2  );
    }

    private void updateDirection(){
//        changeStrategyIfCorner();
        this.currentDirection = monsterStrategy.updateDirection(currentCenterPosition,currentDirection);
        this.currentLeftPosition = this.currentLeftPosition.add(this.currentDirection.multiply(speed));
        this.currentCenterPosition = this.calculateCurrentCenter();
    }

    /**
     * it overrides entity step,
     * updates the direction and position of the monster
     * it calls the strategy updateDirection in runtime so it does
     * not know which strategy does it statically.
     * update direction is made my concrete strategy function
     */
    @Override
    public void step() {
        //TODO
        this.updateDirection();
        this.monsterState.update();
    }

    private void changeStrategyIfCorner(){
        /*
        Corner 1 Line
         */
        int centerX = currentCenterPosition.getIntX();
        int centerY = currentCenterPosition.getIntY();
        if (centerX <= 100 && centerX >= 0 && centerY == 100  ){
            this.monsterStrategy= new MonsterCircularStrategy();
        }
        else if( centerY <= 100 && centerY >= 0 && centerX == 300){
            this.monsterStrategy=  new MonsterCircularStrategy();
        }
        else if(centerX >= 300 && centerX <= 400 && centerY ==300){
            this.monsterStrategy=  new MonsterCircularStrategy();
        }
        else if(centerY >=300 && centerY <=400 && centerX ==100){
            this.monsterStrategy=  new MonsterCircularStrategy();
        }
    }

    /**
     * generates random starting point for monster
     * @return
     */
    private Vector2D generateRandomStartPoint(){
        Random rand = new Random();
        int randX = rand.nextInt(50);
        int randY = rand.nextInt(50)+300;
        return new Vector2D(randX,randY);
    }


    /**
     *  returns remaining health to game.
     *  called from Game instance. It calculates the damage done to monster
     *  and shows it using red rectangle
     *  */
    public double getAttacked(double damage, TowerType towerType){
        health -= damage;

        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawRect(currentCenterPosition.getIntX(),currentCenterPosition.getIntY(),Commons.TowerZoneDivideLength/2,Commons.TowerZoneDivideLength/2);

        updateState(towerType);
        return health;
    }

    /**
     * updates the state of the monster given the tower type from the game
     * it uses the state variable to reach and do changes
     * it does not know anything state specific.
     * Each state implements these method to make state design pattern.
     * it takes TowerType enum @param towerType
     */
    public void updateState(TowerType towerType){
        monsterState.updateAttackingTower(towerType);
        monsterState.update();
    }

    /**
     * called from the concrete state classes and changes the states
     * it takes the MonsterState@param newState
     */
    public void setMonsterState(MonsterState newState){
        this.monsterState= newState;
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
     * it returns the current center position of the monster
     * @return Vector2D currentCenter
     */
    public Vector2D getCurrentCenterPosition(){return this.currentCenterPosition;};

    /**
     * it sets the speed of monster. Called from specific concrete state
     * MonsterIceState
     * double @param speed
     */
    public void setSpeed(double speed){this.speed = speed;};

    /**
     * it is used to decrement healt of monster.
     * Called from Specific MonsterPoisonState
     */
    public void decrementHealthByFive(){this.health=-this.health-5;};

    /**
     * returns currentDirection of the monster
     * @return Vector2D currentDirection
     */
    public Vector2D getMonsterDirection(){return this.currentDirection;}

    /**
     *
     * @return int reward of the killed monster
     */
    public int getMonsterKillReward(){return this.reward;};

    /**
     * It overrides entity paint. Draws monster as a white rectangle
     * Then it calls the monsterstate.paint() by doing that it does not know anythong state specific
     * Concrete state Class overrides this and makes the painting
     * Graphics @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO

        g.setColor(Color.WHITE);
        g.fillRect(currentLeftPosition.getIntX(), currentLeftPosition.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(health),currentCenterPosition.getIntX(),currentCenterPosition.getIntY());

        /**
         * call state specific paint
         */
        monsterState.paint(g);

    }
}
