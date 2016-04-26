package com.epam.abstractfactory.skisetparts.ski;

public class ProfessionalSki implements Ski {
    String skiName = "HEAD Ski";

    @Override
    public String getSkiName() {
        return skiName;
    }
}
