package com.epam.strategy.guns.gun;

import com.epam.strategy.guns.behavior.BoardCarry;
import com.epam.strategy.guns.behavior.BurstShooting;

public class MachineGun extends Gun {
    public MachineGun() {
        shootSpeedBehavior = new BurstShooting();
        carryBehavior = new BoardCarry();
    }

    @Override
    public String show() {
        String outData = "It is a machine gun.";
        System.out.println(outData);
        return outData;
    }
}
