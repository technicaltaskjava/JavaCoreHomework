package com.epam.abstractfactory.skisetparts.ski;

public class AmateurBoard implements Ski {
    String skiName = "RIDE Board";

    @Override
    public String getSkiName() {
        return skiName;
    }
}
