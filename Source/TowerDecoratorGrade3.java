import java.awt.*;

public class TowerDecoratorGrade3 extends TowerDecorator{
    TowerDecoratorGrade3(Tower sourceTower) {
        super(sourceTower);
    }


    @Override
    public void paint(Graphics g) {
        wrappedTower.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("*", wrappedTower.centerPosition.getIntX()+Commons.TowerZoneDivideLength/10+Commons.TowerZoneDivideLength/10,wrappedTower.centerPosition.getIntY());
    }

    @Override
    public int getKillCount() {
        return wrappedTower.getKillCount();
    }

    @Override
    public int getTowerCost() {
        return this.wrappedTower.getTowerCost();
    }

    @Override
    public void step() {
        System.out.println("Tower Decorater 3 step called");
        wrappedTower.step();
    }

}
