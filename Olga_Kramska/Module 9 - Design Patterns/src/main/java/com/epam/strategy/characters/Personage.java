package com.epam.strategy.characters;

import com.epam.strategy.fighting.FightingBehaviour;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public abstract class Personage {
    private String name;
    private FightingBehaviour fightingBehaviour;

    public Personage(String name, FightingBehaviour fightingBehaviour) {
        this.name = name;
        this.fightingBehaviour = fightingBehaviour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFightingBehaviour(FightingBehaviour fightingBehaviour) {
        this.fightingBehaviour = fightingBehaviour;
    }

    public void performFighting(){
        fightingBehaviour.fight();
    }
}
