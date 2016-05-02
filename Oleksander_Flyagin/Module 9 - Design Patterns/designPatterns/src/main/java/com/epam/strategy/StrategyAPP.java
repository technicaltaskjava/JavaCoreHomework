package com.epam.strategy;

import com.epam.strategy.os.MacOS;
import com.epam.strategy.os.Unix;
import com.epam.strategy.os.Windows;
import com.epam.strategy.media.CD;
import com.epam.strategy.media.IPX;
import com.epam.strategy.media.USB;
import com.epam.strategy.strategyos.StrategyClient;

public class StrategyAPP {
    private StrategyAPP() {
    }

    public static void main(String[] args) {
        StrategyClient pc = new StrategyClient();
        pc.setStrategy(new CD());
        pc.executeStrategy(new Windows());


        System.out.println("-------------------------------------------------------------------------------");

        pc.setStrategy(new USB());
        pc.executeStrategy(new Unix());

        System.out.println("-------------------------------------------------------------------------------");


        pc.setStrategy(new IPX());
        pc.executeStrategy(new MacOS());

    }
}
