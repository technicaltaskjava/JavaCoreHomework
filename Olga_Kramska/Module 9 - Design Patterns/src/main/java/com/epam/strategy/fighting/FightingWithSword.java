package com.epam.strategy.fighting;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class FightingWithSword implements FightingBehaviour {
    @Override
    public void fight() {
        System.out.println("I'm fighting with a sword");
    }
}
