import java.awt.*;

public class TowerDecoratorGrade1 extends TowerDecorator{
    TowerDecoratorGrade1(Tower sourceTower) {
        super(sourceTower);
    }

    @Override
    public void paint(Graphics g) {
        wrappedTower.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("*", wrappedTower.centerPosition.getIntX(),wrappedTower.centerPosition.getIntY());
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
        System.out.println("Tower Decorater 1 step called");
        wrappedTower.step();
    }

    //TODO

}

