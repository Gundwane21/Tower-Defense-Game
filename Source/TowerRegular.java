import java.awt.*;
import java.awt.geom.Ellipse2D;


public class TowerRegular extends Tower{

    private Vector2D upperLeftPosition;
    private Vector2D centerPosition;
    private int currentStep;

    private final int range = 150;
    private final int rateOfFire = 20 ;
    private int damage=20;
    private int cost =20;

    TowerRegular(Vector2D position){
        this.upperLeftPosition = position;
        Vector2D center = new Vector2D(upperLeftPosition.getIntX()+(Commons.TowerZoneDivideLength/2), upperLeftPosition.getIntY()+(Commons.TowerZoneDivideLength/2));
        this.centerPosition = center;
        this.currentStep = rateOfFire;

        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }

    public void calculateStepAndAttack(){
        if (currentStep == 0){
            Game.getInstance().attackToMonsterIfRange(centerPosition,range,damage);
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

        g2d.drawOval(centerPosition.getIntX()-(range), centerPosition.getIntY()-(range), range*2  , range*2);

        //TODO
    }
}
