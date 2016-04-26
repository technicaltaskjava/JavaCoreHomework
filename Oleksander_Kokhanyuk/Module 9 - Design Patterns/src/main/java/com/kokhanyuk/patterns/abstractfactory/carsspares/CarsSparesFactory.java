package com.kokhanyuk.patterns.abstractfactory.carsspares;

import com.kokhanyuk.patterns.abstractfactory.lib.AbstractSparesFactory;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CarsSparesFactory extends AbstractSparesFactory {
    @Override
    public CarsWheel createWheel() {
        return new CarsWheel();
    }

    @Override
    public CarsBelt createBelt() {
        return new CarsBelt();
    }
}
