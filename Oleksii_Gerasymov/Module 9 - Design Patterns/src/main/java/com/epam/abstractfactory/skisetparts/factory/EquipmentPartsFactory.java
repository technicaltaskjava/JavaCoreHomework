package com.epam.abstractfactory.skisetparts.factory;

import com.epam.abstractfactory.skisetparts.boots.Boots;
import com.epam.abstractfactory.skisetparts.ski.Ski;
import com.epam.abstractfactory.skisetparts.helmet.Helmet;

public interface EquipmentPartsFactory {
    public Ski createSki();
    public Ski createBoard();
    public Boots createSkiBoots();
    public Boots createBoardBoots();
    public Helmet createHelmet();
}
