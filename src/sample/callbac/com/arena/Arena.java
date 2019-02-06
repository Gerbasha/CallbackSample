package src.sample.callbac.com.arena;

import src.sample.callbac.com.fighters.base.BaseFighter;
import src.sample.callbac.com.fighters.base.FightBreakAction;
import src.sample.callbac.com.fighters.base.PostFightActions;
import src.sample.callbac.com.fighters.base.PreFightActions;

public class Arena {
    PrintResult result = new PrintResult();
    /**
     * @param opponentOne first fighter
     * @param opponentTwo second fighter
     * @return return winner, or null when both are dead
     */
    public BaseFighter fight(BaseFighter opponentOne, BaseFighter opponentTwo) {
        int roundsCount = 0;

        final BaseFighter[] winner = {null}; //holder for callback result

        preRoundActions(opponentOne, opponentTwo, (w) -> winner[0] = w);
        if (winner[0] != null) return winner[0];
        preRoundActions(opponentTwo, opponentOne, (w) -> winner[0] = w);
        if (winner[0] != null) return winner[0];

        while (roundsCount <= 10) {

            int takenDamage2 = opponentOne.attack(opponentTwo);
            int takenDamage1 = opponentTwo.attack(opponentOne);

            postRoundActions(opponentOne, opponentTwo, takenDamage2);
            postRoundActions(opponentTwo, opponentOne, takenDamage1);

            if (opponentOne.isAlive() && opponentTwo.isAlive()) {
                roundsCount++;
            } else if (opponentOne.isAlive()) {
                result.printResult(opponentOne.getName(), opponentOne.getHealth(), roundsCount);
                return opponentOne;
            } else if (opponentTwo.isAlive()) {
                result.printResult(opponentTwo.getName(), opponentTwo.getHealth(), roundsCount);
                return opponentTwo;
            }
            if (roundsCount == 10) {
                if (opponentOne.getHealth() > opponentTwo.getHealth()) {
                    result.printResult(opponentOne.getName(), opponentOne.getHealth(), roundsCount);
                    return opponentOne;
                } else if (opponentTwo.getHealth() > opponentOne.getHealth()) {
                    result.printResult(opponentTwo.getName(), opponentTwo.getHealth(), roundsCount);
                    return opponentTwo;
                }
            }
        }
        return null;
    }

    private void preRoundActions(BaseFighter opponentOne, BaseFighter opponentTwo, FightBreakAction.FightCallBack callBack) {
        if (opponentOne instanceof PreFightActions)
            ((PreFightActions) opponentOne).actionWithFight(opponentTwo);
        if (opponentOne instanceof FightBreakAction)
            ((FightBreakAction) opponentOne).actionWithFight(opponentTwo, callBack);
    }

    private void postRoundActions(BaseFighter opponentOne, BaseFighter opponentTwo, int damageTakenByOpponentTwo) {
        if (opponentOne instanceof PostFightActions)
            ((PostFightActions) opponentOne).action(opponentTwo, damageTakenByOpponentTwo);

    }
}