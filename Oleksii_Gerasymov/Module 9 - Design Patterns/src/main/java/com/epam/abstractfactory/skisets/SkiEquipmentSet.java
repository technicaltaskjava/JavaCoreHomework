package com.epam.abstractfactory.skisets;

import com.epam.abstractfactory.skisetparts.factory.EquipmentPartsFactory;

public class SkiEquipmentSet extends RentEquipmentSet {
    EquipmentPartsFactory partsFactory;

    public SkiEquipmentSet(EquipmentPartsFactory partsFactory) {
        this.partsFactory = partsFactory;
    }

    @Override
    public void assembleSet() {
        System.out.println("Assembling set : " + equipmentType);
        ski = partsFactory.createSki();
        boots = partsFactory.createSkiBoots();
        helmet = partsFactory.createHelmet();
    }
}
