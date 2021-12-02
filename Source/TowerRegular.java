import java.awt.*;

public class TowerRegular extends Tower{

    private Vector2D position;

    TowerRegular(Vector2D position){
        this.position = position;
        Graphics graphics = Display.getInstance().getGamePanel().getGraphics();
        this.paint(graphics);
    }

    //TODO

    @Override
    public void step() {
        System.out.println("TowerRegular step called");
        //TODO
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("TowerRegular paint called");
        System.out.println(   "Tower x: " + String.valueOf(position.getIntX() )  +  "Tower  y: " + String.valueOf(position.getIntY() )  );

        g.setColor(Color.RED);
        g.fillOval(position.getIntX(), position.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);
        //TODO
    }
}
