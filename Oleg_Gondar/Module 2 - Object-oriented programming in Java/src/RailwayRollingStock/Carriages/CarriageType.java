package RailwayRollingStock.Carriages;

/**
 * Created by Oleg on 07.03.2016.
 */
public enum CarriageType {

    COMMON,
    SECOND_CLASS,
    COMPARTMENT,
    FIRST_CLASS,
    HI_SPEED_SECOND_CLASS,
    HI_SPEED_FIRST_CLASS;

    public int seatsCount() {

        switch (this) {
            case COMMON:
                return 81;
            case SECOND_CLASS:
                return 54;
            case COMPARTMENT:
                return 36;
            case FIRST_CLASS:
                return 18;
            case HI_SPEED_SECOND_CLASS:
                return 80;
            case HI_SPEED_FIRST_CLASS:
                return 56;
            default:
                return 0;

        }
    }
}



