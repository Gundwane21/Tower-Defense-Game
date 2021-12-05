public abstract class Tower extends Entity{

    protected Vector2D upperLeftPosition;
    protected Vector2D centerPosition;

    /**
     * simple constructor that calculates upperleft and center positions
     * @param position
     */
    Tower(Vector2D position){
        this.upperLeftPosition=position;
        this.centerPosition=calculateCenterPosition(upperLeftPosition);
    };

    /**
     * calculates the center position of the tower given upper left position of the tower
     * @param upperLeftPosition
     */
    protected Vector2D calculateCenterPosition(Vector2D upperLeftPosition){
        return new Vector2D(upperLeftPosition.getIntX()+(Commons.TowerZoneDivideLength/2), upperLeftPosition.getIntY()+(Commons.TowerZoneDivideLength/2));
    }
    public abstract int getKillCount();
    public abstract int getTowerCost();
    public abstract void step();
    
}
