import java.awt.*;

public class MonsterPoisonState extends MonsterState{


    MonsterPoisonState(Monster monster) {
        super(monster);
    }

    //TODO
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

    @Override
    public void paint(Graphics g) {
        //TODO
        System.out.println("Monster Poison State paint called!!!!");
        g.setColor(Color.GREEN);
        g.drawRect(monster.currentLeftPosition.getIntX(), monster.currentLeftPosition.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);

    }
}
