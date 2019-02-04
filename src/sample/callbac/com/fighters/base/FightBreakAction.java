package sample.callbac.com.fighters.base;

public interface FightBreakAction {

    void actionWithFight(BaseFighter fighter, FightCallBack callBack);

    public interface FightCallBack {
        void fightImidiatlyDone(BaseFighter winner);
    }

}
