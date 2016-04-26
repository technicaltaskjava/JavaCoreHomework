package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class MotoFactory {

    private MotoFactory() {
    }

    public static Motorcycle makeMoto(MotoType type, Location location)
    {
        Motorcycle motorcycle;
        switch(location) {
            case USA:
                motorcycle = USAMotoFactory.makeMoto(type);
                break;
            case JAPAN:
                motorcycle = JapanMotoFactory.makeMoto(type);
                break;
            case GERMANY:
                motorcycle = GermanyMotoFactory.makeMoto(type);
                break;
            case ITALY:
                motorcycle = ItalyMotoFactory.makeMoto(type);
                break;
            case CHINA:
                motorcycle = ChinaMotoFactory.makeMoto(type);
                break;
            default:
                throw new IllegalArgumentException("No such location of factory");
        }
        return motorcycle;
    }
}
