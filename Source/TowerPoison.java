import java.awt.*;

public class TowerPoison extends Tower{
    //TODO
    private int currentStep;
    private int killCount=0;

    private final int range = 100;
    private final int rateOfFire = 20 ;
    private final int damage= 10;
    private final int cost = 15;

    TowerPoison(Vector2D position){
        super(position);
        this.currentStep = rateOfFire;

    }
    /**
     * similar to regularTower
     * calculates its step and if in range attack
     * and kill monster
     */
    public void calculateStepAndAttack(){
        if (currentStep == 0){
            Game.getInstance().attackToMonsterIfRange(centerPosition,TowerType.Poison,range,damage);
            currentStep = rateOfFire;
        }
        else{
            currentStep--;
        }

    };

    //TODO
    @Override
    public void step() {

        calculateStepAndAttack();
        //TODO
    }

    /**
     *
     * @return killCount of tower
     */
    @Override
    public int getKillCount(){return this.killCount;}

    /**
     *
     * @return cost of creating this tower
     */
    @Override
    public int getTowerCost() {
        return this.cost;
    }

    /**
     * increments the killcount of this tower
     */
    public void incrementKillCount(){this.killCount++;};

    /**
     * draw the tower as blue and
     * draws dotted line that represents its range
     * Graphics @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        //System.out.println(   "Tower x: " + String.valueOf(upperLeftPosition.getIntX() )  +  "Tower  y: " + String.valueOf(upperLeftPosition.getIntY() )  );

        g.setColor(Color.GREEN);
        g.fillOval(upperLeftPosition.getIntX(), upperLeftPosition.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);


        /* draw dotted lines */
        Graphics2D g2d = (Graphics2D) g.create();
        final  float[] dash1 = {10.0f};
        final  BasicStroke dashed = new BasicStroke(1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, dash1, 0.0f);
        g2d.setStroke(dashed);
        g2d.setColor(Color.ORANGE);

        g2d.drawOval(centerPosition.getIntX()-(range), centerPosition.getIntY()-(range), range*2  , range*2);


    }
}
