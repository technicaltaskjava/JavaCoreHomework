package com.epam.abstractfactory;

import com.epam.abstractfactory.rent.AmateurRentStore;
import com.epam.abstractfactory.rent.ProfessionalRentStore;
import com.epam.abstractfactory.rent.RentStore;

/* Subject Area is SkiRent. Every visitor orders a ski-set or board set which contains of ski/board, boots and helmet.
Depending of abilities of visitor Fabric assemble professional or amateur kit of equipment. */

public class AbstractFactoryRun {
    private AbstractFactoryRun() {
    }

    public static void equipmentSetRentExample() {
        RentStore amateurRent = new AmateurRentStore();
        RentStore professionalRent = new ProfessionalRentStore();

        amateurRent.orderSkiSet("board");
        System.out.println("First order done." + "\n");

        professionalRent.orderSkiSet("ski");
        System.out.println("Second order done." + "\n");
    }
}
