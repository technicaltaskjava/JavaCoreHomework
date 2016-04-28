package com.epam.strategy;

import com.epam.strategy.characters.*;
import com.epam.strategy.fighting.FightingWithLance;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class StrategyTest {

    private StrategyTest() {
    }

    public static void main(String[] args) {
        Personage king = new King("Arthur");
        Personage knight = new Knight("Lancelot");
        Personage troll = new Troll("Troll");

        king.performFighting();

        knight.performFighting();
        knight.setFightingBehaviour(new FightingWithLance());
        knight.performFighting();

        troll.performFighting();
    }
}
