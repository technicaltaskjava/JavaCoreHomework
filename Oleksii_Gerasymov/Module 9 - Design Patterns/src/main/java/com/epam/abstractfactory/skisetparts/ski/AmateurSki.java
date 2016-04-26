package com.epam.abstractfactory.skisetparts.ski;

public class AmateurSki implements Ski {
    String skiName = "BUKOVEL Ski";

    @Override
    public String getSkiName() {
        return skiName;
    }
}
