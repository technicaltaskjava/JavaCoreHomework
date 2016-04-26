package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class GermanyMotoFactory {

    private GermanyMotoFactory() {
    }

    public static Motorcycle makeMoto(MotoType type) {
        Motorcycle motorcycle;
        switch (type) {
            case STANDARD:
                motorcycle = new StandardMoto(Location.GERMANY);
                break;
            case CRUISER:
                motorcycle = new CruiserMoto(Location.GERMANY);
                break;
            case SPORTBIKE:
                motorcycle = new SportBikeMoto(Location.GERMANY);
                break;
            case TOURING:
                motorcycle = new TouringMoto(Location.GERMANY);
                break;
            case OFFROAD:
                motorcycle = new OffRoadMoto(Location.GERMANY);
                break;
            default:
                throw new IllegalArgumentException("No such type of motorcycle");
        }
        return motorcycle;
    }

}
