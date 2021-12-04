public abstract class MonsterState<monster> implements IPaintable{
    //TODO


    protected Monster monster;
    protected TowerType attackingTowerType=null;
    protected int stepCounter=0;

    MonsterState(Monster monster){
        this.monster = monster;
    }

    public abstract void update();
    /**
     * same function for all monster states so no need for abstract
     * @param towerType
     */
    public void updateAttackingTower(TowerType towerType){
        this.attackingTowerType = towerType;
    };

    /*public void incrementStepCounter(){
        this.stepCounter++;
    }*/

}
