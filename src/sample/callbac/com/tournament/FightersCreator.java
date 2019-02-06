package src.sample.callbac.com.tournament;

import src.sample.callbac.com.fighters.*;
import src.sample.callbac.com.fighters.base.BaseFighter;

public class FightersCreator {

    public BaseFighter[] createFighters() {

        DragonRider rider1 = new DragonRider("rider1", 100, 20, 0.2);
        Dragon dragon1 = new Dragon("dragon1", 100, 20, 0.2, new ElementPower.Elements[]{ElementPower.Elements.WATER, ElementPower.Elements.FIRE});
        Dragon dragon2 = new Dragon("dragon2", 100, 25, 0.2, new ElementPower.Elements[]{ElementPower.Elements.WATER, ElementPower.Elements.FIRE, ElementPower.Elements.EARTH});
        HollyKnight holly1 = new HollyKnight("holly1", 100, 20, 0.2, 5);
        DarkKnight dark1 = new DarkKnight("dark1", 100, 30, 0.2);
        Knight knight1 = new Knight("knight1", 100, 15, 0.2, 0.1);
        Dragon dragon3 = new Dragon("dragon3", 100, 27, 0.2, new ElementPower.Elements[]{ElementPower.Elements.WATER, ElementPower.Elements.FIRE});
        DarkKnight dark2 = new DarkKnight("dark2", 100, 25, 0.2);


        BaseFighter[] fighters = {rider1, dragon1, dragon2, holly1, dark1, knight1, dragon3, dark2};
        return fighters;
    }
}