import java.awt.*;

public class TowerDecoratorGrade3 extends TowerDecorator{
    TowerDecoratorGrade3(Tower sourceTower) {
        super(sourceTower);
    }

    /**
     * It first calls wrapped object paint
     * then wraps it around with its own paint
     * Graphics @param g
     */
    @Override
    public void paint(Graphics g) {
        wrappedTower.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("*", wrappedTower.centerPosition.getIntX()+Commons.TowerZoneDivideLength/10+Commons.TowerZoneDivideLength/10,wrappedTower.centerPosition.getIntY());
    }
    /**
     * uses wrapped objects method
     * @return killCountOfTower
     */
    @Override
    public int getKillCount() {
        return wrappedTower.getKillCount();
    }
    /**
     * uses wrapped objects cost
     * @return cost of tower
     */
    @Override
    public int getTowerCost() {
        return this.wrappedTower.getTowerCost();
    }
    /**
     * it calls wrapped objects step
     */
    @Override
    public void step() {
        wrappedTower.step();
    }

}
