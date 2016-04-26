package com.epam.abstractfactory.rent;

import com.epam.abstractfactory.skisets.RentEquipmentSet;

public abstract class RentStore {
    public RentEquipmentSet orderSkiSet(String equipmentType) {
        RentEquipmentSet rentEquipment;
        rentEquipment = makeEquipmentSet(equipmentType);
        rentEquipment.assembleSet();
        rentEquipment.getEquipmentSet();
        rentEquipment.getDocumentsFromClient();
        rentEquipment.registerClientAndSet();
        rentEquipment.getRentCashFromClient();
        return rentEquipment;
    }

    abstract RentEquipmentSet makeEquipmentSet(String equipmentType);
}
