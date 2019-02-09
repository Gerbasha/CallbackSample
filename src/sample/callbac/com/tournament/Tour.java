package src.sample.callbac.com.tournament;

import src.sample.callbac.com.arena.Arena;
import src.sample.callbac.com.fighters.base.BaseFighter;

public class Tour {

    Arena arena = new Arena();

    public void createAllFighters() {

        FightersCreator creator = new FightersCreator();
        BaseFighter[] listOfFighters = creator.createFighters();
        BaseFighter[][] fighters = new BaseFighter[listOfFighters.length / 2][];

        int s = listOfFighters.length;
        for (int i = 0; i < fighters.length; i++) {
            fighters[i] = new BaseFighter[s];
            s = s / 2;
        }

        fighters[0] = listOfFighters;

        int f = 0;
        int a = 1;
        for (int i = 0; i < fighters.length - 1; i++) {
            for (int j = 0; j < fighters[i].length; j++) {
                fighters[a][f] = arena.fight(fighters[i][j], fighters[i][++j]);
                f++;
            }
            a++;
            f = 0;
        }
        System.out.println("Winner = " + fighters[3][0].getName());
    }
}