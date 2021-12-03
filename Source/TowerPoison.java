import java.awt.*;

public class TowerPoison extends Tower{
    //TODO
    private Vector2D position;

    private final int range = 100;
    private final int rateOfFire = 20 ;
    private int damage= 10;
    private int cost = 15;

    TowerPoison(Vector2D position){
        this.position = position;
        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }

    @Override
    public void step() {
        //TODO
        System.out.println("TowerPoison step called");

    }

    @Override
    public void paint(Graphics g) {
        //TODO
        System.out.println("TowerPoison paint called");
        System.out.println(   "Tower x: " + String.valueOf(position.getIntX() )  +  "Tower  y: " + String.valueOf(position.getIntY() )  );

        g.setColor(Color.GREEN);
        g.fillOval(position.getIntX(), position.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);

    }
}
