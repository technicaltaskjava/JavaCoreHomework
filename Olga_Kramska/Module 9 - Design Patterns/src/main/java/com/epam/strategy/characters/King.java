package com.epam.strategy.characters;

import com.epam.strategy.fighting.FightingWithSword;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class King extends Personage {

    public King(String name) {
        super(name, new FightingWithSword());
    }
}
