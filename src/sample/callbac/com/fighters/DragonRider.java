package src.sample.callbac.com.fighters;

import src.sample.callbac.com.fighters.base.BaseFighter;
import src.sample.callbac.com.fighters.base.FightBreakAction;
import src.sample.callbac.com.fighters.base.PostFightActions;

public class DragonRider extends BaseFighter implements PostFightActions, FightBreakAction {

    Dragon dragonPet = null;
    private int pureAttack;

    public DragonRider(String name, int health, int attack, double defense) {
        super(name, health, attack, defense);
        pureAttack = attack;
    }
    public void countHealth() {
        setHealth(getHealth() + dragonPet.getHealth());
    }

    public void countAttack() {
        setAttack(getAttack() + dragonPet.getAttack());
    }

    private boolean isDragonAlive() {
        return getHealth() > getFullHP();
    }

    @Override
    public void action(BaseFighter opponent, int takenDamage) {
        if (!isDragonAlive()) {
            opponent.setAttack(getPureAttack());
        }
    }

    public int getPureAttack() {
        return pureAttack;
    }

    @Override
    public void actionWithFight(BaseFighter fighter, FightCallBack callBack) {
        if (!isDragonAlive()){
            dragonPet = (Dragon) fighter;
            countAttack();
            countHealth();
            callBack.fightImidiatlyDone(this);
        }
    }
}
