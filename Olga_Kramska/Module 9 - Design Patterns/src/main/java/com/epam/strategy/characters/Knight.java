package com.epam.strategy.characters;

import com.epam.strategy.fighting.FightingWithSword;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class Knight extends Personage {

    public Knight(String name) {
        super(name, new FightingWithSword());
    }
}
