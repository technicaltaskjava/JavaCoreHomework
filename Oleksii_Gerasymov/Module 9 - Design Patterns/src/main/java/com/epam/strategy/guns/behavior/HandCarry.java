package com.epam.strategy.guns.behavior;

public class HandCarry implements CarryBehavior {
    @Override
    public String carry() {
        String outData = "My gun is in my hand.";
        System.out.println(outData);
        return outData;
    }
}
