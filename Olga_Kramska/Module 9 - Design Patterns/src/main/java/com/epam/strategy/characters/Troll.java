package com.epam.strategy.characters;

import com.epam.strategy.fighting.FightingWithAxe;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class Troll extends Personage {
    public Troll(String name) {
        super(name, new FightingWithAxe());
    }
}
