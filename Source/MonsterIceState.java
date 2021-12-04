import java.awt.*;

public class MonsterIceState extends MonsterState{

    MonsterIceState(Monster monster) {
        super(monster);
        System.out.println("Monster Ice State Constructor");

    }
    //TODO

    /**
     * updates speed according to step and restores the speed and state of the monster after 3 steps
     */
    @Override
    public void update() {
        //TODO
        this.stepCounter++;
        if(stepCounter != 5){
            monster.setSpeed(0.2*(float)stepCounter);

        }
        else{
            monster.setSpeed(1.0);
            this.stepCounter = 0;
            monster.setMonsterState(new MonsterNullState(monster));
        }
    }


    @Override
    public void paint(Graphics g) {
        //TODO
        System.out.println("Monster Ice State paint called!!!!");
        g.setColor(Color.BLUE);
        g.drawRect(monster.currentLeftPosition.getIntX(), monster.currentLeftPosition.getIntY(), Commons.TowerZoneDivideLength  , Commons.TowerZoneDivideLength);

    }
}
