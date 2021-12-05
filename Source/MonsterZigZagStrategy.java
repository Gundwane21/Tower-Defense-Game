public class MonsterZigZagStrategy implements IMonsterStrategy {

    private boolean inLeft = true;
    private boolean inUp = false;
    private boolean inRight = false;
    private boolean inDown = false;
    private boolean inEnd = false;

    private boolean goUpRight = true;
    private boolean goUpLeft = false;
    private boolean goDownRight = false;
    private boolean goDownLeft = false;

    private final Vector2D  a = new Vector2D(100,100);
    private final Vector2D b = new Vector2D(300,100);
    private final Vector2D c = new Vector2D(300,300);

    /**
     * It calculates the new direction for ZigZag
     * move of monster. It does not know anything rather than paramaters
     * Current position @param position
     * previous direction @param direction
     * @return newDirection
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {

        int rightBoundary = position.getIntX() + Commons.TowerZoneDivideLength/2;
        int leftBoundary = position.getIntX() - Commons.TowerZoneDivideLength/2;
        int upBoundary = position.getIntY() - Commons.TowerZoneDivideLength/2;
        int downBoundary = position.getIntY() + Commons.TowerZoneDivideLength/2;

        if ( inLeft && downBoundary < Commons.TowerZoneY ){
            inUp = true;
            inLeft = false;
            inRight=false;
            inDown=false;
            inEnd = false;
        }
        else if( inUp && ( leftBoundary > Commons.StartWidth+Commons.TowerZoneWidth)){
            inUp = false;
            inLeft = false;
            inRight=true;
            inDown=false;
            inEnd = false;

        }
        else if( inRight && ( upBoundary > Commons.StartY )){
            inUp = false;
            inLeft = false;
            inRight=false;
            inDown=true;
            inEnd = false;
        }
        else if( !inLeft && !inRight && !inUp && !inDown && !inEnd){
            inLeft = true;
            inEnd = false;
            inDown = false;
            inUp = false;
            inRight = false;
        }
        /**
         * monster reached to the start zone
         */
        else if(inDown && (position.getIntX() < Commons.TowerZoneX ) ){
            inUp = false;
            inLeft = false;
            inRight=false;
            inDown=false;
            inEnd = true;
        }

        if(inLeft){
            if( direction.equals(new Vector2D(1,-1)) &&  Commons.TowerZoneX == rightBoundary ) {
               goUpRight = false;
               goUpLeft = true;
               goDownLeft= false;
               goDownRight=false;

            }
            else if (( direction.equals(new Vector2D(-1,-1)) &&  Commons.StartX == leftBoundary )){
                goUpRight = true;
                goUpLeft = false;
                goDownLeft= false;
                goDownRight=false;
            }
        }
        else if(inUp) {
            if (direction.equals(new Vector2D(1, -1)) && 0 == upBoundary) {
                goUpRight = false;
                goUpLeft = false;
                goDownLeft = false;
                goDownRight = true;

            } else if ((direction.equals(new Vector2D(1, 1)) && 100 == downBoundary)) {
                goUpRight = true;
                goUpLeft = false;
                goDownLeft = false;
                goDownRight = false;
            } else if ((direction.equals(new Vector2D(-1, -1)) && Commons.StartX == leftBoundary)) {
                goUpRight = true;
                goUpLeft = false;
                goDownLeft = false;
                goDownRight = false;

            }
        }
        else if(inRight) {
            if (direction.equals(new Vector2D(1, 1)) && Commons.GamePanelWidth == rightBoundary) {
                goUpRight = false;
                goUpLeft = false;
                goDownLeft = true;
                goDownRight = false;

            } else if ((direction.equals(new Vector2D(-1, 1)) && Commons.StartWidth + Commons.TowerZoneWidth == leftBoundary)) {
                goUpRight = false;
                goUpLeft = false;
                goDownLeft = false;
                goDownRight = true;
            } else if (direction.equals(new Vector2D(1, -1)) && 0 == upBoundary) {
                goUpRight = false;
                goUpLeft = false;
                goDownLeft = false;
                goDownRight = true;

            }
        }
        else if(inDown){
            if( direction.equals(new Vector2D(-1,-1)) &&  Commons.TowerZoneY+Commons.TowerZoneHeight == upBoundary ) {
                goUpRight = false;
                goUpLeft = false;
                goDownLeft= true;
                goDownRight=false;

            }
            else if (( direction.equals(new Vector2D(-1,1)) &&  Commons.GameHeight == downBoundary )){
                goUpRight = false;
                goUpLeft = true;
                goDownLeft= false;
                goDownRight=false;
            }
            if (direction.equals(new Vector2D(1, 1)) && Commons.GamePanelWidth == rightBoundary) {
                goUpRight = false;
                goUpLeft = false;
                goDownLeft = true;
                goDownRight = false;

            }
        }
        else if(inEnd){
            return new Vector2D(0,0);
        }

        if(goUpRight){
            return new Vector2D(1, -1);
        }
        else if(goUpLeft){
            return new Vector2D(-1, -1);
        }
        else if(goDownRight){
            return new Vector2D(1,1);
        }
        else if(goDownLeft){
            return new Vector2D(-1,1);
        }
        else{
            System.out.println("Monster Zig Zag Strategy else");
            return  new Vector2D(1,-1);
        }
    }
}
