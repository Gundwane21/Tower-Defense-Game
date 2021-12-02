import java.awt.*;

public class TowerIce extends Tower{
    //TODO
    private Vector2D position;

    TowerIce(Vector2D position){
        this.position = position;
        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }


    @Override
    public void step() {
        //TODO
        System.out.println("TowerIce step called");

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
