package com.kokhanyuk.patterns.abstractfactory.lib;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public abstract class Belt {
    protected int lengthBelt;
    protected int buckleType;
    protected abstract void length(int length);
    protected abstract void buckle(int type);
}
