package com.kokhanyuk.patterns.abstractfactory.planespares;

import com.kokhanyuk.patterns.abstractfactory.lib.Wheel;
import org.apache.log4j.Logger;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class PlanesWheel extends Wheel {
    private static Logger logger = Logger.getLogger(PlanesWheel.class);

    @Override
    public void tyre(int size) {
        this.sizeWheel = size;
        logger.info("Planes wheel size " + sizeWheel + " is create.");
    }
}
