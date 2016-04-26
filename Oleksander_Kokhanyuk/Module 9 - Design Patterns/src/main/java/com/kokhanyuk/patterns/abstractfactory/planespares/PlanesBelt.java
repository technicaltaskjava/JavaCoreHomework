package com.kokhanyuk.patterns.abstractfactory.planespares;

import com.kokhanyuk.patterns.abstractfactory.lib.Belt;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class PlanesBelt extends Belt {
    @Override
    public void length(int length) {
        this.lengthBelt = 2 * length;
    }

    @Override
    public void buckle(int type) {
        this.buckleType = type;
    }
}
