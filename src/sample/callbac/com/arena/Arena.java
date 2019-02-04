package sample.callbac.com.arena;

import sample.callbac.com.fighters.base.BaseFighter;
import sample.callbac.com.fighters.base.FightBreakAction;
import sample.callbac.com.fighters.base.PostFightActions;
import sample.callbac.com.fighters.base.PreFightActions;

public class Arena {

    /**
     * @param oponnentOne first fighter
     * @param opponentToo second fighter
     * @return return winner, or null when both a dead
     */
    public BaseFighter fight(BaseFighter oponnentOne, BaseFighter opponentToo) {
        int roundsCount = 10;

        final BaseFighter[] winner = {null}; //holder for callback result
        while (roundsCount > 0) {
            preRoundActions(oponnentOne, opponentToo, (w) -> winner[0] = w);
            if (winner[0] != null) return winner[0];
            preRoundActions(opponentToo, oponnentOne, (w) -> winner[0] = w);
            if (winner[0] != null) return winner[0];

            int takenDamage2 = oponnentOne.attac(opponentToo);
            int takenDamage1 = opponentToo.attac(oponnentOne);

            postRoundActions(oponnentOne, opponentToo, takenDamage2);
            postRoundActions(opponentToo, oponnentOne, takenDamage1);

            if (oponnentOne.isAlive() && opponentToo.isAlive())
                roundsCount++;
            else if (oponnentOne.isAlive())
                return oponnentOne;
            else if (opponentToo.isAlive())
                return opponentToo;
            else return null;
        }
        return null;
    }

    private void preRoundActions(BaseFighter oponnentOne, BaseFighter opponentToo, FightBreakAction.FightCallBack callBack) {
        if (oponnentOne instanceof PreFightActions)
            ((PreFightActions) oponnentOne).actionWithFight(opponentToo);
        if (oponnentOne instanceof FightBreakAction)
            ((FightBreakAction) oponnentOne).actionWithFight(opponentToo, callBack);
    }

    private void postRoundActions(BaseFighter oponnentOne, BaseFighter opponentToo, int damageTakenByOppponentToo) {
        if (oponnentOne instanceof PostFightActions)
            ((PostFightActions) oponnentOne).action(opponentToo, damageTakenByOppponentToo);

    }

}
