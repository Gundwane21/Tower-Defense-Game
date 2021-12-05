public class TowerRegularFactory implements ITowerFactory {

    /**
     * Factory Design pattern is used
     * only way to create regular tower instances
     * only called from Game class
     * Position of the tower@param position
     * @return Tower
     */
    @Override
    public Tower createTower(Vector2D position) {

        return new TowerRegular(position);
    }
}
