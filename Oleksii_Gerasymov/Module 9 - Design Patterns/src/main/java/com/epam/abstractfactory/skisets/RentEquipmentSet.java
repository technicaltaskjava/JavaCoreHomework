package com.epam.abstractfactory.skisets;

import com.epam.abstractfactory.skisetparts.boots.Boots;
import com.epam.abstractfactory.skisetparts.ski.Ski;
import com.epam.abstractfactory.skisetparts.helmet.Helmet;

public abstract class RentEquipmentSet {
    String equipmentType;
    Ski ski;
    Boots boots;
    Helmet helmet;

    public abstract void assembleSet();

    public void getDocumentsFromClient() {
        System.out.println("Getting documents from client...");
    }

    public void registerClientAndSet() {
        System.out.println("Registering data about set and client...");
    }

    public void getRentCashFromClient() {
        System.out.println("Getting rent cash from client...");
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void getEquipmentSet() {
        System.out.println("Ski : " + this.ski.getSkiName());
        System.out.println("Boots : " + this.boots.getBootsName());
        System.out.println("Helmet : " + this.helmet.getHelmetName());
    }
}