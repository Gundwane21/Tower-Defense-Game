public abstract class TowerDecorator extends Tower {
    //TODO
    protected Tower wrappedTower;

    /**
     * it takes source tower and creates a
     * wrapped object to implement
     * Decorator Pattern
     * @param sourceTower
     */
    TowerDecorator(Tower sourceTower){
        super(sourceTower.upperLeftPosition);
        this.wrappedTower=sourceTower;};
}
