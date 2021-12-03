public class MonsterCircularStrategy implements IMonsterStrategy {
    //TODO

    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {
        //System.out.println("Monster Circular Strategy Called");
        //System.out.println(position.getIntY() >= (Commons.StartHeight + Commons.TowerZoneHeight ));
        //System.out.println(position.getIntX() < (Commons.StartWidth + Commons.TowerZoneWidth ));
        if (  position.getIntY() >= 0 && (direction.equals(new Vector2D(0,0)) || direction.equals(new Vector2D(0,-1))  )  ){
            return new Vector2D(0,-1);
        }
        else if( position.getIntY() < Commons.StartHeight && position.getIntX() < (Commons.StartWidth + Commons.TowerZoneWidth ) && (direction.equals(new Vector2D(0,-1)) || direction.equals(new Vector2D(1,0)) ) ){
            return new Vector2D(1,0);
        }
        else if( position.getIntX() >= (Commons.StartWidth + Commons.TowerZoneWidth ) && position.getIntY() < (Commons.StartHeight + Commons.TowerZoneWidth )  && (direction.equals(new Vector2D(1,0))  || direction.equals(new Vector2D(0,1) ) )){
            return new Vector2D(0,1);
        }
        else if( position.getIntY() >= (Commons.StartHeight + Commons.TowerZoneHeight ) && position.getIntX() <= (Commons.StartWidth + Commons.TowerZoneWidth )  &&( direction.equals(new Vector2D(0,1)) || direction.equals(new Vector2D(-1,0)  ) )) {
            return new Vector2D(-1, 0);
        }
        else {
            System.out.println("else part monster is finished");
            return new Vector2D(-1,-1);
        }
    }
}
