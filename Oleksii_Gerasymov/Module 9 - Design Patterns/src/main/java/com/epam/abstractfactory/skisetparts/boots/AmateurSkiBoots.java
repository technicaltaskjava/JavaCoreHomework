package com.epam.abstractfactory.skisetparts.boots;

public class AmateurSkiBoots implements Boots {
    String bootsName = "NORDICA Ski Boots";
    
    @Override
    public String getBootsName() {
        return bootsName;
    }
}
