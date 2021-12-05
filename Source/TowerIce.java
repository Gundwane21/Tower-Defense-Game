import java.awt.*;

public class TowerIce extends Tower{
    //TODO
    private int currentStep;
    private int killCount=0;

    private final int range = 75;
    private final int rateOfFire = 10 ;
    private final int damage=5;
    private final int cost =25;

    TowerIce(Vector2D position){
        super(position);
        this.currentStep = rateOfFire;
        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }

    public void calculateStepAndAttack(){
        if (currentStep == 0){
            boolean isMonsterKilled = Game.getInstance().attackToMonsterIfRange(centerPosition,TowerType.Ice,range,damage);
            if(isMonsterKilled)
                incrementKillCount();
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


    @Override
    public int getKillCount() {return this.killCount;}

    @Override
    public int getTowerCost() {
        return this.cost;
    }

    public void incrementKillCount(){this.killCount++;};

    @Override
    public void paint(Graphics g) {
        //TODO
    //      System.out.println(   "Tower x: " + String.valueOf(upperLeftPosition.getIntX() )  +  "Tower  y: " + String.valueOf(upperLeftPosition.getIntY() )  );

        g.setColor(Color.BLUE);
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
