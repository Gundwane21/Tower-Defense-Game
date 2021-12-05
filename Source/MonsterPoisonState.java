import java.awt.*;

public class MonsterPoisonState extends MonsterState{


    MonsterPoisonState(Monster monster) {
        super(monster);
    }

    /**
     * Overrides abstract MonsterState update
     * it checks the counter and decreases the health by
     * 5 for 3 steps. Then it returns back to MonsterNullState
     */
    @Override
    public void update() {

        //TODO
        this.stepCounter++;
        if(stepCounter != 3){
            monster.decrementHealthByFive();

        }
        else{
            this.stepCounter = 0;
            monster.setMonsterState(new MonsterNullState(monster));
        }
    }

    /**
     * it overrides abstract MonsterState paint
     * paints a green box around monster while in poison
     * Graphics @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        g.setColor(Color.GREEN);
        g.drawRect(monster.currentLeftPosition.getIntX(), monster.currentLeftPosition.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);

    }
}
