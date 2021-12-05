import java.awt.*;

public class MonsterNullState extends MonsterState{

    /**
     * inherit monster from abstract class monster
     * code reuse*/
    MonsterNullState(Monster monster) {
        super(monster);
    }

    /**
     * Overrides abstract MonsterState update
     * It changes state when a Ice Tower or Poison Tower is
     * in range
     */
    @Override
    public void update() {
        if(attackingTowerType == TowerType.Ice){
            monster.setMonsterState(new MonsterIceState(monster));
        }
        else if(attackingTowerType == TowerType.Poison){
            monster.setMonsterState(new MonsterPoisonState(monster));
        }
        else{
            monster.setMonsterState(new MonsterNullState(monster));
        }
    }


    @Override
    public void paint(Graphics g) {
        //monster.paint(g);
    }


}
