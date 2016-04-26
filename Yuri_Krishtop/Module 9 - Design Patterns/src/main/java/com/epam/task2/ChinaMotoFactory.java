package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class ChinaMotoFactory {

    private ChinaMotoFactory() {
    }

    public static Motorcycle makeMoto(MotoType type) {
        Motorcycle motorcycle;
        switch (type) {
            case STANDARD:
                motorcycle = new StandardMoto(Location.CHINA);
                break;
            case CRUISER:
                motorcycle = new CruiserMoto(Location.CHINA);
                break;
            case SPORTBIKE:
                motorcycle = new SportBikeMoto(Location.CHINA);
                break;
            case TOURING:
                motorcycle = new TouringMoto(Location.CHINA);
                break;
            case OFFROAD:
                motorcycle = new OffRoadMoto(Location.CHINA);
                break;
            default:
                throw new IllegalArgumentException("No such type of motorcycle");
        }
        return motorcycle;
    }

}
