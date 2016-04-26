package com.epam.abstractfactory.skisetparts.factory;

import com.epam.abstractfactory.skisetparts.boots.AmateurBoardBoots;
import com.epam.abstractfactory.skisetparts.boots.AmateurSkiBoots;
import com.epam.abstractfactory.skisetparts.boots.Boots;
import com.epam.abstractfactory.skisetparts.ski.AmateurBoard;
import com.epam.abstractfactory.skisetparts.ski.AmateurSki;
import com.epam.abstractfactory.skisetparts.ski.Ski;
import com.epam.abstractfactory.skisetparts.helmet.AmateurHelmet;
import com.epam.abstractfactory.skisetparts.helmet.Helmet;

public class AmateurPartsFactory implements EquipmentPartsFactory {
    @Override
    public Ski createSki() {
        return new AmateurSki();
    }

    @Override
    public Ski createBoard() {
        return new AmateurBoard();
    }

    @Override
    public Boots createSkiBoots() {
        return new AmateurSkiBoots();
    }

    @Override
    public Boots createBoardBoots() {
        return new AmateurBoardBoots();
    }

    @Override
    public Helmet createHelmet() {
        return new AmateurHelmet();
    }
}
