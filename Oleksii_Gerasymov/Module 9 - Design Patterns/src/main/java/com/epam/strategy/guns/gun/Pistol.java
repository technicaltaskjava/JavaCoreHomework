package com.epam.strategy.guns.gun;

import com.epam.strategy.guns.behavior.HandCarry;
import com.epam.strategy.guns.behavior.SingleShooting;

public class Pistol extends Gun {
    public Pistol() {
        shootSpeedBehavior = new SingleShooting();
        carryBehavior = new HandCarry();
    }

    @Override
    public String show() {
        String outData = "It is a pistol.";
        System.out.println(outData);
        return outData;
    }


}
