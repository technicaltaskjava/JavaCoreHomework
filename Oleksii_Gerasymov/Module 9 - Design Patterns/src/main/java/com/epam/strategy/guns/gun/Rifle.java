package com.epam.strategy.guns.gun;

import com.epam.strategy.guns.behavior.CombiShooting;
import com.epam.strategy.guns.behavior.HandCarry;

public class Rifle extends Gun {
    public Rifle() {
        shootSpeedBehavior = new CombiShooting();
        carryBehavior = new HandCarry();
    }

    @Override
    public String show() {
        String outData = "It is a rifle.";
        System.out.println(outData);
        return outData;
    }
}
