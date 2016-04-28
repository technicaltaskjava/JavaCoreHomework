package com.epam.abstractfactory.skisetparts.factory;

import com.epam.abstractfactory.skisetparts.boots.Boots;
import com.epam.abstractfactory.skisetparts.boots.ProfessionalBoardBoots;
import com.epam.abstractfactory.skisetparts.boots.ProfessionalSkiBoots;
import com.epam.abstractfactory.skisetparts.ski.ProfessionalBoard;
import com.epam.abstractfactory.skisetparts.ski.ProfessionalSki;
import com.epam.abstractfactory.skisetparts.ski.Ski;
import com.epam.abstractfactory.skisetparts.helmet.Helmet;
import com.epam.abstractfactory.skisetparts.helmet.ProfessionalHelmet;

public class ProfessionalPartsFactory implements EquipmentPartsFactory {
    @Override
    public Ski createSki() {
        return new ProfessionalSki();
    }

    @Override
    public Ski createBoard() {
        return new ProfessionalBoard();
    }

    @Override
    public Boots createSkiBoots() {
        return new ProfessionalSkiBoots();
    }

    @Override
    public Boots createBoardBoots() {
        return new ProfessionalBoardBoots();
    }

    @Override
    public Helmet createHelmet() {
        return new ProfessionalHelmet();
    }
}
