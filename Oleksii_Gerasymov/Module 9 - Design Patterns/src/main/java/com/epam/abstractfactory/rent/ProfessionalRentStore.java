package com.epam.abstractfactory.rent;

import com.epam.abstractfactory.skisetparts.factory.EquipmentPartsFactory;
import com.epam.abstractfactory.skisetparts.factory.ProfessionalPartsFactory;
import com.epam.abstractfactory.skisets.BoardEquipmentSet;
import com.epam.abstractfactory.skisets.RentEquipmentSet;
import com.epam.abstractfactory.skisets.SkiEquipmentSet;

public class ProfessionalRentStore extends RentStore {
    @Override
    RentEquipmentSet makeEquipmentSet(String equipmentType) {
        RentEquipmentSet equipmentSet = null;
        EquipmentPartsFactory partsFactory = new ProfessionalPartsFactory();

        if ("board".equals(equipmentType)) {
            equipmentSet = new BoardEquipmentSet(partsFactory);
            equipmentSet.setEquipmentType("Professional Board Set");
        }

        if ("ski".equals(equipmentType)) {
            equipmentSet = new SkiEquipmentSet(partsFactory);
            equipmentSet.setEquipmentType("Professional Ski Set");
        }
        return equipmentSet;
    }
}
