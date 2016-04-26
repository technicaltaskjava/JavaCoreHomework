package com.kokhanyuk.patterns.abstractfactory.carsspares;

import com.kokhanyuk.patterns.abstractfactory.lib.Wheel;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CarsWheel extends Wheel {
    @Override
    public void tyre(int size) {
        this.sizeWheel=size;
    }
}
