public abstract class TowerDecorator extends Tower {
    //TODO
    protected Tower wrappedTower;

    TowerDecorator(Tower sourceTower){
        super(sourceTower.upperLeftPosition);
        this.wrappedTower=sourceTower;};
}
