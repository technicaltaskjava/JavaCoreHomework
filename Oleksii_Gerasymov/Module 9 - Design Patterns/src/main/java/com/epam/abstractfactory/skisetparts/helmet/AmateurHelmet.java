package com.epam.abstractfactory.skisetparts.helmet;

public class AmateurHelmet implements Helmet {
    String helmetName = "Without Glasses";

    @Override
    public String getHelmetName() {
        return helmetName;
    }
}
