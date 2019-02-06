package src.sample.callbac.com.fighters;

import src.sample.callbac.com.fighters.base.BaseFighter;
import src.sample.callbac.com.fighters.base.PostFightActions;
import src.sample.callbac.com.fighters.base.PreFightActions;

public class DarkKnight extends BaseFighter implements PostFightActions, PreFightActions {
    public DarkKnight(String name, int health, int attack, double defense) {
        super(name, health, attack, defense);
    }

    @Override
    public void action(BaseFighter opponent, int takenDamage) {
        setHealth(getHealth() + ((getFullHP() - getHealth()) / 2) );
    }

    @Override
    public void actionWithFight(BaseFighter fighter) {
        if (getHealth() < getFullHP()){
            setHealth(getFullHP());
        }
    }
}