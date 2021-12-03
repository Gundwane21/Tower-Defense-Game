import java.awt.*;

public class TowerIce extends Tower{
    //TODO
    private Vector2D position;
    private int currentStep;

    private final int range = 75;
    private final int rateOfFire = 10 ;
    private int damage=5;
    private int cost =25;

    TowerIce(Vector2D position){
        this.position = position;
        this.currentStep = rateOfFire;
        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }


    @Override
    public void step() {
        //TODO

        System.out.println("TowerIce step called");
        if (currentStep == 0){

            currentStep = rateOfFire;
        }
        else{
            currentStep--;
        }
    }
    @Override
    public void paint(Graphics g) {
        //TODO
        System.out.println("TowerIce paint called");
        System.out.println(   "Tower x: " + String.valueOf(position.getIntX() )  +  "Tower  y: " + String.valueOf(position.getIntY() )  );

        g.setColor(Color.BLUE);
        g.fillOval(position.getIntX(), position.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);

    }
}
