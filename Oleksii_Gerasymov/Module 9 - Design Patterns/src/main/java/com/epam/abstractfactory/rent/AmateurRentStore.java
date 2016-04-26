package com.epam.abstractfactory.rent;

import com.epam.abstractfactory.skisetparts.factory.AmateurPartsFactory;
import com.epam.abstractfactory.skisetparts.factory.EquipmentPartsFactory;
import com.epam.abstractfactory.skisets.BoardEquipmentSet;
import com.epam.abstractfactory.skisets.SkiEquipmentSet;
import com.epam.abstractfactory.skisets.RentEquipmentSet;

public class AmateurRentStore extends RentStore {
    @Override
    RentEquipmentSet makeEquipmentSet(String equipmentType) {
        RentEquipmentSet equipmentSet = null;
        EquipmentPartsFactory partsFactory = new AmateurPartsFactory();

        if ("board".equals(equipmentType)) {
            equipmentSet = new BoardEquipmentSet(partsFactory);
            equipmentSet.setEquipmentType("Amateur Board Set");
        }

        if ("ski".equals(equipmentType)) {
            equipmentSet = new SkiEquipmentSet(partsFactory);
            equipmentSet.setEquipmentType("Amateur Ski Set");
        }
        return equipmentSet;
    }
}
