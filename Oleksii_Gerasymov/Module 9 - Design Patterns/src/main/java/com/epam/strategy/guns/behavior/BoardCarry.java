package com.epam.strategy.guns.behavior;

public class BoardCarry implements CarryBehavior {
    @Override
    public String carry() {
        String outData = "My gun is on board.";
        System.out.println(outData);
        return outData;
    }
}
