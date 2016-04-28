package com.epam.abstractfactory;

import com.epam.abstractfactory.skisetparts.boots.Boots;
import com.epam.abstractfactory.skisetparts.factory.AmateurPartsFactory;
import com.epam.abstractfactory.skisetparts.factory.EquipmentPartsFactory;
import com.epam.abstractfactory.skisetparts.factory.ProfessionalPartsFactory;
import com.epam.abstractfactory.skisetparts.helmet.Helmet;
import com.epam.abstractfactory.skisetparts.ski.Ski;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AbstractFactoryTest {
    @Test
    public void abstractFactoryTest() {

        EquipmentPartsFactory partsFactory = new AmateurPartsFactory();
        Ski ski = partsFactory.createBoard();
        Boots boots = partsFactory.createBoardBoots();
        Helmet helmet = partsFactory.createHelmet();
        assertEquals("RIDE Board", ski.getSkiName());
        assertEquals("RIDE Board Boots", boots.getBootsName());
        assertEquals("Without Glasses", helmet.getHelmetName());

        partsFactory = new ProfessionalPartsFactory();
        ski = partsFactory.createSki();
        boots = partsFactory.createSkiBoots();
        helmet = partsFactory.createHelmet();
        assertEquals("HEAD Ski", ski.getSkiName());
        assertEquals("DALBELLO Ski Boots", boots.getBootsName());
        assertEquals("With Glasses", helmet.getHelmetName());
    }
}
