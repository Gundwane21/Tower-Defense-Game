import java.awt.*;

public class TowerDecoratorGrade1 extends TowerDecorator{
    TowerDecoratorGrade1(Tower sourceTower) {
        super(sourceTower);
    }

    @Override
    public void paint(Graphics g) {
        wrappedTower.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("Decor 1", wrappedTower.centerPosition.getIntX(),wrappedTower.centerPosition.getIntY());
    }

    @Override
    public void step() {
        System.out.println("Tower Decorater 1 paint called");
        wrappedTower.step();
    }

    //TODO

}

