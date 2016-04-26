package com.epam.strategy;

/* Subject Area is Guns. Every gun can shoot. Pistol shoots single, Rifle shoots combine, Machine gun shoots burst.
Pistol and Rifle are carried by hands, Machine gun is carried on board. */

import com.epam.strategy.guns.gun.Gun;
import com.epam.strategy.guns.gun.Rifle;

public class StrategyRun {
    private StrategyRun() {
    }

    public static void gunsExample() {
        Gun m16 = new Rifle();
        m16.show();
        m16.shoot();
        m16.performShootSpeed();
        m16.performCarry();
    }
}
