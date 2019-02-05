package src.sample.callbac.com.fighters;

import src.sample.callbac.com.fighters.base.BaseFighter;
import src.sample.callbac.com.fighters.base.PreFightActions;

public class Dragon extends BaseFighter implements ElementPower, PreFightActions {

    int elementMask;
    Elements[] elements;

    public Dragon(String name, int health, int attack, double defense, Elements[] elements) {
        super(name, health, attack, defense);
        this.elements = elements;
    }

    @Override
    public int getElementPower() {
        int byteMask = 0;
        for (Elements i : elements){
            byteMask |= i.getValue();
        }
        return byteMask;
    }

    public int getPowerMultiplier(int power) {
        int difPower = getElementPower() ^ power;
        int tempDifPower = ~difPower;
        int reverseElements = tempDifPower | power;
        int elements = ~ reverseElements;
        int count = 1;
        for (Elements p : Elements.values()) {
            count += (elements & p.getValue()) > 0 ? 1 : 0;
        }
        return count;
    }

    public void countAttack(BaseFighter fighter) {
        int bonusAttack = getPowerMultiplier(fighter instanceof Dragon ? ((Dragon) fighter).getElementPower() : 0);
        setAttack(getAttack() * bonusAttack);
    }

    @Override
    public void actionWithFight(BaseFighter fighter) {
        countAttack(fighter);
    }
}
