import java.awt.*;

public class TowerRegular extends Tower{

    private int currentStep;
    private int killCount=0;


    private final int range = 150;
    private final int rateOfFire = 20 ;
    private final int damage=20;
    private final int  cost =20;

    TowerRegular(Vector2D position){
        super(position);
        this.currentStep = rateOfFire;

    }

    /**
     * calculates steps and only attack when its turn
     * call game instance to reach monsters and damage them.
     */
    private void calculateStepAndAttack(){
        if (currentStep == 0){
            boolean isMonsterKilled = Game.getInstance().attackToMonsterIfRange(centerPosition,TowerType.Regular,range,damage);
            if (isMonsterKilled){
                incrementKillCount();
            }
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
     * draw the tower as red and
     * draws dotted line that represents its range
     * Graphics @param g
     */
    @Override
    public void paint(Graphics g) {

        //System.out.println(   "Tower x: " + String.valueOf(upperLeftPosition.getIntX() )  +  "Tower  y: " + String.valueOf(upperLeftPosition.getIntY() )  );
        /* draw inner circle */
        g.setColor(Color.RED);
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
