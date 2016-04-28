package com.epam.strategy.fighting;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class FightingWithLance implements FightingBehaviour {
    @Override
    public void fight() {
        System.out.println("I attack with a lance");
    }
}
