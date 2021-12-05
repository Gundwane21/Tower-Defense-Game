import java.awt.*;

public class TowerDecoratorGrade2 extends TowerDecorator{
    //TODO
    TowerDecoratorGrade2(Tower sourceTower) {
        super(sourceTower);
    }
    @Override
    public void paint(Graphics g) {
        wrappedTower.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("*", wrappedTower.centerPosition.getIntX()+Commons.TowerZoneDivideLength/10,wrappedTower.centerPosition.getIntY());
    }

    @Override
    public int getKillCount() {
        return this.wrappedTower.getKillCount();
    }

    @Override
    public int getTowerCost() {
        return this.wrappedTower.getTowerCost();
    }

    @Override
    public void step() {
        System.out.println("Tower Decorater 2 step called");
        wrappedTower.step();
    }

}
