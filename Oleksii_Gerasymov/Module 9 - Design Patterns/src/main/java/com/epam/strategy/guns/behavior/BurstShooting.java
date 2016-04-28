package com.epam.strategy.guns.behavior;

public class BurstShooting implements ShootSpeedBehavior {
    @Override
    public String shootSpeed() {
        String outData = "I'm burst shooting!";
        System.out.println(outData);
        return outData;
    }
}
