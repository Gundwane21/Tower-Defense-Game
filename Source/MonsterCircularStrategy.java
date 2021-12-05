public class MonsterCircularStrategy implements IMonsterStrategy {
    //TODO

    /**
     * initialy in left
     */
    private boolean inLeft = true;
    private boolean inUp = false;
    private boolean inRight = false;
    private boolean inDown = false;
    private boolean inEnd = false;

    private final Vector2D  a = new Vector2D(100,100);
    private final Vector2D b = new Vector2D(300,100);
    private final Vector2D c = new Vector2D(300,300);
    private final Vector2D d =  new Vector2D(100,300);

    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {
        //System.out.println("Monster Circular Strategy Called");
        //System.out.println(position.getIntY() >= (Commons.StartHeight + Commons.TowerZoneHeight ));
        //System.out.println(position.getIntX() < (Commons.StartWidth + Commons.TowerZoneWidth ));
        System.out.println("y:" + String.valueOf(( b.getIntY()  - position.getIntY())) + " x: "+  String.valueOf( position.getIntX() - b.getIntX()  ) );

        //Vector2D upperLeft = new Vector2D(position.getX()- Commons.TowerZoneDivideLength/2 , position.getY() - Commons.TowerZoneDivideLength/2 );

        if ( inLeft && ( ( a.getIntY()  - position.getIntY()) == a.getIntX() - position.getIntX()) ){
            inUp = true;
            inLeft = false;
            inRight=false;
            inDown=false;
            inEnd = false;
        }
        else if( inUp && ( b.getIntY()  - position.getIntY() ==  position.getIntX() - b.getIntX())){
            inUp = false;
            inLeft = false;
            inRight=true;
            inDown=false;
            inEnd = false;

        }
        else if( inRight && ( position.getIntY() - c.getIntY() == position.getIntX()- c.getIntX())){
            inUp = false;
            inLeft = false;
            inRight=false;
            inDown=true;
            inEnd = false;
        }
        else if(inDown && ( d.getIntX() - position.getIntX() == position.getIntY() - d.getIntY()    ) ){
            inEnd = true;
            inDown = false;
            inUp = false;
            inRight = false;
            inLeft = false;
        }

        if(inLeft){
            return new Vector2D(0,-1);
        }
        else if(inUp){
            return new Vector2D(1,0);
        }
        else if(inRight){
            return new Vector2D(0,1);
        }
        else if(inDown){
            return new Vector2D(-1,0);
        }
        else if (inEnd){
            return new Vector2D(0,0);
        }
        else {
            System.out.println("Monster Circular move else case");
            return new Vector2D(0,-1);
        }
    }
}
