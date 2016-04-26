package com.epam.abstractfactory;

import com.epam.abstractfactory.skisetparts.boots.Boots;
import com.epam.abstractfactory.skisetparts.factory.AmateurPartsFactory;
import com.epam.abstractfactory.skisetparts.factory.EquipmentPartsFactory;
import com.epam.abstractfactory.skisetparts.factory.ProfessionalPartsFactory;
import com.epam.abstractfactory.skisetparts.helmet.Helmet;
import com.epam.abstractfactory.skisetparts.ski.Ski;
import com.epam.abstractfactory.skisets.BoardEquipmentSet;
import com.epam.abstractfactory.skisets.RentEquipmentSet;
import com.epam.abstractfactory.skisets.SkiEquipmentSet;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AbstractFactoryTest {
    @Test
    public void abstractFactoryTest() {

        EquipmentPartsFactory partsFactory = new AmateurPartsFactory();
        RentEquipmentSet equipmentSet = new BoardEquipmentSet(partsFactory);
        Ski ski = partsFactory.createBoard();
        Boots boots = partsFactory.createBoardBoots();
        Helmet helmet = partsFactory.createHelmet();
        assertEquals("RIDE Board", ski.getSkiName());
        assertEquals("RIDE Board Boots", boots.getBootsName());
        assertEquals("Without Glasses", helmet.getHelmetName());

        partsFactory = new ProfessionalPartsFactory();
        equipmentSet = new SkiEquipmentSet(partsFactory);
        ski = partsFactory.createSki();
        boots = partsFactory.createSkiBoots();
        helmet = partsFactory.createHelmet();
        assertEquals("HEAD Ski", ski.getSkiName());
        assertEquals("DALBELLO Ski Boots", boots.getBootsName());
        assertEquals("With Glasses", helmet.getHelmetName());
    }
}
