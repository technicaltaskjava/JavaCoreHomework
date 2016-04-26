package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class JapanMotoFactory {

    private JapanMotoFactory() {
    }

    public static Motorcycle makeMoto(MotoType type) {
        Motorcycle motorcycle;
        switch (type) {
            case STANDARD:
                motorcycle = new StandardMoto(Location.JAPAN);
                break;
            case CRUISER:
                motorcycle = new CruiserMoto(Location.JAPAN);
                break;
            case SPORTBIKE:
                motorcycle = new SportBikeMoto(Location.JAPAN);
                break;
            case TOURING:
                motorcycle = new TouringMoto(Location.JAPAN);
                break;
            case OFFROAD:
                motorcycle = new OffRoadMoto(Location.JAPAN);
                break;
            default:
                throw new IllegalArgumentException("No such type of motorcycle");
        }
        return motorcycle;
    }

}
