package com.epam.abstractfactory.skisetparts.helmet;

public class ProfessionalHelmet implements Helmet {
    String helmetName = "With Glasses";

    @Override
    public String getHelmetName() {
        return helmetName;
    }
}
