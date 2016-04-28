package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class ItalyMotoFactory {

    private ItalyMotoFactory() {
    }

    public static Motorcycle makeMoto(MotoType type) {
        Motorcycle motorcycle;
        switch (type) {
            case STANDARD:
                motorcycle = new StandardMoto(Location.ITALY);
                break;
            case CRUISER:
                motorcycle = new CruiserMoto(Location.ITALY);
                break;
            case SPORTBIKE:
                motorcycle = new SportBikeMoto(Location.ITALY);
                break;
            case TOURING:
                motorcycle = new TouringMoto(Location.ITALY);
                break;
            case OFFROAD:
                motorcycle = new OffRoadMoto(Location.ITALY);
                break;
            default:
                throw new IllegalArgumentException("No such type of motorcycle");
        }
        return motorcycle;
    }

}
