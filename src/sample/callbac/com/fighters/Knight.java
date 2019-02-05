package src.sample.callbac.com.fighters;

import src.sample.callbac.com.fighters.base.BaseFighter;

import java.util.Random;

public class Knight extends BaseFighter {

    private double shield;
    private Random random;

    public Knight(String name, int health, int attack, double defense, float shield) {
        super(name, health, attack, defense);
        this.shield = shield;
        random = new Random();
    }

    @Override
    public void takeDamage(int damage) {
        double r = random.nextGaussian();
        if ( r > shield){
            super.takeDamage(damage);
        }
    }
}
