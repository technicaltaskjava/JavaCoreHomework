package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class USAMotoFactory {

    private USAMotoFactory() {
    }

    public static Motorcycle makeMoto(MotoType type) {
        Motorcycle motorcycle;
        switch (type) {
            case STANDARD:
                motorcycle = new StandardMoto(Location.USA);
                break;
            case CRUISER:
                motorcycle = new CruiserMoto(Location.USA);
                break;
            case SPORTBIKE:
                motorcycle = new SportBikeMoto(Location.USA);
                break;
            case TOURING:
                motorcycle = new TouringMoto(Location.USA);
                break;
            case OFFROAD:
                motorcycle = new OffRoadMoto(Location.USA);
                break;
            default:
                throw new IllegalArgumentException("No such type of motorcycle");
        }
        return motorcycle;
    }

}
