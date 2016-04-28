package com.epam.strategy.guns.behavior;

public class SingleShooting implements ShootSpeedBehavior {
    @Override
    public String shootSpeed() {
        String outData = "I'm single shooting!";
        System.out.println(outData);
        return outData;
    }
}
