package com.epam.abstractfactory.skisetparts.ski;

public class ProfessionalBoard implements Ski {
    String skiName = "NITRO Board";

    @Override
    public String getSkiName() {
        return skiName;
    }
}
