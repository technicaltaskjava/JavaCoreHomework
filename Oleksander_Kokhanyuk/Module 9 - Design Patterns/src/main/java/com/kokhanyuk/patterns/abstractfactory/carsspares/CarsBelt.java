package com.kokhanyuk.patterns.abstractfactory.carsspares;

import com.kokhanyuk.patterns.abstractfactory.lib.Belt;
import org.apache.log4j.Logger;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CarsBelt extends Belt {
    private static Logger logger = Logger.getLogger(CarsBelt.class);

    @Override
    public void length(int length) {
        this.lengthBelt = length;
        logger.info("Cars belt length " + lengthBelt + " is create.");
    }

    @Override
    public void buckle(int type) {
        this.buckleType = type;
    }
}
