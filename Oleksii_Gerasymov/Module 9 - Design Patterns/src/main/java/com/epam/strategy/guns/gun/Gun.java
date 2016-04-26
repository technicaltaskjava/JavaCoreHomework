package com.epam.strategy.guns.gun;

import com.epam.strategy.guns.behavior.CarryBehavior;
import com.epam.strategy.guns.behavior.ShootSpeedBehavior;

public abstract class Gun {
    ShootSpeedBehavior shootSpeedBehavior;
    CarryBehavior carryBehavior;

    public Gun() {
    }

    public abstract String show();

    public String performShootSpeed() {
        return shootSpeedBehavior.shootSpeed();
    }

    public String performCarry() {
        return carryBehavior.carry();
    }

    public String shoot() {
        String outData = "I'm shooting.";
        System.out.println(outData);
        return outData;
    }
}
