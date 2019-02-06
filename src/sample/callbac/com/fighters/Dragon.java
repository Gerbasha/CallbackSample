package src.sample.callbac.com.fighters;

import src.sample.callbac.com.fighters.base.BaseFighter;
import src.sample.callbac.com.fighters.base.PreFightActions;

public class Dragon extends BaseFighter implements ElementPower, PreFightActions {

    Elements[] elements;
    private int pureAttack;

    public Dragon(String name, int health, int attack, double defense, Elements[] elements) {
        super(name, health, attack, defense);
        this.elements = elements;
        this.pureAttack = attack;
    }

    /**
     * Create a byte mask from dragon's elemental power using ENUMs.
     *
     * @return byteMask
     */
    @Override
    public int getElementPower() {
        int byteMask = 0;
        for (Elements i : elements) {
            byteMask |= i.getValue();
        }
        return byteMask;
    }

    /**
     * Compare byte masks of dragons. getPowerMultiplier takes byte mask of calling object and takes second byte mask as a parameter
     * of second object.
     *
     * <ul>
     * <li>Variable int difPower takes result of ^(bitwise XOR).
     * <li>Variable int tempDifPower takes difPower after ~(bitwise compliment).
     * <li>Variable int reverseElements takes tempDifPower after | (bitwise or) of tempDifPower and power.
     * <li>Variable int elements takes reverseElements after ~(bitwise compliment).
     * </ul>
     * Example: first power 1011, second power 0101;
     *
     * <pre>
     *     1011 ^ 0101 = 1110;
     *     ~ 1110 = 0001;
     *     0001 | 0101 = 0101;
     *     ~ 0101 = 1010;
     * </pre>
     * In cycle for method counts number of "passed" elements of byte mask. In example this number os two (number of "1" in byte mask.
     * So the count will equals 2.
     * @param power
     * @return count
     */

    public int getPowerMultiplier(int power) {
        int difPower = getElementPower() ^ power;
        int tempDifPower = ~difPower;
        int reverseElements = tempDifPower | power;
        int elements = ~reverseElements;
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
        setAttack(getPureAttack());
        if (getHealth() < getFullHP()) {
            setHealth(getFullHP());
        }
        countAttack(fighter);
    }

    public int getPureAttack() {
        return pureAttack;
    }
}