package com.kokhanyuk.patterns.abstractfactory.lib;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public abstract class AbstractSparesFactory {
    protected abstract Wheel createWheel();
    protected abstract Belt createBelt();
}
