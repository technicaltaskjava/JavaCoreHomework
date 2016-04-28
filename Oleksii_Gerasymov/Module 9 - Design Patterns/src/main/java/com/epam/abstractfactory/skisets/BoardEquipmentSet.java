package com.epam.abstractfactory.skisets;

import com.epam.abstractfactory.skisetparts.factory.EquipmentPartsFactory;

public class BoardEquipmentSet extends RentEquipmentSet {
    EquipmentPartsFactory partsFactory;

    public BoardEquipmentSet(EquipmentPartsFactory partsFactory) {
        this.partsFactory = partsFactory;
    }

    @Override
    public void assembleSet() {
        System.out.println("Assembling set : " + equipmentType);
        ski = partsFactory.createBoard();
        boots = partsFactory.createBoardBoots();
        helmet = partsFactory.createHelmet();
    }
}
