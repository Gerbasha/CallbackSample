package src.sample.callbac.com.fighters;

import src.sample.callbac.com.fighters.base.BaseFighter;
import src.sample.callbac.com.fighters.base.PostFightActions;
import src.sample.callbac.com.fighters.base.PreFightActions;

public class HollyKnight extends BaseFighter implements PostFightActions, PreFightActions {

    int heal;

    public HollyKnight(String name, int health, int attack, double defense, int heal) {
        super(name, health, attack, defense);
        this.heal = heal;
    }

    @Override
    public void action(BaseFighter opponent, int takenDamage) {
        if (getHealth() - takenDamage > heal && getHealth() < getFullHP()) {
            setHealth(getHealth() + heal);
            overHeal();
        }
    }

    public void overHeal() {
        if (getHealth() > getFullHP()) {
            setHealth(getFullHP());
        }
    }

    @Override
    public void actionWithFight(BaseFighter fighter) {
        if (getHealth() < getFullHP()){
            setHealth(getFullHP());
        }
    }
}
