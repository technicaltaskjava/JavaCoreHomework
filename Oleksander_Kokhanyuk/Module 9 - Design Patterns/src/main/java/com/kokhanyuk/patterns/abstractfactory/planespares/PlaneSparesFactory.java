package com.kokhanyuk.patterns.abstractfactory.planespares;

import com.kokhanyuk.patterns.abstractfactory.lib.AbstractSparesFactory;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class PlaneSparesFactory extends AbstractSparesFactory {
    @Override
    public PlanesWheel createWheel() {
        return new PlanesWheel();
    }

    @Override
    public PlanesBelt createBelt() {
        return new PlanesBelt();
    }
}
