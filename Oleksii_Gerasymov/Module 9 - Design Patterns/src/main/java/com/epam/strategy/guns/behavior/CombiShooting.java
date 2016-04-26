package com.epam.strategy.guns.behavior;

public class CombiShooting implements ShootSpeedBehavior {
    @Override
    public String shootSpeed() {
        String outData = "I'm single or burst shooting!";
        System.out.println(outData);
        return outData;
    }
}
