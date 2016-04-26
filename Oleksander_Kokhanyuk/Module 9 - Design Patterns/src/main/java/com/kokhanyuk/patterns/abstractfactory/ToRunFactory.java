package com.kokhanyuk.patterns.abstractfactory;

import com.kokhanyuk.patterns.abstractfactory.carsspares.CarsBelt;
import com.kokhanyuk.patterns.abstractfactory.carsspares.CarsSparesFactory;
import com.kokhanyuk.patterns.abstractfactory.planespares.PlaneSparesFactory;
import com.kokhanyuk.patterns.abstractfactory.planespares.PlanesWheel;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ToRunFactory {

    private ToRunFactory() {
    }

    public static void main(String[] args) {
        CarsSparesFactory cars = new CarsSparesFactory();
        PlaneSparesFactory planes = new PlaneSparesFactory();
        CarsBelt belt = cars.createBelt();
        belt.length(1200);
        PlanesWheel wheel = planes.createWheel();
        wheel.tyre(15);
    }
}
