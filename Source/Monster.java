import java.awt.*;

public class Monster extends Entity {
    //TODO
    private int health;
    private final int speed = 1 ;
    private final int reward=10;

    MonsterState monsterState;
    IMonsterStrategy monsterStrategy;

    Vector2D currentLeftPosition;
    Vector2D currentCenterPosition;
    Vector2D currentDirection;

    Monster(int waveCount,IMonsterStrategy monsterStrategy){
        this.health = 100 + waveCount*20 ;
        this.monsterStrategy =  monsterStrategy;
        currentLeftPosition = new Vector2D(Commons.StartX,Commons.StartY);
        currentCenterPosition = this.calculateCurrentCenter();
        currentDirection= new Vector2D(0,-1);
        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }

    private Vector2D calculateCurrentCenter(){
        return new Vector2D(this.currentLeftPosition.getX()+ Commons.TowerZoneDivideLength/2 , this.currentLeftPosition.getY() + Commons.TowerZoneDivideLength/2  );
    }

    private void updateDirection(){
        this.currentDirection = monsterStrategy.updateDirection(currentLeftPosition,currentDirection);
        System.out.println(currentDirection);
        this.currentLeftPosition = this.currentLeftPosition.add(this.currentDirection);
        this.currentCenterPosition = this.calculateCurrentCenter();
    }

    @Override
    public void step() {
        //TODO
        this.updateDirection();
    }

    /* returns remaining health to game  */
    public double getAttacked(double damage){
        health -= damage;
        return health;
    }

    public Vector2D getCurrentCenterPosition(){return this.currentCenterPosition;}

    @Override
    public void paint(Graphics g) {
        //TODO
        System.out.println("Monster paint called");
        //System.out.println(   "Tower x: " + String.valueOf(upperLeftPosition.getIntX() )  +  "Tower  y: " + String.valueOf(upperLeftPosition.getIntY() )  );
        /* draw monster square */
        g.setColor(Color.YELLOW);
        g.fillRect(currentLeftPosition.getIntX(), currentLeftPosition.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(health),currentCenterPosition.getIntX(),currentCenterPosition.getIntY());
    }
}
